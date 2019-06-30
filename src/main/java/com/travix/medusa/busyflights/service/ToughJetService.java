package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

import java.util.List;

public interface ToughJetService {

    List<ToughJetResponse> getToughJetResponses(String from, String to, String outboundDate, String inboundDate,
                                                int numberOfAdults);
}
