package br.edu.ufrn.imd.coopuni.converter;

import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.service.AreaService;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

@Model
public class AreaConverter implements Converter {
  @Inject
  AreaService areaService;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Long id = Long.valueOf(value);
    return areaService.retrieve(id);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    Area area = (Area) value;
    Long id = area.getId();
    return String.valueOf(id);
  }
}
