package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.dto.response.VoyageResponse;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.model.enums.VoyageStatus;
import com.samisezgin.finalproject.repository.VoyageRepository;
import com.samisezgin.finalproject.service.VoyageService;
import com.samisezgin.finalproject.util.CustomDateTimeConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class VoyageServiceImplTest {
    @InjectMocks
    VoyageServiceImpl voyageService;

    @Mock
    VoyageRepository voyageRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void testCreate() {
        // Setup mock objects
        VoyageRequest voyageRequest = new VoyageRequest();
        voyageRequest.setFromCity("ankara");
        voyageRequest.setToCity("istanbul");
        voyageRequest.setPrice(100.0);
        voyageRequest.setVoyageDateTime("2023-01-01 21:00");
        voyageRequest.setTravelType(TravelType.BUS);

        Voyage voyage = new Voyage();
        voyage.setFromCity("ankara");
        voyage.setToCity("istanbul");
        voyage.setPrice(100.0);
        voyage.setVoyageDateTime(LocalDateTime.parse("2023-01-01T21:00:00"));
        voyage.setTravelType(TravelType.BUS);

        VoyageResponse voyageResponse = new VoyageResponse();
        voyageResponse.setFromCity("ankara");
        voyageResponse.setToCity("istanbul");
        voyageResponse.setPrice(100.0);
        voyageResponse.setVoyageDateTime("2023-01-01 21:00");

        voyage.setTravelType(TravelType.BUS);
        ModelMapper modelMapper = mock(ModelMapper.class);
        VoyageRepository voyageRepository = mock(VoyageRepository.class);

        // Define mock behavior
        when(modelMapper.map(voyageRequest, Voyage.class)).thenReturn(voyage);
        when(modelMapper.map(voyage, VoyageResponse.class)).thenReturn(voyageResponse);

        // Initialize class under test
        VoyageServiceImpl voyageService = new VoyageServiceImpl(voyageRepository, modelMapper);

        // Call method under test
        VoyageResponse result = voyageService.create(voyageRequest);

        // Verify results
        verify(modelMapper).map(voyageRequest, Voyage.class);
        verify(voyageRepository).save(voyage);
         assertEquals(result, voyageResponse);
    }

    @Test
    public void deactivateVoyage_validId() {
        // given
        Integer id = 1;
        String originCity="ankara";
        Voyage voyage = new Voyage();
        voyage.setId(id);
        voyage.setFromCity(originCity);
        voyage.setVoyageStatus(VoyageStatus.ACTIVE);
        when(voyageRepository.findById(id)).thenReturn(Optional.of(voyage));
        voyageService.deactivate(1);
        // then
        assertEquals(VoyageStatus.PASSIVE, voyage.getVoyageStatus());
    }

    @Test
    public void deleteVoyage_validId() {
        // given
        Integer id = 1;
        Voyage voyage = new Voyage();
        voyage.setId(id);
        voyage.setFromCity("ankara");

        VoyageResponse voyageResponse = new VoyageResponse();
        voyageResponse.setFromCity("ankara");

        when(voyageRepository.findById(id)).thenReturn(Optional.of(voyage));
        when(modelMapper.map(voyage,VoyageResponse.class)).thenReturn(voyageResponse);

        VoyageResponse voyageResponse1 = voyageService.delete(id);
        // then
        assertEquals(voyageResponse1.getFromCity(),voyage.getFromCity());
    }

    @Test
    public void getTotalTicketNumbersSold_validId_returnsCorrectNumber() {
        // given
        Integer id = 1;
        Voyage voyage = new Voyage();
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        voyage.setTicketList(ticketList);
        when(voyageRepository.findById(id)).thenReturn(Optional.of(voyage));

        // when
        int totalTicketNumbersSold = voyageService.getSoldTicketCount(id);

        // then
        assertEquals(2, totalTicketNumbersSold);
    }

    @Test
    public void getVoyagesByCityTravelTypeDateTime_validInput_returnsCorrectList() {
        // given
        String originCity = "New York";
        String destinationCity = "Los Angeles";
        TravelType travelType = TravelType.BUS;
        String voyageDateTime = "2022-01-01";

        Voyage voyage1 = new Voyage();
        voyage1.setFromCity(originCity);
        voyage1.setToCity(destinationCity);
        voyage1.setTravelType(travelType);
        voyage1.setVoyageDateTime(CustomDateTimeConverter.convert(voyageDateTime));
        Voyage voyage2 = new Voyage();
        voyage2.setFromCity(originCity);
        voyage2.setToCity(destinationCity);
        voyage2.setTravelType(travelType);
        voyage2.setVoyageDateTime(CustomDateTimeConverter.convert(voyageDateTime));

        List<Voyage> voyageList = new ArrayList<>();
        voyageList.add(voyage1);
        voyageList.add(voyage2);
        when(voyageRepository.findVoyages(originCity, destinationCity, String.valueOf(TravelType.BUS),
                CustomDateTimeConverter.convert(voyageDateTime))).thenReturn(Optional.of(voyageList));

        VoyageResponse voyageResponse1 = new VoyageResponse();
        voyageResponse1.setFromCity(originCity);
        voyageResponse1.setToCity(destinationCity);
        voyageResponse1.setTravelType(travelType);
        voyageResponse1.setVoyageDateTime(voyageDateTime);
        VoyageResponse voyageResponse2 = new VoyageResponse();
        voyageResponse2.setFromCity(originCity);
        voyageResponse2.setToCity(destinationCity);
        voyageResponse2.setTravelType(travelType);
        voyageResponse2.setVoyageDateTime(voyageDateTime);

        List<VoyageResponse> expectedVoyageResponseList = new ArrayList<>();
        expectedVoyageResponseList.add(voyageResponse1);
        expectedVoyageResponseList.add(voyageResponse2);
        when(modelMapper.map(voyage1, VoyageResponse.class)).thenReturn(voyageResponse1);
        when(modelMapper.map(voyage2, VoyageResponse.class)).thenReturn(voyageResponse2);

        // when
        List<VoyageResponse> actualVoyageResponseList = voyageService.getByCityOrTravelTypeOrDate(originCity, destinationCity, "BUS", CustomDateTimeConverter.convert(voyageDateTime));

        // then
        assertEquals(expectedVoyageResponseList, actualVoyageResponseList);
    }

}