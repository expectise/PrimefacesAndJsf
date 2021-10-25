package com.serpienteemplumada.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.serpienteemplumada.dto.TagscontentDTO;
import com.serpienteemplumada.views.beans.TagscontentViewBean;

@Scope("view")
@Component("tagscontent")
public class TagscontentView extends TagscontentViewBean{

	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		TagscontentDTO input = (TagscontentDTO) sessionMap.get("tagscontentDTO");
		
		if (input != null) {
			setTagscontentDTO(input);
			sessionMap.remove("tagscontentDTO");
		}
	}
}
