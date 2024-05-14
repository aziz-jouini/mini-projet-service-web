package com.example.miniprojetserviceweb.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private int nbAbsences = 0; // New field for number of absences
    private boolean Reussite;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getNbAbsences() {
        return nbAbsences;
    }
    public void setNbAbsences(int nbAbsences) {
        this.nbAbsences = nbAbsences;
    }

    public boolean isReussite() {
        return Reussite;
    }
    public void setReussite(boolean Reussite) {
        this.Reussite = Reussite;
    }

    // Getters and setters

}