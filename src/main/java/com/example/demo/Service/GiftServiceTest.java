package com.example.demo.Service;

import com.example.demo.Models.Customer;
import com.example.demo.Models.History;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.HistoryRepository;
import com.example.demo.Repository.SegmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GiftServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SegmentRepository segmentRepository;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private GiftService giftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetGiftsByDial_Success() {
        long msisdn = 123123123L;
        Customer customer = new Customer(3, msisdn, 3);

        when(customerRepository.getCustomerByMsisdn(msisdn)).thenReturn(Optional.of(customer));
        when(segmentRepository.getGiftNamesBySegmentId(3)).thenReturn(Arrays.asList("Free Internet Bundles", "Special Offers"));

        List<String> gifts = giftService.getGiftsByDial(msisdn);

        assertEquals(2, gifts.size());
        assertTrue(gifts.contains("Free Internet Bundles"));
        verify(customerRepository, times(1)).getCustomerByMsisdn(msisdn);
        verify(segmentRepository, times(1)).getGiftNamesBySegmentId(3);
    }

    @Test
    void testGetGiftsByDial_CustomerNotFound() {
        long msisdn = 12345L;
        when(customerRepository.getCustomerByMsisdn(msisdn)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> giftService.getGiftsByDial(msisdn));

        assertEquals("Customer not found with dial: " + msisdn, exception.getMessage());
        verify(customerRepository, times(1)).getCustomerByMsisdn(msisdn);
    }


    @Test
    void testUseGift_Success() {
        long msisdn = 123123123L;
        int giftId = 3;
        Customer customer = new Customer(1, msisdn, 10);

        when(customerRepository.getCustomerByMsisdn(msisdn)).thenReturn(Optional.of(customer));
        when(segmentRepository.getGiftNamesBySegmentId(10)).thenReturn(Arrays.asList("Gift1", "Gift2"));
        when(historyRepository.existsByMsisdnAndGiftId(msisdn, giftId)).thenReturn(false);

        String result = giftService.useGift(msisdn, giftId);

        assertTrue(result.contains("Gift with ID " + giftId + " used successfully"));
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    void testUseGift_CustomerNotFound() {
        long msisdn = 12345L;
        int giftId = 1;

        when(customerRepository.getCustomerByMsisdn(msisdn)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> giftService.useGift(msisdn, giftId));

        assertEquals("Customer not found with dial: " + msisdn, exception.getMessage());
    }

    @Test
    void testUseGift_NoGiftsAvailable() {
        long msisdn = 12345L;
        int giftId = 1;
        Customer customer = new Customer(1, msisdn, 10);

        when(customerRepository.getCustomerByMsisdn(msisdn)).thenReturn(Optional.of(customer));
        when(segmentRepository.getGiftNamesBySegmentId(10)).thenReturn(List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> giftService.useGift(msisdn, giftId));

        assertEquals("No gifts available for your segment.", exception.getMessage());
    }

    @Test
    void testUseGift_GiftAlreadyUsed() {
        long msisdn = 12345L;
        int giftId = 1;
        Customer customer = new Customer(1, msisdn, 10);

        when(customerRepository.getCustomerByMsisdn(msisdn)).thenReturn(Optional.of(customer));
        when(segmentRepository.getGiftNamesBySegmentId(10)).thenReturn(Arrays.asList("Gift1", "Gift2"));
        when(historyRepository.existsByMsisdnAndGiftId(msisdn, giftId)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> giftService.useGift(msisdn, giftId));

        assertEquals("Gift already used before.", exception.getMessage());
    }
}
