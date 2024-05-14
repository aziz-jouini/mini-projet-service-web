package com.example.miniprojetserviceweb.Repository;

import com.example.miniprojetserviceweb.Model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
