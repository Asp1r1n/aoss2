package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Seed;
import aoss.assignment.restservice.services.inventory.SeedsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db/inventory/seeds")
public class SeedsController {

    private final SeedsService seedsService;

    public SeedsController(SeedsService seedsService) {
        this.seedsService = seedsService;
    }

    @GetMapping
    public List<Seed> list(){
        return seedsService.findAll();
    }

    @GetMapping("{id}")
    public Seed getOne(@PathVariable String id){
        return seedsService.findById(id);
    }

    @PostMapping
    public Seed create(@RequestBody Seed seed){
        return seedsService.create(seed);
    }

    @PutMapping("{id}")
    public Seed update(@PathVariable String id, @RequestBody Seed seed){
        Seed byId = seedsService.findById(id);
        BeanUtils.copyProperties(seed, byId);
        return seedsService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        seedsService.deleteById(id);
    }
}
