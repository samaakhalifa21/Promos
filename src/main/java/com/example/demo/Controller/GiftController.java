package com.example.demo.Controller;

import com.example.demo.Service.GiftService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping("/msisdn")
    public List<String> AllGiftsAvailable(@RequestParam(required = false) Optional<Long> msisdn) {

        Long value = msisdn.orElseThrow(
                () -> new IllegalArgumentException(" msisdn is required .")
        );

        return giftService.getGiftsByDial(msisdn.get());
    }

    @GetMapping ("/use")
    public String useGift(@RequestParam Long msisdn,
                          @RequestParam Integer giftId) {
        return giftService.useGift(msisdn, giftId);
    }
}

