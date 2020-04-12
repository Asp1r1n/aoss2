package aoss.assignment.restservice.controllers.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Tree;
import aoss.assignment.restservice.services.inventory.TreesService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/trees")
public class TreesController {

    private final TreesService treesService;

    public TreesController(TreesService treesService) {
        this.treesService = treesService;
    }

    @GetMapping
    public List<Tree> list(){
        return treesService.findAll();
    }

    @GetMapping("{id}")
    public Tree getOne(@PathVariable String id){
        return treesService.findById(id);
    }

    @PostMapping
    public Tree create(@RequestBody Tree tree){
        return treesService.create(tree);
    }

    @PutMapping("{id}")
    public Tree update(@PathVariable String id, @RequestBody Tree tree){
        Tree byId = treesService.findById(id);
        BeanUtils.copyProperties(tree, byId);
        return treesService.updateById(id, byId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        treesService.deleteById(id);
    }


}
