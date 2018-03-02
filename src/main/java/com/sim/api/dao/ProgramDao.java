package com.sim.api.dao;

import java.util.List;

import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.ProgramMst;

public interface ProgramDao {

	public List<ProgramMst> searchProgramMstByDataTable(SearchDataTable<ProgramMst> dataTable);
	public int countSearchProgramByDataTableTotal(SearchDataTable<ProgramMst> dataTable) ;
	public int countSearchProgramByDataTableFilter(SearchDataTable<ProgramMst> dataTable) ;
	public List<ProgramMst> searchProgramMstDefaultPriviligeByRoleId(int roldId);
}
