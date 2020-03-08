package com.telusko;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlienResource {
	
	@Autowired
	AlienRepository repo;
	
	@PostMapping("/save")
    Alien createAlien(@RequestBody Alien alien) {
        return repo.save(alien);
    }
	
	@GetMapping("aliens")
	public List<Alien> getAliens(){
		
		List<Alien> aliens= (List<Alien>) repo.findAll();
		
		return aliens;
	}
	
 	 
	@GetMapping("/alien/{id}")
	public Alien getAlien(@PathVariable int id){
		
		Alien alien= (Alien) repo.findById(id).get();
		
		return alien;
	}
	
	@PutMapping("/alien/{id}")
    Alien updateAlien(@RequestBody Alien newAlien, @PathVariable int id) {		
 
		return repo.findById(id).map(alien -> {
            alien.setName(newAlien.getName());
            alien.setPoints(newAlien.getPoints());
            return repo.save(alien);
        }).orElseGet(() -> {
        	newAlien.setId(id);
            return repo.save(newAlien);
        });
		
    }
	
	@DeleteMapping("/alien/{id}")
    void deleteEmployee(@PathVariable int id) {
        repo.deleteById(id);
    }
	
        
}
