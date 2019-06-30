package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ToughJetServiceImpl implements ToughJetService {

    public List<ToughJetResponse> getToughJetResponses(String from, String to, String outboundDate,
                                                       String inboundDate, int numberOfAdults) {
        List<ToughJetResponse> toughJetResponses = getToughJetResponses();
        List<ToughJetResponse> toughJetResponseList = new ArrayList<>();
        for (ToughJetResponse toughJetResponse : toughJetResponses) {
            if (toughJetResponse.getDepartureAirportName().equalsIgnoreCase(from) &&
            toughJetResponse.getArrivalAirportName().equalsIgnoreCase(to) && toughJetResponse.getOutboundDateTime().equalsIgnoreCase(outboundDate) &&
            toughJetResponse.getInboundDateTime().equalsIgnoreCase(inboundDate) && numberOfAdults > 0) {
                double price = ((toughJetResponse.getBasePrice() + toughJetResponse.getTax()) - (toughJetResponse.getBasePrice() *
                        (toughJetResponse.getDiscount() / 100)))  * numberOfAdults;
                toughJetResponse.setBasePrice(price);
                toughJetResponseList.add(toughJetResponse);
            }
        }
        return toughJetResponseList;
    }

    private static List<ToughJetResponse> getToughJetResponses() {
        List<ToughJetResponse> toughJetResponses = new ArrayList<>();
        toughJetResponses.add(new ToughJetResponse("MAHAN", 200, 20, 20,
                "LHR", "AMS", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        toughJetResponses.add(new ToughJetResponse("ASEMAN", 100, 10, 15,
                "AMS", "LHR", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        toughJetResponses.add(new ToughJetResponse("IRANAIR", 150, 15, 11,
                "LHR", "AMS", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        toughJetResponses.add(new ToughJetResponse("ZAGROS", 170, 17, 9,
                "AMS", "LHR", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        toughJetResponses.add(new ToughJetResponse("ATAAIR", 350, 20, 19,
                "LHR", "AMS", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        toughJetResponses.add(new ToughJetResponse("KISHAIR", 259, 32, 10,
                "AMS", "LHR", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
        toughJetResponses.add(new ToughJetResponse("QESHM AIR", 189, 22, 11,
                "LHR", "AMS", new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                new SimpleDateFormat("dd-MM-yyyy").format(new Date())));

        return toughJetResponses;
    }
}
