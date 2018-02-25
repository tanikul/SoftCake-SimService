package com.sim.api.service;

import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.ProgramMst;

public interface ProgramService {
	
	public DataTable<ProgramMst> searchProgramByDataTable(SearchDataTable<ProgramMst> dataTable) ;
	public DataTable<ProgramMst> searchProgramDefaultPriviligeByRoldId(SearchDataTable<ProgramMst> dataTable,int roldId) ;

}
