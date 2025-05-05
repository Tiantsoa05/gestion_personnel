package com.gestion.personnel.repository;

import com.gestion.personnel.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeRepository extends JpaRepository<Employe,String>{
	@Query("SELECT e.numEmp FROM Employe e ORDER BY e.numEmp DESC LIMIT 1")
	String findLastId();
}
