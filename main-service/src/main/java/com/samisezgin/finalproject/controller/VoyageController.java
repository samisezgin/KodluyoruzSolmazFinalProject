package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.VoyageRequest;
import com.samisezgin.finalproject.dto.response.VoyageResponse;
import com.samisezgin.finalproject.model.enums.TravelType;
import com.samisezgin.finalproject.service.VoyageService;
import com.samisezgin.finalproject.util.CustomDateTimeConverter;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

@RestController
@RequestMapping("/voyages")
public class VoyageController {

    private final VoyageService voyageService;

    public VoyageController(VoyageService voyageService) {
        this.voyageService = voyageService;
    }


    @PostMapping
    public VoyageResponse create(@RequestBody VoyageRequest voyageRequest) {
        LoggerUtil.getLogger().log(Level.INFO, "VoyageController POST request -> createVoyage :" + voyageRequest.getFromCity()+"->"+voyageRequest.getToCity());
        return voyageService.create(voyageRequest);
    }

    @PutMapping("/{id}")
    public VoyageResponse deactivate(@PathVariable Integer id) {
        LoggerUtil.getLogger().log(Level.INFO, "VoyageController PUT request -> deactivateVoyage : voyageId -> " + id);
        return voyageService.deactivate(id);
    }

    @DeleteMapping("/{id}")
    public VoyageResponse delete(@PathVariable Integer id) {
        LoggerUtil.getLogger().log(Level.INFO, "VoyageController DELETE request -> deleteVoyage : voyageId -> " + id);
        return voyageService.delete(id);
    }

    @GetMapping("/getSoldTicketCount/{id}")
    public Integer getSoldTicketCount(@PathVariable Integer id) {
        return voyageService.getSoldTicketCount(id);
    }

    @GetMapping("/revenue/{id}")
    public Double getTotalRevenue(@PathVariable Integer id) {
        return voyageService.getTotalRevenue(id);
    }

    @GetMapping
    public List<VoyageResponse> getByCityOrTravelTypeOrDate(@RequestParam String fromCity, @RequestParam String toCity, @RequestParam(required = false) TravelType travelType, @RequestParam(required = false) String voyageDateTimeStr) {

        String travelTypeStr = Objects.nonNull(travelType) ? travelType.toString() : null;

        LocalDateTime voyageDateTime = Objects.nonNull(voyageDateTimeStr) ? CustomDateTimeConverter.convert(voyageDateTimeStr) : null;

        return voyageService.getByCityOrTravelTypeOrDate(fromCity, toCity, travelTypeStr, voyageDateTime);
    }

}
