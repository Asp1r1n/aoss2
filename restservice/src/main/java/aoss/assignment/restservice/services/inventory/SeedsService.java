package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Seed;
import aoss.assignment.restservice.repos.inventory.SeedsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedsService {

    private final SeedsRepo seedsRepo;

    public SeedsService(SeedsRepo seedsRepo) {
        this.seedsRepo = seedsRepo;
    }

    public List<Seed> findAll() {
        return seedsRepo.findAll();
    }

    public Seed findById(String id) {
        return seedsRepo.findById(id);
    }

    public Seed create(Seed seed) {
        return seedsRepo.create(seed);
    }

    public Seed updateById(String id, Seed byId) {
        return seedsRepo.updateById(id,byId);
    }

    public void deleteById(String id) {
        seedsRepo.deleteById(id);
    }

}
