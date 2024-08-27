package org.example.boat_rental.service;

import jakarta.persistence.Entity;
import org.example.boat_rental.entities.Boat;
import org.example.boat_rental.repository.BoatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Boatservice {

    BoatRepository boatRepository;

    public Boatservice(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public List<Boat> getAllBoats() {
        List<Boat> boats = new ArrayList<>();
        boatRepository.findAll().forEach(boats::add);
        return boats;
    }

    public Boat getBoat(long id) {
        return boatRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Boat not found"));
    }

    public Boat createBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    public void deleteBoat(long id) {
        if (boatRepository.existsById(id)) boatRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Boat with ID " + id + " not found");
    }

    public Boat updateBoat(long id, Boat updateBoat) {
        Boat boat = boatRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Boat with ID " + id + " not found"));

        boat.setName(updateBoat.getName());
        boat.setCapacity(updateBoat.getCapacity());
        boat.setCategory(updateBoat.getCategory());

        return boatRepository.save(boat);
    }

    public long getNumbersOfBoats() {
        return boatRepository.count();
    }

    public List<Boat> filterByName(String filterName) {
        List<Boat> allBoats = new ArrayList<>();
        boatRepository.findAll().forEach(allBoats::add);
        return allBoats.stream().filter(boat -> boat.getName().toLowerCase().contains(filterName.toLowerCase())).toList();

    }

}
