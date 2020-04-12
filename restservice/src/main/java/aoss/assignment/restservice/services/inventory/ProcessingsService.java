package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.Genomic;
import aoss.assignment.restservice.models.inventory.Processing;
import aoss.assignment.restservice.repos.inventory.ProcessingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingsService {

    private final ProcessingsRepo processingsRepo;

    public ProcessingsService(ProcessingsRepo processingsRepo) {
        this.processingsRepo = processingsRepo;
    }

    public List<Processing> findAll() {
        return processingsRepo.findAll();
    }

    public Processing findById(String id) {
        return processingsRepo.findById(id);
    }

    public Processing create(Processing processing) {
        return processingsRepo.create(processing);
    }

    public Processing updateById(String id, Processing byId) {
        return processingsRepo.updateById(id,byId);
    }

    public void deleteById(String id) {
        processingsRepo.deleteById(id);
    }
}
