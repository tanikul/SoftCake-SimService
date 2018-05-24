package com.sim.api.service;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sim.api.dao.SimDao;
import com.sim.api.datatable.DataTable;
import com.sim.api.datatable.SearchDataTable;
import com.sim.api.model.FilterSearch;
import com.sim.api.model.RequestSim;
import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.utils.AppUtils;

@Service
public class SimServiceImpl implements SimService {

	private static final Logger logger = Logger.getLogger(SimServiceImpl.class);
	
	@Autowired
	private SimDao simDao;
	
	@Autowired
	private AppUtils app;
	
	@Override
	public ResultDataSim SearchSim(int page, FilterSearch filter) {
		ResultDataSim result = new ResultDataSim();
		try {
			List<Sim> sims = simDao.SearchSim(page, filter);
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
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public DataTable<Sim> SearchSimDataTable(SearchDataTable<Sim> searchDataTable, String type) {
		DataTable<Sim> result = new DataTable<>();
		try {
			List<Sim> corps = simDao.SearchSimDataTable(searchDataTable, type);
			result.setData(corps);
			result.setDraw(searchDataTable.getDraw());
			result.setRecordsTotal(simDao.countSimTotalDataTable(searchDataTable, type));
			result.setRecordsFiltered(simDao.CountSimDataTableFilter(searchDataTable, type));
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}

	@Override
	public Sim selectSimByNumber(String simNumber, String userId) {
		Sim result = null;
		try {
			result = simDao.selectSimByNumber(simNumber, userId);
			result.setSimNumber(app.parseSimFormat(result.getSimNumber()));
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
		return result;
	}
	
	@Override
	public void rejectSim(Sim sim) {
		try {
			simDao.updateAutorizeStatus(sim);
		}catch(Exception ex){
			logger.error(ex);
			throw ex; 
		}
	}
	
	@Override
	public void cancelSim(Sim sim) {
		try {
			simDao.deleteSimTmp(sim);
		}catch(Exception ex){
			logger.error(ex);
			throw ex; 
		}
	}
	
	@Override
	public void acceptSim(Sim sim) {
		try {
			if("D".equals(sim.getOperationFlag())){
				simDao.deleteSimTmpAndMst(sim);
			}else if("N".equals(sim.getOperationFlag())){
				sim.setActiveStatus("Y");
				simDao.copyToSimMst(sim);
			}else{
				sim.setActiveStatus("Y");
				simDao.updateTmpToSimMst(sim);
			}
		}catch(Exception ex){
			logger.error(ex);
			throw ex; 
		}
	}

	@Override
	public void updateSimTmp(Sim sim) {
		try {
			simDao.updateSimTmp(sim);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}
	
	@Override
	public void copyToSimTmp(Sim sim) {
		try {
			simDao.copyToSimTmp(sim);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}
	
	@Override
	public void copyToSimTmpDelete(Sim sim) {
		try {
			simDao.copyToSimTmpDelete(sim);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}
	
	@Override
	public DataTable<RequestSim> SearchRequestSimDataTable(SearchDataTable<RequestSim> searchDataTable) {
		DataTable<RequestSim> result = new DataTable<>();
		try {
			List<RequestSim> corps = simDao.SearchSimRequestDataTable(searchDataTable);
			result.setData(corps);
			result.setDraw(searchDataTable.getDraw());
			result.setRecordsTotal(simDao.countRequestSimTotalDataTable(searchDataTable));
			result.setRecordsFiltered(simDao.CountRequestSimDataTableFilter(searchDataTable));
		} catch (Exception e) {
    		logger.error(e);
    		throw e;
        }
		return result;
	}
	
	@Override
	public void saveRequestSim(RequestSim sim) {
		try {
			simDao.saveRequestSim(sim);
		} catch(Exception ex){
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public void cncelRequestSim(RequestSim sim) {
		try {
			simDao.cncelRequestSim(sim);
		}catch(Exception ex){
			logger.error(ex);
			throw ex; 
		}
	}
	
	@Override
	public void approveRequestSim(RequestSim sim) {
		try {
			if("C".equals(sim.getRequestStatus())) {
				simDao.deleteRequestSim(sim);
			}else if("W".equals(sim.getRequestStatus())) {
				sim.setRequestStatus("Y");
				simDao.updateRequestSim(sim);
			}
		}catch(Exception ex){
			logger.error(ex);
			throw ex; 
		}
	}

	@Override
	public int checkSimNumberBeforeRequest(String sim) {
		return simDao.checkSimNumberBeforeRequest(sim);
	}

	@Override
	public int checkDuplicateSimNumber(String sim) {
		return simDao.checkDuplicateSimNumber(sim);
	}

	@Override
	public Sim checkDuplicateSimBeforeAddSimNumber(String simNumber) {
		return simDao.checkDuplicateSimBeforeAddSimNumber(simNumber);
	}
}
