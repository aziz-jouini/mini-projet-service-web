package com.example.miniprojetserviceweb.Controller;

import com.example.miniprojetserviceweb.Model.Etudiant;
import com.example.miniprojetserviceweb.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/get")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant createdEtudiant = etudiantRepository.save(etudiant);
        return new ResponseEntity<>(createdEtudiant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant != null) {
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        Etudiant existingEtudiant = etudiantRepository.findById(id).orElse(null);
        if (existingEtudiant != null) {
            existingEtudiant.setNom(etudiantDetails.getNom());
            existingEtudiant.setPrenom(etudiantDetails.getPrenom());
            existingEtudiant.setNbAbsences(etudiantDetails.getNbAbsences());
            existingEtudiant.setReussite(etudiantDetails.isReussite()); // Update exam result or grade
            Etudiant updatedEtudiant = etudiantRepository.save(existingEtudiant);
            return new ResponseEntity<>(updatedEtudiant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/taux-absence")
    public ResponseEntity<Double> getTauxAbsentéisme() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        int totalEtudiants = etudiants.size();
        if (totalEtudiants == 0) {
            return new ResponseEntity<>(0.0, HttpStatus.OK); // Avoid division by zero
        }
        long totalAbsences = etudiants.stream().filter(e -> e.getNbAbsences() > 0).count();
        double tauxAbsence = ((double) totalAbsences / totalEtudiants) * 100.0;
        return new ResponseEntity<>(tauxAbsence, HttpStatus.OK);
    }

    @GetMapping("/taux-reussite")
    public ResponseEntity<Double> getTauxRéussite() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        int totalEtudiants = etudiants.size();
        if (totalEtudiants == 0) {
            return new ResponseEntity<>(0.0, HttpStatus.OK); // Avoid division by zero
        }
        long totalReussite = etudiants.stream().filter(Etudiant::isReussite).count();
        double tauxReussite = ((double) totalReussite / totalEtudiants) * 100.0;
        return new ResponseEntity<>(tauxReussite, HttpStatus.OK);
    }
}
