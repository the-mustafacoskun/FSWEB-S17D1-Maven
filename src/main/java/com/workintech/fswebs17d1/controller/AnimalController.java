package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech")
public class AnimalController {
    Map<Integer, Animal> animals;

    @PostConstruct
    public void init(){
        animals = new HashMap<Integer, Animal>();
        animals.put(1,new Animal(1,"Monkey"));
        animals.put(2,new Animal(2,"Dog"));
        animals.put(3,new Animal(3,"Cat"));
        System.out.println("AnimalController init");
    }
    @GetMapping(value = "/animal")
    public List<Animal> getAnimals() {
        return this.animals.values().stream().toList();
    }
    @GetMapping("/animal/{id}")
    public Animal returnAnimalById(@PathVariable int id) {
        if(id<0 || id>this.animals.size()) {

            System.out.println("AnimalController getAnimal: id out of bounds");
            return null;
        }

        return this.animals.get(id);
    }

    @PostMapping("/animal")
    public Animal addAnimal(@RequestBody Animal animal) {
        this.animals.put(animal.getId(), animal);
        return animal;
    }

    @PutMapping("/animal/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal) {
        return this.animals.put(id, animal);
    }

    @DeleteMapping("/animal/{id}")
    public void deleteAnimal(@PathVariable int id) {
        this.animals.remove(id);
        System.out.println("AnimalController deleteAnimal:"+animals.get(id));
    }

}
