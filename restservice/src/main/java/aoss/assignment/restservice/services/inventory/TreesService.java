package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Tree;
import aoss.assignment.restservice.repos.inventory.TreesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreesService {

    private final TreesRepo treesRepo;

    public TreesService(TreesRepo treesRepo) {
        this.treesRepo = treesRepo;
    }

    public List<Tree> findAll() {
        return treesRepo.findAll();
    }

    public Tree findById(String id) {
        return treesRepo.findById(id);
    }

    public Tree create(Tree tree) {
        return treesRepo.create(tree);
    }

    public Tree updateById(String id, Tree byId) {
        return treesRepo.updateById(id, byId);
    }

    public void deleteById(String id) {
        treesRepo.deleteById(id);
    }
}
