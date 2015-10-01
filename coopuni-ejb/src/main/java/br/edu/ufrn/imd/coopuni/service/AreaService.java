package br.edu.ufrn.imd.coopuni.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ufrn.imd.coopuni.boundary.AreaDAO;
import br.edu.ufrn.imd.coopuni.model.Area;

@Stateless
public class AreaService {
	
	@Inject
	private AreaDAO areaDAO;
	
	public void register(Area area) throws Exception {
	    areaDAO.create(area);
	}
	
	public Area findById(long id) {
		return areaDAO.retrieve(id);
	}
	
	public List<Area> getAllEntries() {
		return areaDAO.getAllEntries();
	}
	
	public Area retrieve(long id) {
		return areaDAO.retrieve(id);
	}
	
}
