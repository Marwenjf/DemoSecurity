package com.sec;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sec.dao.EtudiantRepository;
import com.sec.entities.Etudiant;

@SpringBootApplication
public class DemoSecurityApplication {

	public static void main(String[] args) throws ParseException {
		 ApplicationContext ctx =SpringApplication.run(DemoSecurityApplication.class, args);
		 /*EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 etudiantRepository.save(new Etudiant("Marwen", "Jaffel", df.parse("1995-04-13")));
		 etudiantRepository.save(new Etudiant("Ahmed", "Jaffel", df.parse("1990-08-06")));
		 etudiantRepository.save(new Etudiant("Abd Elwaheb", "Jaffel", df.parse("1963-09-23")));
		 etudiantRepository.save(new Etudiant("Soukaina", "Haider", df.parse("1966-03-10")));
		 
		 List<Etudiant> etudiants = etudiantRepository.findAll();
		 etudiants.forEach(e-> System.out.println(e.toString()));*/
	}

}
