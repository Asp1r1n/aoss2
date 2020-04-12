package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.ReferenceMaterial;
import aoss.assignment.restservice.services.inventory.ReferenceMaterialsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/referencematerials")
public class ReferenceMaterialsController {

    private final ReferenceMaterialsService referenceMaterialsService;

    public ReferenceMaterialsController(ReferenceMaterialsService referenceMaterialsService) {
        this.referenceMaterialsService = referenceMaterialsService;
    }

    @GetMapping
    public List<ReferenceMaterial> list(){
        return referenceMaterialsService.findAll();
    }

    @GetMapping("{id}")
    public ReferenceMaterial getOne(@PathVariable String id){
        return referenceMaterialsService.findById(id);
    }

    @PostMapping
    public ReferenceMaterial create(@RequestBody ReferenceMaterial referenceMaterial){
        return referenceMaterialsService.create(referenceMaterial);
    }

    @PutMapping("{id}")
    public ReferenceMaterial update(@PathVariable String id, @RequestBody ReferenceMaterial referenceMaterial){
        ReferenceMaterial byId = referenceMaterialsService.findById(id);
        BeanUtils.copyProperties(referenceMaterial, byId);
        return referenceMaterialsService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        referenceMaterialsService.deleteById(id);
    }
}
