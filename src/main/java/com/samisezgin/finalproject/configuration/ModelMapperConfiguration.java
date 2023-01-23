package com.samisezgin.finalproject.configuration;

import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.dto.response.BookingResponse;
import com.samisezgin.finalproject.dto.response.VoyageResponse;
import com.samisezgin.finalproject.model.Booking;
import com.samisezgin.finalproject.model.Ticket;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.util.PasswordUtil;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Configuration
public class ModelMapperConfiguration {
    private final ModelMapper modelMapper=new ModelMapper();

    @Bean
    public ModelMapper getModelMapper(){

        stringToLocalDateTimeConverter();
        localDateTimeToStringConverter();
        bookingToBookingResponseConverter();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

    private void localDateTimeToStringConverter() {
        Converter<LocalDateTime, String> toString = new AbstractConverter<LocalDateTime, String>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            @Override
            protected String convert(LocalDateTime source) {
                return source.format(formatter);
            }
        };
        modelMapper.addConverter(toString);
        modelMapper.createTypeMap(Voyage.class, VoyageResponse.class)
                .addMappings(mapper -> mapper.using(toString)
                        .map(Voyage::getVoyageDateTime, VoyageResponse::setVoyageDateTime));
    }

    private void stringToLocalDateTimeConverter() {
        Converter<String, LocalDateTime> toLocalDateTime = new AbstractConverter<String, LocalDateTime>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            @Override
            protected LocalDateTime convert(String source) {
                return LocalDateTime.parse(source, formatter);
            }
        };
        modelMapper.addConverter(toLocalDateTime);

        modelMapper.createTypeMap(VoyageRequest.class, Voyage.class)
                .addMappings(mapper -> mapper.using(toLocalDateTime)
                        .map(VoyageRequest::getVoyageDateTime, Voyage::setVoyageDateTime));
    }

    private void bookingToBookingResponseConverter()
    {
        TypeMap<Booking, BookingResponse> typeMap = modelMapper.createTypeMap(Booking.class, BookingResponse.class);
        typeMap.addMappings(mapper -> mapper.map(booking -> booking.getPassengerUser().getEmail(), BookingResponse::setUserEmail));

    }

}