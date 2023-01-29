package com.samisezgin.finalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.service.VoyageService;
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
@WebMvcTest(VoyageController.class)
class VoyageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VoyageService voyageService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    void create() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/voyages")
                        .content(asJsonString(new VoyageRequest("ankara", "istanbul", "2023-01-29 21:00", TravelType.BUS, 23.0)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void getTotalRevenueByIdAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/voyages/revenue/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    void deactivateVoyageAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/voyages/revenue/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void deleteVoyageAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/voyages/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void getTotalTicketNumbersSoldAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/voyages/getSoldTicketCount/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    void getByCityOrTravelTypeOrDate() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/voyages/revenue/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("fromCity","ankara")
                        .param("toCity","istanbul")
                        .param("travelType", String.valueOf(TravelType.BUS))
                        .param("voyageDateTimeStr","2023-01-29 21:00"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}