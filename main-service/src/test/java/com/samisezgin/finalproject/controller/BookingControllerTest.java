package com.samisezgin.finalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samisezgin.finalproject.client.PaymentServiceClient;
import com.samisezgin.finalproject.dto.request.BookingRequest;
import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.model.Invoice;
import com.samisezgin.finalproject.model.PaymentRequest;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingServiceImpl bookingService;

    @MockBean
    private PaymentServiceClient paymentServiceClient;

    @MockBean
    private Invoice invoice;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void getBookingByIdAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/bookings/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void createBookingAPI() throws Exception {
        TicketRequest[] ticketRequests = new TicketRequest[1];
        ticketRequests[0] = new TicketRequest("14912345", "Sami", "Sezgin", Gender.MALE);

        mvc.perform(MockMvcRequestBuilders
                        .post("/bookings")
                        .content(asJsonString(new BookingRequest("mail@mail.com",
                                ticketRequests,
                                "ankara", "izmir", "2023-10-17 19:00", TravelType.PLANE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void paymentAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .post("/bookings/payment")
                        .content(asJsonString(new PaymentRequest(1,"EFT")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}