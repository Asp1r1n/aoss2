package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import aoss.assignment.restservice.models.inventory.Genomic;
import aoss.assignment.restservice.repos.inventory.GenomicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenomicsService {

    private final GenomicsRepo genomicsRepo;

    public GenomicsService(GenomicsRepo genomicsRepo) {
        this.genomicsRepo = genomicsRepo;
    }

    public List<Genomic> findAll() {
        return genomicsRepo.findAll();
    }


    public Genomic findById(String id) {
        return genomicsRepo.findById(id);
    }

    public Genomic create(Genomic genomic) {
        return genomicsRepo.create(genomic);
    }

    public Genomic updateById(String id, Genomic byId) {
        return genomicsRepo.updateById(id, byId);
    }

    public void deleteById(String id) {
        genomicsRepo.deleteById(id);
    }

}
