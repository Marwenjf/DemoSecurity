package com.sec.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sec.dao.EtudiantRepository;
import com.sec.entities.Etudiant;

@RestController
public class EtudiantRestService {

	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Secured(value = {"ROLE_ADMIN","ROLE_SCOLARITE"})
	@RequestMapping(value="/etudiants",method = RequestMethod.POST)
	public Object saveEtudiant(@RequestBody @Valid Etudiant e, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String,Object> errors = new HashMap<>();
			errors.put("errors", true);
			for (FieldError fe : bindingResult.getFieldErrors()) {
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}
		return etudiantRepository.save(e);
	}
	
	@RequestMapping("/etudiants")
	public Page<Etudiant> listEtudiants(int page,int size){
		return etudiantRepository.findAll(PageRequest.of(page, size));
	}
	
	@RequestMapping(value="/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = context.getAuthentication().getName();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority ga: context.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
	}
}
