package com.learning.bookingservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class IdGenerator {
    public static String generateBookingId(String trainNumber, LocalDateTime departureTime) {
        String uuid = UUID.randomUUID().toString();

        String uuidPrefix = uuid.substring(0, 8);

        String departureTimeSuffix = departureTime.toString().substring(departureTime.toString().length() - 4);

        return "BID" + uuidPrefix + trainNumber + departureTimeSuffix;
    }

    public static String generatePNR() {
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        // Generate a random 6-digit number
        Random random = new Random();
        int randomValue = random.nextInt(1000000); // 6 digits
        String randomString = String.format("%06d", randomValue);

        // Concatenate the date/time string and random number to form the PNR number
        String pnrNumber = dateTimeString + randomString;

        // Trim to 10 digits if longer (in case random value exceeded 6 digits)
        if (pnrNumber.length() > 10) {
            pnrNumber = pnrNumber.substring(0, 10);
        }

        return pnrNumber;
    }

}
