package com.samisezgin.finalproject.repository;

import com.samisezgin.finalproject.model.Voyage;
import com.samisezgin.finalproject.model.enums.TravelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage,Integer> {

    @Query(value = "select * from voyages v where (v.available_seats >0) and (UPPER(v.from_city) =UPPER(:fromCity) and UPPER(v.to_city) = UPPER(:toCity)) and (v.travel_type =coalesce(:travelType,'PLANE') or v.travel_type =coalesce(:travelType,'BUS')) and v.voyage_status = 'ACTIVE' and (date(v.voyage_date_time) =date(coalesce(:voyageDateTime,now())))",nativeQuery = true)
    Optional<List<Voyage>> findVoyages(@Param("fromCity") String fromCity, @Param("toCity") String toCity, @Param("travelType") String travelType, @Param("voyageDateTime") LocalDateTime voyageDatetime);

    Optional<Voyage> findVoyageByFromCityAndToCityAllIgnoreCaseAndVoyageDateTimeAndTravelType(String fromCity, String toCity, LocalDateTime voyageDateTime, TravelType travelType);


}
