package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import aoss.assignment.restservice.services.inventory.CultureBoxesService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory/cultureboxes")
public class CultureBoxesController {

    private final CultureBoxesService cultureBoxesService;

    public CultureBoxesController(CultureBoxesService cultureBoxesService) {
        this.cultureBoxesService = cultureBoxesService;
    }

    @GetMapping
    public List<CultureBox> list(){
        return cultureBoxesService.findAll();
    }

    @GetMapping("{id}")
    public CultureBox getOne(@PathVariable String id){
        return cultureBoxesService.findById(id);
    }

    @PostMapping
    public CultureBox create(@RequestBody CultureBox cultureBox){
        return cultureBoxesService.create(cultureBox);
    }

    @PutMapping("{id}")
    public CultureBox update(@PathVariable String id, @RequestBody CultureBox cultureBox){
        CultureBox byId = cultureBoxesService.findById(id);
        BeanUtils.copyProperties(cultureBox, byId);
        return cultureBoxesService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        cultureBoxesService.deleteById(id);
    }
}
