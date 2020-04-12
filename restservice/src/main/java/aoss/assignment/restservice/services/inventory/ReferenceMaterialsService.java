package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.ReferenceMaterial;
import aoss.assignment.restservice.repos.inventory.ReferenceMaterialsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceMaterialsService {
    
    private final ReferenceMaterialsRepo referenceMaterialsRepo;

    public ReferenceMaterialsService(ReferenceMaterialsRepo referenceMaterialsRepo) {
        this.referenceMaterialsRepo = referenceMaterialsRepo;
    }


    public List<ReferenceMaterial> findAll() {
        return referenceMaterialsRepo.findAll();
    }

    public ReferenceMaterial findById(String id) {
        return referenceMaterialsRepo.findById(id);
    }

    public ReferenceMaterial create(ReferenceMaterial referenceMaterial) {
        return referenceMaterialsRepo.create(referenceMaterial);
    }

    public ReferenceMaterial updateById(String id, ReferenceMaterial byId) {
        return referenceMaterialsRepo.updateById(id,byId);
    }

    public void deleteById(String id) {
        referenceMaterialsRepo.deleteById(id);
    }
}
