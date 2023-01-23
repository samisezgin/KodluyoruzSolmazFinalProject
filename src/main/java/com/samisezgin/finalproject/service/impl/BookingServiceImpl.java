package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.exceptions.*;
import com.samisezgin.finalproject.model.Booking;
import com.samisezgin.finalproject.model.PassengerUser;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;
import com.samisezgin.finalproject.repository.BookingRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.repository.VoyageRepository;
import com.samisezgin.finalproject.service.BookingService;
import com.samisezgin.finalproject.util.CustomDateTimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BookingServiceImpl implements BookingService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;
    private final VoyageRepository voyageRepository;

    public BookingServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                              BookingRepository bookingRepository,
                              VoyageRepository voyageRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bookingRepository = bookingRepository;
        this.voyageRepository = voyageRepository;
    }

    private static void checkIfBookingIsValid(BookingRequest bookingRequest) {
        if (bookingRequest.getBookingListTicketList() == null || bookingRequest.getBookingListTicketList().length == 0) {
            throw new EmptyTicketListException("Sefere bilet eklenmedi");
        }
    }

    private static void checkPassengerIsAvailableForBooking(BookingRequest bookingRequest, PassengerUser foundUser, Voyage foundVoyage) {
        var previousTicketCount = foundUser.getBookingList().stream().flatMap(booking -> booking.getTicketList().stream().filter(ticket -> ticket.getVoyage().getId().equals(foundVoyage.getId()))).count();

        if (foundUser.getPassengerType().equals(PassengerType.INDIVIDUAL)) {
            if (bookingRequest.bookingListTicketList.length + previousTicketCount > 5) {
                throw new TicketListOverflowException("Bireysel kullanıcı bir sefer için en fazla 5 bilet alabilir!");
            }
            int maleCount = 0;
            for (TicketRequest ticket : bookingRequest.getBookingListTicketList()) {
                if (ticket.getGender() == Gender.MALE)
                    maleCount++;
                if (maleCount > 2) {
                    throw new MaleTicketLimitException("Bireysel kullanıcı tek rezervazyonda en fazla 2 erkek bileti alabilir.");
                }
            }
        }
        if (foundUser.getPassengerType().equals(PassengerType.CORPORATE)) {
            if (bookingRequest.bookingListTicketList.length + previousTicketCount > 5) {
                throw new TicketListOverflowException("Kurumsal kullanıcı bir sefer için en fazla 20 bilet alabilir!");
            }
        }
    }

    private static void checkVoyageHasAvailableSeats(Voyage foundVoyage, BookingRequest bookingRequest) {
        if (foundVoyage.getAvailableSeats() < bookingRequest.getBookingListTicketList().length) {
            throw new TicketListOverflowException("Seferde yeterli yer bulunmamaktadır.");
        }
    }

    @Override
    public BookingResponse create(BookingRequest bookingRequest) {

        checkIfBookingIsValid(bookingRequest);

        PassengerUser foundUser = findUser(bookingRequest);

        Voyage foundVoyage = findVoyage(bookingRequest);

        checkVoyageHasAvailableSeats(foundVoyage, bookingRequest);

        checkPassengerIsAvailableForBooking(bookingRequest, foundUser, foundVoyage);

        Booking booking = new Booking();

        prepareBooking(bookingRequest, foundUser, foundVoyage, booking);

        bookingRepository.save(booking);

        return prepareBookingResponse(booking);
    }

    private Voyage findVoyage(BookingRequest bookingRequest) {
        Voyage foundVoyage = voyageRepository
                .findVoyages(bookingRequest.getFromCity(), bookingRequest.getToCity(), bookingRequest.getTravelType().toString(), CustomDateTimeConverter.convert(bookingRequest.getVoyageDateTime()))
                .orElseThrow(() -> new VoyageNotFoundException("Sefer işlem için uygun değil")).get(0);
        return foundVoyage;
    }

    private PassengerUser findUser(BookingRequest bookingRequest) {
        PassengerUser foundUser = (PassengerUser) userRepository
                .findByEmailIgnoreCase(bookingRequest.getPassengerUserEmail())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));
        return foundUser;
    }

    private BookingResponse prepareBookingResponse(Booking booking) {
        BookingResponse bookingResponse = modelMapper.map(booking, BookingResponse.class);
        bookingResponse.setFromCity(booking.getTicketList().get(0).getVoyage().getFromCity());
        bookingResponse.setToCity(booking.getTicketList().get(0).getVoyage().getToCity());
        bookingResponse.setTravelType(booking.getTicketList().get(0).getVoyage().getTravelType());
        bookingResponse.setVoyageDateTime(booking.getTicketList().get(0).getVoyage().getVoyageDateTime().toString().replace('T', ' '));
        return bookingResponse;
    }

    private void prepareBooking(BookingRequest bookingRequest, PassengerUser foundUser, Voyage foundVoyages, Booking booking) {
        booking.setPassengerUser(foundUser);
        booking.setTicketList(Arrays.stream(bookingRequest.getBookingListTicketList()).map(ticketRequest -> modelMapper.map(ticketRequest, Ticket.class)).toList());
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

        return modelMapper.map(booking, BookingResponse.class);
    }


}
