package com.hadi.hotel;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private MongoOperations mongoTemplate;

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository, MongoTemplate mongoTemplate) {
        this.hotelRepository = hotelRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping()
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelRepository.insert(hotel);
    }

    @PutMapping
    public Hotel update(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        hotelRepository.delete(id);
    }

    @GetMapping("/{id}")
    public Hotel findById(@PathVariable("id") String id) {
        return hotelRepository.findById(id);
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> findByPricePerNightLessThan(@PathVariable("maxPrice") int maxPrice) {
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city) {
        return hotelRepository.findByCity(city);
    }
}