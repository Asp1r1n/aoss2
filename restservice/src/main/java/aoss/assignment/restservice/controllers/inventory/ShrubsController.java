package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Shrub;
import aoss.assignment.restservice.services.inventory.ShrubsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db/inventory/shrubs")
public class ShrubsController {

    private final ShrubsService shrubsService;

    public ShrubsController(ShrubsService shrubsService) {
        this.shrubsService = shrubsService;
    }

    @GetMapping
    public List<Shrub> list(){
        return shrubsService.findAll();
    }

    @GetMapping("{id}")
    public Shrub getOne(@PathVariable String id){
        return shrubsService.findById(id);
    }

    @PostMapping
    public Shrub create(@RequestBody Shrub shrub){
        return shrubsService.create(shrub);
    }

    @PutMapping("{id}")
    public Shrub update(@PathVariable String id, @RequestBody Shrub shrub){
        Shrub byId = shrubsService.findById(id);
        BeanUtils.copyProperties(shrub, byId);
        return shrubsService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        shrubsService.deleteById(id);
    }
}
