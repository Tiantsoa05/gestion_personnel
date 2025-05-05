package com.gestion.personnel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employe {
	@Id
	private String numEmp;
	
	private String nom;
	
	private Double salaire;
	
	public String getNumEmp() {
		return this.numEmp;
	}
	public void setNumEmp(String n) {
		this.numEmp = n;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}
	
}
