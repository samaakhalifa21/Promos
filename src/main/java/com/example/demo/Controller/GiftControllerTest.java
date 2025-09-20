package com.example.demo.Controller;

import com.example.demo.Service.GiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GiftControllerTest {

    @Mock
    private GiftService giftService;

    @InjectMocks
    private GiftController giftController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllGiftsAvailable_success() {
        // Arrange
        Long msisdn = 123123123L;
        List<String> mockGifts = Arrays.asList("Free Internet Bundles", "Special Offers");
        when(giftService.getGiftsByDial(msisdn)).thenReturn(mockGifts);

        // Act
        List<String> result = giftController.AllGiftsAvailable(Optional.of(msisdn));

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains("Free Internet Bundles"));
        verify(giftService, times(1)).getGiftsByDial(msisdn);
    }

    @Test
    void testAllGiftsAvailable_missingMsisdn() {
        // Act + Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> giftController.AllGiftsAvailable(Optional.empty())
        );

        assertEquals(" msisdn is required .", exception.getMessage());
        verify(giftService, never()).getGiftsByDial(anyLong());
    }

    @Test
    void testUseGift_success() {
        // Arrange
        Long msisdn = 123123123L;
        Integer giftId = 103;
        String expectedResponse = "Gift used successfully";
        when(giftService.useGift(msisdn, giftId)).thenReturn(expectedResponse);

        // Act
        String result = giftController.useGift(msisdn, giftId);

        // Assert
        assertEquals(expectedResponse, result);
        verify(giftService, times(1)).useGift(msisdn, giftId);
    }
}
