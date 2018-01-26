package com.hadi.hotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>
        , PagingAndSortingRepository<Hotel, String> {

    Hotel findById(String id);
    Hotel findByName(String hotelName);

    List<Hotel> findByPricePerNightLessThan(int max);

    @Query("{address.city : ?0}")
    List<Hotel> findByCity(String city);
} 