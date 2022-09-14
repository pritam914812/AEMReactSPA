package com.adobe.aem.guides.wknd.spa.react.core.models.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.acs.commons.models.injectors.annotation.ChildResourceFromRequest;
import com.adobe.aem.guides.wknd.spa.react.core.models.FooterLinksModel;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class FooterLinksModelImpl extends LinkModelImpl implements FooterLinksModel {

	@ChildResourceFromRequest
	private List<LinkModelImpl> menuItems;

	@ValueMapValue
	private String role;

	@Inject
	private SlingHttpServletRequest request;

	public List<LinkModelImpl> getMenuItems() {
		return menuItems;
	}

	public String getRole() {
		return role;
	}

	@PostConstruct
	protected void init() {
		List<String> selector = Arrays.asList(request.getRequestPathInfo().getSelectors());
		if (Objects.nonNull(menuItems) && !menuItems.isEmpty()) {
			menuItems.removeIf(menu -> {
				return (StringUtils.isNotBlank(menu.getRole()) && !selector.contains(menu.getRole()));
			});
		}
	}

}
