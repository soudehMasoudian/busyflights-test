package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.util.List;

public interface CrazyAirService {

    List<CrazyAirResponse> getCrazyAirFares(String departureAirportCode, String destinationAirportCode ,
                                            int numberOfPassengers, String departureDate, String arrivalDate);
}
