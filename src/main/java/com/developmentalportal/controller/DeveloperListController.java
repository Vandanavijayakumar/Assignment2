package com.developmentalportal.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.developmentalportal.entity.Developers;
import com.developmentalportal.repository.DeveloperRepository;

import java.util.List;

@Scope (value = "session")
@Join(path = "/", to = "/developers/developer_list.jsf")
@Component (value = "developerList")
@ELBeanName(value = "developerList")
public class DeveloperListController {

	@Autowired
	private DeveloperRepository developerRepository;
	private List<Developers> developers;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		developers = developerRepository.findAll();
	}


	public List<Developers> getDevelopers() {
		return developers;
	}

	private Developers developer = new Developers();

	public String createNewDeveloper() {
		developerRepository.save(developer);
		developer = new Developers();
		return "/developers/developer_list.xhtml?faces-redirect=true";
	}

	public Developers getAllDevelopers(){
		return developer;
	}


}
