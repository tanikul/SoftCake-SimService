package com.sim.api.service;

import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;

public interface SimService {

	public ResultDataSim SearchSim(int page, Sim sim);
	void insertSim(Sim sim);
	public DataTable<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable);
}
