package com.adobe.aem.guides.wknd.spa.react.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.spa.react.core.models.LinkModel;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LinkModelImpl implements LinkModel {

	@ValueMapValue
	private String title;

	@ValueMapValue
	private String link;

	@ValueMapValue
	private boolean isInternal;

	@ValueMapValue
	private String linkAction;
	
	@ValueMapValue
	private String role;

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public boolean isInternal() {
		return isInternal;
	}

	public String getLinkAction() {
		return linkAction;
	}
	
	public String getRole() {
		return role;
	}
	
}
