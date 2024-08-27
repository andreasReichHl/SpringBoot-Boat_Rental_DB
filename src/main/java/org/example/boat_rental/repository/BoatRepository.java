package org.example.boat_rental.repository;

import org.example.boat_rental.entities.Boat;
import org.springframework.data.repository.CrudRepository;

public interface BoatRepository extends CrudRepository<Boat, Long> {
}
