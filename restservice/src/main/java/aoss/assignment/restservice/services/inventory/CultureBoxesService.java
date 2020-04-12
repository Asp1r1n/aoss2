package aoss.assignment.restservice.services.inventory;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import aoss.assignment.restservice.models.inventory.CultureBox;
import aoss.assignment.restservice.repos.inventory.CultureBoxesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureBoxesService {

    private final CultureBoxesRepo cultureBoxesRepo;

    public CultureBoxesService(CultureBoxesRepo cultureBoxesRepo) {
        this.cultureBoxesRepo = cultureBoxesRepo;
    }

    public List<CultureBox> findAll(){
        return cultureBoxesRepo.findAll();
    }

    public CultureBox findById(String id) {
        return cultureBoxesRepo.findById(id);
    }

    public CultureBox create(CultureBox cultureBox) {
        return cultureBoxesRepo.create(cultureBox);
    }

    public CultureBox updateById(String id, CultureBox cultureBox) {
        return cultureBoxesRepo.updateById(id,cultureBox);
    }

    public void deleteById(String id) {
        cultureBoxesRepo.deleteById(id);
    }
}
