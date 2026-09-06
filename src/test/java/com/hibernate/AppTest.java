package com.hibernate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import com.hibernate.service.BookingService;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        
    }

    @Test
    public void testPuneCentresListHasSixCentres()
    {
        assertEquals(6, BookingService.PUNE_CENTRES.size());

        List<String> expectedNames = List.of(
                "Shri Chhatrapati Shivaji Maharaj Market Yard, Gultekdi",
                "Manjari Sub-Market Yard",
                "Shri Nageshwar Maharaj Sub-Market Yard, Moshi",
                "Pimpri Sub-Market Yard",
                "Khadki Sub-Market Yard",
                "Uttamnagar Sub-Market Yard"
        );

        List<String> expectedLocations = List.of(
                "Gultekdi, Pune",
                "Manjari, Pune",
                "Moshi, Pune",
                "Pimpri, Pune",
                "Khadki, Pune",
                "Uttamnagar, Pune"
        );

        for (int i = 0; i < expectedNames.size(); i++) {
            assertEquals(expectedNames.get(i), BookingService.PUNE_CENTRES.get(i).getName());
            assertEquals(expectedLocations.get(i), BookingService.PUNE_CENTRES.get(i).getLocation());
            assertFalse(BookingService.PUNE_CENTRES.get(i).getName().contains("North"), "Should not contain North");
            assertFalse(BookingService.PUNE_CENTRES.get(i).getName().contains("South"), "Should not contain South");
        }
    }
}
