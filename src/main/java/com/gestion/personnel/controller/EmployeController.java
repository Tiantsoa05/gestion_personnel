package com.gestion.personnel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.personnel.entities.Employe;
import com.gestion.personnel.repository.EmployeRepository;

import interfaces.Stats;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/employer")
public class EmployeController {
	private EmployeRepository rep = null;
	
	public EmployeController(EmployeRepository r) {
		this.rep = r;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employe>> getAll(){
		return new ResponseEntity<>(rep.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <?> byID(@PathVariable String id){
		Optional<Employe> emp = rep.findById(id);
		
		if(emp.isPresent()) {			
			return new ResponseEntity<>(emp.get(),HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>("L'employé concerné est introuvable",HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/new")
	public ResponseEntity<Employe> ajouter(@RequestBody Employe employer){
		
		String lastCode = rep.findLastId();
		String newCode = this.generateUniqueCode(lastCode);
		
		employer.setNumEmp(newCode);
		
		return new ResponseEntity<>(rep.save(employer),HttpStatus.CREATED);
	}
	
	@PostMapping("/modif/{idEmp}")
	public ResponseEntity <?> modifier(@PathVariable String idEmp,@RequestBody Employe modif){
		Optional<Employe> emp = rep.findById(idEmp);
		
		if(emp.isPresent()) {	
			Employe empL = emp.get();
			
			empL.setNom(modif.getNom());
			empL.setSalaire(modif.getSalaire());
			
			return new ResponseEntity<>(rep.save(empL),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("L'employé concerné est introuvable",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/suppr/{id}")
	private ResponseEntity <?> supprimer(@PathVariable String id){
		Optional<Employe> emp = rep.findById(id);
		
		if(emp.isPresent()) {
			rep.delete(emp.get());
			return new ResponseEntity<>("Employé supprimé avec succès",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("L'employé concerné est introuvable",HttpStatus.NOT_FOUND);
	}
	
	private String generateUniqueCode(String lastCode) {
	    if (lastCode == null) {
	        return "EMP1"; 
	    }   
	    int numberPart = Integer.parseInt(lastCode.substring(3));
	    return "EMP" + (numberPart + 1); 
	}
}


