package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Genomic;
import aoss.assignment.restservice.models.inventory.Processing;
import aoss.assignment.restservice.services.inventory.ProcessingsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/processing")
public class ProcessingsController {

    private final ProcessingsService processingsService;

    public ProcessingsController(ProcessingsService processingsService) {
        this.processingsService = processingsService;
    }

    @GetMapping
    public List<Processing> list(){
        return processingsService.findAll();
    }

    @GetMapping("{id}")
    public Processing getOne(@PathVariable String id){
        return processingsService.findById(id);
    }

    @PostMapping
    public Processing create(@RequestBody Processing processing){
        return processingsService.create(processing);
    }

    @PutMapping("{id}")
    public Processing update(@PathVariable String id, @RequestBody Processing processing){
        Processing byId = processingsService.findById(id);
        BeanUtils.copyProperties(processing, byId);
        return processingsService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        processingsService.deleteById(id);
    }
}
