package com.example.miniprojetserviceweb.Controller;

import com.example.miniprojetserviceweb.Model.Enseignant;
import com.example.miniprojetserviceweb.Repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @GetMapping
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantRepository.findAll();
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        Enseignant createdEnseignant = enseignantRepository.save(enseignant);
        return new ResponseEntity<>(createdEnseignant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        Enseignant enseignant = enseignantRepository.findById(id).orElse(null);
        if (enseignant != null) {
            return new ResponseEntity<>(enseignant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignantDetails) {
        Enseignant existingEnseignant = enseignantRepository.findById(id).orElse(null);
        if (existingEnseignant != null) {
            existingEnseignant.setNom(enseignantDetails.getNom());
            existingEnseignant.setPrenom(enseignantDetails.getPrenom());
            existingEnseignant.setMatiere(enseignantDetails.getMatiere());
            Enseignant updatedEnseignant = enseignantRepository.save(existingEnseignant);
            return new ResponseEntity<>(updatedEnseignant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        enseignantRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
