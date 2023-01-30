package com.samisezgin.finalproject.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.model.enums.VoyageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "voyages")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    private Integer availableSeats;

    private Double price;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime voyageDateTime;

    @Enumerated(EnumType.STRING)
    private TravelType travelType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "voyage", cascade = CascadeType.ALL)
    private List<Ticket> ticketList;

    @Enumerated(EnumType.STRING)
    private VoyageStatus voyageStatus;

    public Voyage() {
        voyageStatus = VoyageStatus.ACTIVE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getVoyageDateTime() {
        return voyageDateTime;
    }

    public void setVoyageDateTime(LocalDateTime voyageDateTime) {
        this.voyageDateTime = voyageDateTime;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public VoyageStatus getVoyageStatus() {
        return voyageStatus;
    }

    public void setVoyageStatus(VoyageStatus voyageStatus) {
        this.voyageStatus = voyageStatus;
    }


}
