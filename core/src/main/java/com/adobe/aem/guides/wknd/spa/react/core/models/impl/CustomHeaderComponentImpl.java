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

import com.adobe.acs.commons.models.injectors.annotation.ChildResourceFromRequest;
import com.adobe.aem.guides.wknd.spa.react.core.models.CustomHeaderComponent;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomHeaderComponent.class,
		ComponentExporter.class }, resourceType = CustomHeaderComponentImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomHeaderComponentImpl implements CustomHeaderComponent {

	static final String RESOURCE_TYPE = "wknd-spa-react/components/header";

	@ChildResourceFromRequest
	private List<FooterLinksModelImpl> primaryFooterLinks;

	@Inject
	private SlingHttpServletRequest request;

	public List<FooterLinksModelImpl> getLinks() {
		return primaryFooterLinks;
	}

	@PostConstruct
	protected void init() {
		List<String> selector = Arrays.asList(request.getRequestPathInfo().getSelectors());
		if (Objects.nonNull(primaryFooterLinks) && !primaryFooterLinks.isEmpty()) {
			primaryFooterLinks.removeIf(menu -> {
				return (StringUtils.isNotBlank(menu.getRole()) && !selector.contains(menu.getRole()));
			});
		}
	}

	@Override
	public String getExportedType() {
		return CustomHeaderComponentImpl.RESOURCE_TYPE;
	}
}