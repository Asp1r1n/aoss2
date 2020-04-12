package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import aoss.assignment.restservice.models.inventory.Genomic;
import aoss.assignment.restservice.services.inventory.GenomicsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/genomics")
public class GenomicsController {

    private final GenomicsService genomicsService;

    public GenomicsController(GenomicsService genomicsService) {
        this.genomicsService = genomicsService;
    }

    @GetMapping
    public List<Genomic> list(){
        return genomicsService.findAll();
    }

    @GetMapping("{id}")
    public Genomic getOne(@PathVariable String id){
        return genomicsService.findById(id);
    }

    @PostMapping
    public Genomic create(@RequestBody Genomic genomic){
        return genomicsService.create(genomic);
    }

    @PutMapping("{id}")
    public Genomic update(@PathVariable String id, @RequestBody Genomic genomic){
        Genomic byId = genomicsService.findById(id);
        BeanUtils.copyProperties(genomic, byId);
        return genomicsService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        genomicsService.deleteById(id);
    }
}
