package com.sim.api.dao;

import java.util.List;

import com.sim.api.model.Sim;

public interface SimDao {

	public List<Sim> SearchSim(int page, Sim sim);
	public int countSimTotal(Sim sim); 
}
