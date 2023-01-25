package com.samisezgin.finalproject.dto.request;

import com.samisezgin.finalproject.model.enums.TravelType;

public class VoyageRequest {

    private String fromCity;

    private String toCity;

    private String voyageDateTime;
    private TravelType travelType;
    private Double price;

    public VoyageRequest() {
    }

    public VoyageRequest(String fromCity, String toCity, String voyageDateTime, TravelType travelType, Double price) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.voyageDateTime = voyageDateTime;
        this.travelType = travelType;
        this.price = price;
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
    public String getVoyageDateTime() {
        return voyageDateTime;
    }

    public void setVoyageDateTime(String voyageDateTime) {
        this.voyageDateTime = voyageDateTime;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
