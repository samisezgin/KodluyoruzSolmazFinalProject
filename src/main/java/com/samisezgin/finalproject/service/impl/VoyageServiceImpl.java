package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.dto.response.VoyageResponse;
import com.samisezgin.finalproject.exceptions.VoyageNotFoundException;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.model.enums.VoyageStatus;
import com.samisezgin.finalproject.repository.VoyageRepository;
import com.samisezgin.finalproject.service.VoyageService;
import com.samisezgin.finalproject.util.Constants;
import com.samisezgin.finalproject.util.CustomDateTimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoyageServiceImpl implements VoyageService {

    private final VoyageRepository voyageRepository;
    private final ModelMapper modelMapper;

    public VoyageServiceImpl(VoyageRepository voyageRepository, ModelMapper modelMapper) {

        this.voyageRepository = voyageRepository;
        this.modelMapper = modelMapper;
    }

    private static void setVoyageCapacity(Voyage voyage) {
        if (voyage.getTravelType().equals(TravelType.BUS)) {
            voyage.setAvailableSeats(Constants.MAX_AVAILABLE_SEATS_FOR_BUS);
        } else {
            voyage.setAvailableSeats(Constants.MAX_AVAILABLE_SEATS_FOR_PLANE);
        }
    }

    @Override
    public VoyageResponse create(VoyageRequest voyageRequest) {
        Voyage voyage = modelMapper.map(voyageRequest, Voyage.class);
        setVoyageCapacity(voyage);
        voyageRepository.save(voyage);

        return modelMapper.map(voyage, VoyageResponse.class);
    }

    @Override
    public VoyageResponse deactivate(Integer id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException("İlgili sefer bulunamadı!"));
        voyage.setVoyageStatus(VoyageStatus.PASSIVE);
        return modelMapper.map(voyage, VoyageResponse.class);
    }

    @Override
    public VoyageResponse delete(Integer id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException("İlgili sefer bulunamadı!"));
        voyageRepository.delete(voyage);
        return modelMapper.map(voyage, VoyageResponse.class);
    }

    @Override
    public Integer getSoldTicketCount(Integer id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException("İlgili sefer bulunamadı!"));
        return voyage.getTicketList().size();
    }

    @Override
    public Double getTotalRevenue(Integer id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException("İlgili sefer bulunamadı!"));
        double price = 0;

        for (Ticket ticket : voyage.getTicketList()) {
            price += ticket.getPrice();
        }
        return price;
    }

    @Override
    public List<VoyageResponse> getByCityOrTravelTypeOrDate(String from, String to, String travelType, LocalDateTime dateTime) {

        List<Voyage> voyageList = voyageRepository.findVoyages(from,to, travelType,dateTime).orElseThrow(()->new VoyageNotFoundException("Aranan kriterlerde sefer bulunamadı"));

        List<VoyageResponse> voyageResponseList = new ArrayList<>();

        for (Voyage voyage : voyageList) {
            voyageResponseList.add(modelMapper.map(voyage, VoyageResponse.class));

        }
        return voyageResponseList;
    }

    @Override
    public Voyage findVoyage(BookingRequest bookingRequest) {
        return voyageRepository.findVoyageByFromCityAndToCityAllIgnoreCaseAndVoyageDateTimeAndTravelType(bookingRequest.getFromCity(),bookingRequest.getToCity(), CustomDateTimeConverter.convert(bookingRequest.getVoyageDateTime()),bookingRequest.getTravelType())
                .orElseThrow(()->new VoyageNotFoundException("Sefer işlem için uygun değil!"));
    }


}
