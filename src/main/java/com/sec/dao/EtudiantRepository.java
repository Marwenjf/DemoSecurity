package com.sec.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

}
