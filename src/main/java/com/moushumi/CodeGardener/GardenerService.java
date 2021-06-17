package com.moushumi.CodeGardener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GardenerService {

    private final GardenerRepository gardenerRepo;

    @Autowired
    public GardenerService(GardenerRepository gardenerRepo) {
        this.gardenerRepo = gardenerRepo;
    }

    public List<Gardener> getGardeners() {
        return gardenerRepo.findAll();
    }

    public Gardener addGardener(Gardener gardener) {
        //Need to check repo to ensure gardener doesn't already exist
        Optional<Gardener> gardenerOptional = gardenerRepo.findGardenerByEmail(gardener.getEmail());
        if (gardenerOptional.isPresent()) {
            throw new IllegalStateException("Student already exists");
        }
        gardenerRepo.save(gardener);
        return gardener;
    }

    public void deleteGardener(Long gardenerId) {
        if (gardenerRepo.existsById(gardenerId)) {
            gardenerRepo.deleteById(gardenerId);
        } else {
            throw new IllegalStateException("Gardener not found");
        }
    }

    @Transactional
    public void updateGardener(Long gardenerId, String name, String email, LocalDate dob) {

        Gardener gardener = gardenerRepo.findById(gardenerId).orElseThrow(() -> new IllegalStateException("Gardener not found"));
        if (gardener.getName() != name && gardener.getName() != null) {
            gardener.setName(name);
            System.out.println("Name is updated");
        }

        Optional<Gardener> gardenerOptional = gardenerRepo.findGardenerByEmail(gardener.getEmail());
        if (!gardenerOptional.isPresent()) {
            gardener.setEmail(email);
        }
        if (!gardener.getDob().equals(dob) && gardener.getDob() != null) {
            gardener.setDob(dob);
        }
    }

    public void updateGardener(Long gardenerId) {
    }
}