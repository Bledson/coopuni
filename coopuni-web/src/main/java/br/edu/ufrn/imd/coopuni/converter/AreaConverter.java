package br.edu.ufrn.imd.coopuni.converter;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.service.AreaService;

@Model
public class AreaConverter implements Converter {
	
	@Inject
	AreaService areaService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 Long id = Long.valueOf(value);
		 Area area = areaService.findById(id);
		 return area;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Area area = (Area) value;
		Long id = area.getId();
		return String.valueOf(id);
	}
	
}