package com.hadi;

import com.hadi.hotel.Address;
import com.hadi.hotel.Hotel;
import com.hadi.hotel.HotelRepository;
import com.hadi.hotel.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootMongodbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(HotelRepository hotelRepository) {
        Hotel kamelia = new Hotel(
                "Kamelia",
                130,
                new Address("Sabzevar", "Iran"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )
        );

        Hotel homa = new Hotel(
                "Homa",
                90,
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )
        );

        Hotel sofitel = new Hotel(
                "Sofitel",
                200,
                new Address("Rome", "Italia"),
                new ArrayList<>()
        );

        // drop all hotels
        hotelRepository.deleteAll();

        // add all hotels to DB
        return args -> {
            Stream.of(kamelia, homa, sofitel).forEach(hotelRepository::save);
        };
    }
}
