package com.sim.api.dao;

import java.util.List;

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.Sim;

public interface SimDao {

	public List<Sim> SearchSim(int page, Sim sim);
	public int countSimTotal(Sim sim);
	void insertSim(Sim sim);
	List<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable);
	int CountSimDataTableFilter(SearchDataTable<Sim> searchDataTable);
	int countSimTotalDataTable(SearchDataTable<Sim> searchDataTable); 
}
