package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.dto.response.TicketResponse;
import com.samisezgin.finalproject.exceptions.*;
import com.samisezgin.finalproject.model.Booking;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;
import com.samisezgin.finalproject.model.enums.PaymentStatus;
import com.samisezgin.finalproject.repository.BookingRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.repository.VoyageRepository;
import com.samisezgin.finalproject.service.BookingService;
import com.samisezgin.finalproject.service.VoyageService;
import com.samisezgin.finalproject.util.Constants;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

@Service
public class BookingServiceImpl implements BookingService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;
    private final VoyageRepository voyageRepository;
    private final VoyageService voyageService;

    public BookingServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                              BookingRepository bookingRepository,
                              VoyageRepository voyageRepository, VoyageService voyageService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bookingRepository = bookingRepository;
        this.voyageRepository = voyageRepository;
        this.voyageService = voyageService;
    }

    private static void checkIfBookingIsValid(BookingRequest bookingRequest) {
        if (bookingRequest.getBookingListTicketList() == null || bookingRequest.getBookingListTicketList().length == 0) {
            LoggerUtil.getLogger().log(Level.SEVERE, "Bireysel kullanıcı bir sefer için en fazla " + Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER + " bilet alabilir!");
            throw new EmptyTicketListException("Sefere bilet eklenmedi");
        }
    }

    private static void checkPassengerIsAvailableForBooking(BookingRequest bookingRequest, User foundUser, Voyage foundVoyage) {
        var previousTicketCount = foundUser.getBookingList().stream().flatMap(booking -> booking.getTicketList().stream().filter(ticket -> ticket.getVoyage().getId().equals(foundVoyage.getId()))).count();

        if (foundUser.getPassengerType().equals(PassengerType.INDIVIDUAL)) {
            if (bookingRequest.bookingListTicketList.length + previousTicketCount > Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER) {
                LoggerUtil.getLogger().log(Level.SEVERE, "Bireysel kullanıcı bir sefer için en fazla " + Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER + " bilet alabilir!");
                throw new TicketListOverflowException("Bireysel kullanıcı bir sefer için en fazla " + Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER + " bilet alabilir!");
            }
            int maleCount = 0;
            for (TicketRequest ticket : bookingRequest.getBookingListTicketList()) {
                if (ticket.getGender() == Gender.MALE)
                    maleCount++;
                if (maleCount > Constants.MAX_ALLOWED_MALE_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER) {
                    LoggerUtil.getLogger().log(Level.SEVERE, "Bireysel kullanıcı tek rezervazyonda en fazla " + Constants.MAX_ALLOWED_MALE_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER + " erkek bileti alabilir.");
                    throw new MaleTicketLimitException("Bireysel kullanıcı tek rezervazyonda en fazla " + Constants.MAX_ALLOWED_MALE_TICKETS_PER_BOOKING_FOR_INDIVIDUAL_PASSENGER + " erkek bileti alabilir.");
                }
            }
        }
        if (foundUser.getPassengerType().equals(PassengerType.CORPORATE)) {
            if (bookingRequest.bookingListTicketList.length + previousTicketCount > Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_CORPORATE_PASSENGER) {
                LoggerUtil.getLogger().log(Level.SEVERE, "Kurumsal kullanıcı bir sefer için en fazla " + Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_CORPORATE_PASSENGER + " bilet alabilir!");
                throw new TicketListOverflowException("Kurumsal kullanıcı bir sefer için en fazla " + Constants.MAX_ALLOWED_TICKETS_PER_BOOKING_FOR_CORPORATE_PASSENGER + " bilet alabilir!");
            }
        }
    }

    private static void checkVoyageHasAvailableSeats(Voyage foundVoyage, BookingRequest bookingRequest) {
        if (foundVoyage.getAvailableSeats() < bookingRequest.getBookingListTicketList().length) {
            LoggerUtil.getLogger().log(Level.WARNING, "Seferde yeterli yer bulunmamaktadır.");
            throw new TicketListOverflowException("Seferde yeterli yer bulunmamaktadır.");
        }
    }

    @Override
    public BookingResponse create(BookingRequest bookingRequest) {

        checkIfBookingIsValid(bookingRequest);

        User foundUser = findUser(bookingRequest);

        Voyage foundVoyage = findVoyage(bookingRequest);

        checkVoyageHasAvailableSeats(foundVoyage, bookingRequest);

        checkPassengerIsAvailableForBooking(bookingRequest, foundUser, foundVoyage);

        Booking booking = new Booking();

        List<Ticket> ticketList= Arrays.stream(bookingRequest.getBookingListTicketList()).toList().stream().map(ticketRequest -> modelMapper.map(ticketRequest,Ticket.class)).toList();

        prepareBooking(foundUser, foundVoyage, booking, ticketList);

        bookingRepository.save(booking);

        BookingResponse bookingResponse=modelMapper.map(booking,BookingResponse.class);

        bookingResponse.setTicketResponseList(ticketList.stream().map(ticket -> modelMapper.map(ticket,TicketResponse.class)).toList());
        LoggerUtil.getLogger().log(Level.INFO, "BookingService -> createBooking done. "+bookingRequest.getPassengerUserEmail()+"---"+bookingRequest.getFromCity()+"->"+bookingRequest.getToCity()+"->"+bookingRequest.getVoyageDateTime());
        return bookingResponse;
    }

    private Voyage findVoyage(BookingRequest bookingRequest) {
        return voyageService.findVoyage(bookingRequest);
    }

    private User findUser(BookingRequest bookingRequest) {
        User foundUser = userRepository
                .findByEmailIgnoreCase(bookingRequest.getPassengerUserEmail())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));
        return foundUser;
    }

    private void prepareBooking(User foundUser, Voyage foundVoyages, Booking booking, List<Ticket> ticketList) {
        booking.setPassengerUser(foundUser);
        booking.setTicketList(ticketList);
        booking.getTicketList().forEach(
                ticket -> {
                    ticket.setBooking(booking);
                    ticket.setVoyage(foundVoyages);
                    ticket.setPrice(ticket.getVoyage().getPrice());
                    booking.setBookingTotalPrice(booking.getBookingTotalPrice() + ticket.getPrice());
                    ticket.getVoyage().setAvailableSeats(ticket.getVoyage().getAvailableSeats() - 1);
                });
    }

    @Override
    public BookingResponse getById(Integer id) {

        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Rezervasyon bulunamadı"));
        BookingResponse bookingResponse=modelMapper.map(booking,BookingResponse.class);
        bookingResponse.setTicketResponseList(booking.getTicketList().stream().map(ticket -> modelMapper.map(ticket,TicketResponse.class)).toList());
        return bookingResponse;
    }

    @Override
    public void changePaymentStatus(Integer id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Rezervasyon bulunamadı"));

        booking.setPaymentStatus(PaymentStatus.SUCCESS);
        LoggerUtil.getLogger().log(Level.INFO, "BookingService -> changePaymentStatus done.");
        bookingRepository.save(booking);
    }


}
