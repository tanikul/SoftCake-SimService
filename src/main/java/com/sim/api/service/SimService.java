package com.sim.api.service;

import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.FilterSearch;
import com.sim.api.model.RequestSim;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;

public interface SimService {

	void insertSim(Sim sim);
	public DataTable<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable, String type);
	public Sim selectSimByNumber(String simNumber, String userId);
	ResultDataSim SearchSim(int page, FilterSearch filter);
	void acceptSim(Sim sim);
	void cancelSim(Sim sim);
	void rejectSim(Sim sim);
	void updateSimTmp(Sim sim);
	void copyToSimTmp(Sim sim);
	void copyToSimTmpDelete(Sim sim);
	DataTable<RequestSim> SearchRequestSimDataTable(SearchDataTable<RequestSim> searchDataTable);
	void saveRequestSim(RequestSim sim);
	void cncelRequestSim(RequestSim sim);
	void approveRequestSim(RequestSim sim);
	int checkSimNumberBeforeRequest(String sim);
	int checkDuplicateSimNumber(String sim);
	Sim checkDuplicateSimBeforeAddSimNumber(String simNumber);
}
