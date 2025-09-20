package com.example.demo.Service;

import com.example.demo.Models.Customer;

import com.example.demo.Models.History;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.SegmentRepository;
import com.example.demo.Repository.HistoryRepository;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GiftService {
    private final CustomerRepository customerRepository;
    private final SegmentRepository segmentRepository;
    private final HistoryRepository historyRepository;

    // Constructor Injection
    public GiftService(CustomerRepository customerRepository, SegmentRepository segmentRepository, HistoryRepository historyRepository) {
        this.customerRepository = customerRepository;
        this.segmentRepository = segmentRepository;
        this.historyRepository = historyRepository;
    }

    public List<String> getGiftsByDial(long msisdn) {

       Optional <Customer> customer = customerRepository.getCustomerByMsisdn(msisdn);

        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found with dial: " + msisdn);
        }


        Integer segmentId = customer.get().getSegmentId();


        return segmentRepository.getGiftNamesBySegmentId(segmentId);
    }



    public String useGift(long msisdn, Integer giftId) {

        Customer customer = customerRepository.getCustomerByMsisdn(msisdn)
                .orElseThrow(() -> new RuntimeException("Customer not found with dial: " + msisdn));


        List<String> availableGifts = segmentRepository.getGiftNamesBySegmentId(customer.getSegmentId());
        if (availableGifts.isEmpty()) {
            throw new IllegalArgumentException("No gifts available for your segment.");
        }


        if (historyRepository.existsByMsisdnAndGiftId(msisdn, giftId)) {
            throw new IllegalArgumentException("Gift already used before.");
        }


        History history = new History(null, msisdn, giftId, LocalDateTime.now());
        historyRepository.save(history);

        return "Gift with ID " + giftId + " used successfully at " + history.getUsedAt();
    }
}



