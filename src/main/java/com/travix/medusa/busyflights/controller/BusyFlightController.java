package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.CrazyAirService;
import com.travix.medusa.busyflights.service.ToughJetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/busyFlight")
public class BusyFlightController {

    public static final Logger logger = LoggerFactory.getLogger(BusyFlightController.class);

    @Autowired
    CrazyAirService crazyAirService;

    @Autowired
    ToughJetService toughJetService;

    @RequestMapping(value = "/search/{from}/{to}/{departureDate}/{returnDate}/{numberOfPassangers}", method = RequestMethod.GET)
    public ResponseEntity<List<BusyFlightsResponse>> searchFlights(@PathVariable("from") String from, @PathVariable("to") String to,
                                                                   @PathVariable("departureDate") String departureDate, @PathVariable("returnDate") String returnDate,
																@PathVariable("numberOfPassangers") int numberOfPassangers) {
        if (numberOfPassangers > 4) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CrazyAirResponse> crazyAirResponseList = crazyAirService.getCrazyAirFares(from, to, numberOfPassangers,
                departureDate, returnDate);
        List<ToughJetResponse> toughJetResponseList = toughJetService.getToughJetResponses(from, to, departureDate,
                returnDate, numberOfPassangers);
        if (crazyAirResponseList.isEmpty() && toughJetResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ArrayList<BusyFlightsResponse> busyFlightsResponseList = new ArrayList<>();

        try {
            for (CrazyAirResponse crazyAirResponse : crazyAirResponseList) {
                BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
                busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
                busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
                busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
                busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
                busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
                busyFlightsResponse.setPrice(crazyAirResponse.getPrice());
                busyFlightsResponse.setSupplier("CrazyAir");
                busyFlightsResponseList.add(busyFlightsResponse);
            }
        } catch (Exception e) {
            /* we can add two fields which named isSucceed and errorDescription
            and set them with true and error description in case of every flight has error
            we should return another type in return which our list is a part of it and the
            other part is mentioned fields.
             */
            logger.error(e.getMessage());
        }
        try {
            for (ToughJetResponse toughJetResponse : toughJetResponseList) {
                BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
                busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
                busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime());
                busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
                busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
                busyFlightsResponse.setPrice(toughJetResponse.getBasePrice());
                busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime());
                busyFlightsResponse.setSupplier("ToughJetAir");
                busyFlightsResponseList.add(busyFlightsResponse);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        Collections.sort(busyFlightsResponseList);
        return new ResponseEntity<>(busyFlightsResponseList, HttpStatus.OK);
    }
}
