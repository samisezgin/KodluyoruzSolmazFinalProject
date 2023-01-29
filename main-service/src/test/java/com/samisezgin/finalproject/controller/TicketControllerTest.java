package com.samisezgin.finalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samisezgin.finalproject.dto.request.TicketRequest;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.service.impl.TicketServiceImpl;
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
@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketServiceImpl ticketService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createTicket() {
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void createVoyageAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/tickets/{voyageId}", 1)
                        .content(asJsonString(new TicketRequest("14912345", "Sami", "Sezgin", Gender.MALE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void updateTicketAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/tickets/{ticketId}", 2)
                        .content(asJsonString(new TicketRequest("14912345", "Sami", "Sezgin", Gender.MALE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void getTicketByIdAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/tickets/{ticketId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
    public void getAllByTelephoneNumberAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/tickets/user/{email}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = {"USER", "ADMIN"})
    public void deleteTicketAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/tickets/{id}", 1))
                .andExpect(status().isOk());
    }
}