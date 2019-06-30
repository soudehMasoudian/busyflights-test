package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CrazyAirServiceImpl implements CrazyAirService {

    @Override
    public List<CrazyAirResponse> getCrazyAirFares(String departureAirportCode, String destinationAirportCode,
                                                   int numberOfPassengers, String departureDate, String arrivalDate) {
        List<CrazyAirResponse> crazyAirResponses = getCrazyAirResponses();
        List<CrazyAirResponse> CrazyAirResponsesList = new ArrayList<>();
        for (CrazyAirResponse crazyAirResponse : crazyAirResponses) {
            if (crazyAirResponse.getDepartureAirportCode().equalsIgnoreCase(departureAirportCode) &&
            crazyAirResponse.getDestinationAirportCode().equalsIgnoreCase(destinationAirportCode) &&
            numberOfPassengers > 0 && crazyAirResponse.getDepartureDate().equalsIgnoreCase(departureDate) &&
            crazyAirResponse.getArrivalDate().equalsIgnoreCase(arrivalDate)) {
                double price = numberOfPassengers * crazyAirResponse.getPrice();
                crazyAirResponse.setPrice(price);
                CrazyAirResponsesList.add(crazyAirResponse);
            }
        }
        return CrazyAirResponsesList;
    }

    private static List<CrazyAirResponse> getCrazyAirResponses() {
        List<CrazyAirResponse> crazyAirResponseList = new ArrayList<>();
        crazyAirResponseList.add(new CrazyAirResponse("IRAN", 20, "C", "LHR",
                "AMS", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        crazyAirResponseList.add(new CrazyAirResponse("IRAQ", 80, "A", "AMS",
                "LHR", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        crazyAirResponseList.add(new CrazyAirResponse("AF", 80, "D", "LHR",
                "AMS", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        crazyAirResponseList.add(new CrazyAirResponse("PK", 120, "E", "AMS",
                "LHR", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        return crazyAirResponseList;
    }
}
