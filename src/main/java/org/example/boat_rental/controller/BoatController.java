package org.example.boat_rental.controller;

import org.example.boat_rental.entities.Boat;
import org.example.boat_rental.service.Boatservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/boat")
public class BoatController {

    Boatservice boatservice;

    public BoatController(Boatservice boatservice) {
        this.boatservice = boatservice;
    }

    @GetMapping
    public List<Boat> getAllBoats(){
        return boatservice.getAllBoats();
    }

    @GetMapping("/{id}")
    public Boat getBoat(@PathVariable Long id){
        return boatservice.getBoat(id);
    }

    @GetMapping("count")
    public String getNumbersOfBoats(){
        return "Es sind " + boatservice.getNumbersOfBoats() + " Boot(e) in der deiner Datenbank";
    }

    @GetMapping("filter")
    public List<Boat> filterByName(@RequestParam String filtername){
        return boatservice.filterByName(filtername);
    }

    @PostMapping
    public Boat createBoat(@RequestBody Boat boat){
        return boatservice.createBoat(boat);
    }

    @DeleteMapping("/{id}")
    public void deleteBoat(@PathVariable Long id){
        boatservice.deleteBoat(id);
    }

    @PutMapping("update/{id}")
    public Boat updateBoat(@PathVariable Long id, @RequestBody Boat boat){
        return boatservice.updateBoat(id, boat);
    }
}
