package com.hadi;

import com.hadi.hotel.Address;
import com.hadi.hotel.Hotel;
import com.hadi.hotel.HotelRepository;
import com.hadi.hotel.Review;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMongodbDemoApplicationTests {

    @Autowired
    private HotelRepository repository;

    static final int NUMBER_OF_HOTELS = 3;

    @Before
    public void init() {
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
        repository.deleteAll();

        // add all hotels to DB
        Stream.of(kamelia, homa, sofitel).forEach(repository::save);
    }

    @Test
    public void countAllHotels() {
        List<Hotel> hotels = repository.findAll();
        assertEquals(NUMBER_OF_HOTELS, hotels.size());
    }

    @Test
    public void countOneHotel() {
        Example<Hotel> example = Example.of(new Hotel("Homa", 90));
        assertThat(repository.count(example)).isEqualTo(1L);
    }

    @Test
    public void setsIdOnSave() {
        Hotel bellagio = repository.save(
                new Hotel(
                        "Bellagio",
                        200,
                        new Address("Las Vegas", "USA"),
                        Collections.singletonList(
                                new Review("Angelina", 10, true)
                        )
                )
        );
        assertThat(bellagio.getId()).isNotNull();
    }

    @Test
    public void findOneHotel() {
        Example<Hotel> example = Example.of(new Hotel("Homa", 90));

        Hotel hotel = repository.findOne(example);
        assertThat(hotel.getName()).isEqualTo("Homa");
    }
}
