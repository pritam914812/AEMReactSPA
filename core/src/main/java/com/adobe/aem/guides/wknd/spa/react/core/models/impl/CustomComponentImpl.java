package com.adobe.aem.guides.wknd.spa.react.core.models.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.spa.react.core.models.CustomComponent;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;

@Model(adaptables = SlingHttpServletRequest.class, adapters = { CustomComponent.class,
		ComponentExporter.class }, resourceType = CustomComponentImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomComponentImpl implements CustomComponent {

	@ValueMapValue
	private String message;

	@ValueMapValue
	private String role;

	@Inject
	private SlingHttpServletRequest request;

	static final String RESOURCE_TYPE = "wknd-spa-react/components/custom-component";

	@PostConstruct
	protected void init() {
		List<String> selector = Arrays.asList(request.getRequestPathInfo().getSelectors());
		if (!selector.contains(role)) {
			message = null;
		}
	}

	@Override
	public String getMessage() {
		return StringUtils.isNotBlank(message) ? message.toUpperCase() : null;
	}

	@Override
	public String getExportedType() {
		return CustomComponentImpl.RESOURCE_TYPE;
	}

}