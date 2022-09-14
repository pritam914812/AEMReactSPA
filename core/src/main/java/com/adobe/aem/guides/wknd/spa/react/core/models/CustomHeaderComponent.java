package com.adobe.aem.guides.wknd.spa.react.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.spa.react.core.models.impl.FooterLinksModelImpl;
import com.adobe.cq.export.json.ComponentExporter;

public interface CustomHeaderComponent extends ComponentExporter {

	List<FooterLinksModelImpl> getLinks();

}