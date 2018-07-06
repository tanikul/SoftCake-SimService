package com.sim.api.dao;

import java.util.List;

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.FilterSearch;
import com.sim.api.model.RequestMst;
import com.sim.api.model.RequestSim;
import com.sim.api.model.Sim;

public interface SimDao {

	public int countSimTotal(Sim sim);
	void insertSim(Sim sim);
	List<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable, String type);
	int CountSimDataTableFilter(SearchDataTable<Sim> searchDataTable, String type);
	int countSimTotalDataTable(SearchDataTable<Sim> searchDataTable, String type);
	Sim selectSimByNumber(String simNumber, String userId);
	List<Sim> SearchSim(int page, FilterSearch filter);
	void updateAutorizeStatus(Sim sim);
	void deleteSimTmp(Sim sim);
	void updateTmpToSimMst(Sim sim);
	void copyToSimTmp(Sim sim);
	void updateSimTmp(Sim sim);
	void deleteSimTmpAndMst(Sim sim);
	void copyToSimMst(Sim sim);
	void copyToSimTmpDelete(Sim sim);
	List<RequestMst> SearchSimRequestDataTable(SearchDataTable<RequestMst> searchDataTable);
	int CountRequestSimDataTableFilter(SearchDataTable<RequestMst> searchDataTable);
	int countRequestSimTotalDataTable(SearchDataTable<RequestMst> searchDataTable);
	void saveRequestSim(RequestMst sim);
	void cancelRequestSim(RequestMst sim);
	void updateRequestSim(RequestSim sim);
	void deleteRequestSim(RequestSim sim);
	int checkSimNumberBeforeRequest(String sim);
	int checkDuplicateSimNumber(String sim);
	Sim checkDuplicateSimBeforeAddSimNumber(String simNumber);
	String generateRequestId();
	void insertRequestSimData(RequestSim sim);
	void cancelRequestBySim(RequestSim sim);
}
