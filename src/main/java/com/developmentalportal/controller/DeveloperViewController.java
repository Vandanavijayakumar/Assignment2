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


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@Scope (value = "session")
@Component (value = "developerView")
@ELBeanName(value = "developerView")
@Join(path = "/developer_view/{id}", to = "/developers/developer_view.jsf")
public class DeveloperViewController {


	@Autowired
	private DeveloperRepository developerRepository;
	private Developers developers = new Developers();

	@Deferred
	@RequestAction
	@IgnorePostback
	public void getOneDeveloper() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String id = request.getParameter("id");
		developers = developerRepository.findOne(Long.parseLong(id));
	}

	public Developers getDeveloper(){
		return developers;
	}

	
	public String updateDeveloperSkills() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String id = request.getParameter("id");
		Developers currentDeveloperData = developerRepository.findOne(Long.parseLong(id));
		String skillUpdate = currentDeveloperData.getSkills() + " , " + developers.getSkills();
		currentDeveloperData.setSkills(skillUpdate);	
		developerRepository.save(currentDeveloperData);
		return "/developers/developer_list.xhtml?faces-redirect=true";
	}
}
