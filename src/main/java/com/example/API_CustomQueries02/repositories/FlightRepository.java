package com.example.API_CustomQueries02.repositories;

import com.example.API_CustomQueries02.entities.Flight;
import com.example.API_CustomQueries02.entities.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "repo-flight")
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByStatus(FlightStatus status);

    @Query(value = "SELECT u FROM Flight u WHERE u.status = ?1 OR u.status = ?2")
    List<Flight> getCustomFlight(FlightStatus p1, FlightStatus p2);
}
