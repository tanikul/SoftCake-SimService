package com.sim.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sim.api.dao.SimDao;
import com.sim.api.dao.SimDaoImpl;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;

@Service
public class SimServiceImpl implements SimService {

	private static final Logger logger = Logger.getLogger(SimServiceImpl.class);
	
	@Autowired
	private SimDao simDao;
	
	@Override
	public ResultDataSim SearchSim(int page, Sim sim) {
		ResultDataSim result = new ResultDataSim();
		try {
			List<Sim> sims = simDao.SearchSim(page, sim);
			result.setSims(sims);
			result.setCnt(sims.size());
		} catch(Exception ex){
			throw ex;
		}
		return result;
	}

	@Override
	public void insertSim(Sim sim) {
		try {
			simDao.insertSim(sim);
		} catch(Exception ex){
			throw ex;
		}
	}

	@Override
	public DataTable<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable) {
		DataTable<Sim> result = new DataTable<>();
		try {
			List<Sim> corps = simDao.SearchSimDataTable(searchDataTable);
			result.setData(corps);
			result.setDraw(searchDataTable.getDraw());
			result.setRecordsTotal(simDao.countSimTotalDataTable(searchDataTable));
			result.setRecordsFiltered(simDao.CountSimDataTableFilter(searchDataTable));
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
}
