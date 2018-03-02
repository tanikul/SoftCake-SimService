package com.sim.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sim.api.dao.ProgramDao;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.ProgramMst;

@Service("programService")
public class ProgramServiceImpl  implements ProgramService{
	private static final Logger LOGGER = Logger.getLogger(ProgramServiceImpl.class);

	@Autowired
	@Qualifier("programDao")
	ProgramDao programDao;
	
	@Override
	public DataTable<ProgramMst> searchProgramByDataTable(SearchDataTable<ProgramMst> dataTable) {
		DataTable<ProgramMst> result = new DataTable<>();
		try {
			List<ProgramMst> roleList = programDao.searchProgramMstByDataTable(dataTable);
			int size = programDao.countSearchProgramByDataTableTotal(dataTable);
			result.setData(roleList);
			result.setDraw(dataTable.getDraw());
			result.setRecordsTotal(size);
			result.setRecordsFiltered(size);
		}catch(Exception ex){
			LOGGER.error(ex);
			throw ex; 
		}
		return result;
	}

	@Override
	public DataTable<ProgramMst> searchProgramDefaultPriviligeByRoldId(SearchDataTable<ProgramMst> dataTable , int roldId) {
		DataTable<ProgramMst> result = new DataTable<>();
		try {
			List<ProgramMst> roleList = programDao.searchProgramMstDefaultPriviligeByRoleId(roldId);
			int size = programDao.countSearchProgramByDataTableTotal(dataTable);
			result.setData(roleList);
			result.setDraw(dataTable.getDraw());
			result.setRecordsTotal(size);
			result.setRecordsFiltered(size);
		}catch(Exception ex){
			LOGGER.error(ex);
			throw ex; 
		}
		return result;
	}

}
