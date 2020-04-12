package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Shrub;
import aoss.assignment.restservice.repos.inventory.ShrubsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShrubsService {

    private final ShrubsRepo shrubsRepo;

    public ShrubsService(ShrubsRepo shrubsRepo) {
        this.shrubsRepo = shrubsRepo;
    }


    public List<Shrub> findAll() {
        return shrubsRepo.findAll();
    }

    public Shrub findById(String id) {
        return shrubsRepo.findById(id);
    }

    public Shrub create(Shrub shrub) {
        return shrubsRepo.create(shrub);
    }

    public Shrub updateById(String id, Shrub byId) {
        return shrubsRepo.updateById(id,byId);
    }

    public void deleteById(String id) {
        shrubsRepo.deleteById(id);
    }
}
