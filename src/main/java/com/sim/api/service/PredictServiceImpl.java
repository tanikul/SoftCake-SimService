package com.sim.api.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sim.api.dao.PredictDao;
import com.sim.api.model.Predict;

@Service("predictService")
public class PredictServiceImpl implements PredictService {

	private static final Logger logger = Logger.getLogger(PredictServiceImpl.class);
	
	@Autowired
	private PredictDao predictDao;
	
	@Override
	public Predict getPredictById(int id) {
		Predict pre = null;
		try {
			pre = predictDao.getPredictById(id);
		} catch(Exception ex){
			throw ex;
		}
		return pre;
	}

	@Override
	public void updatePredict(Predict predict) {
		try {
			predictDao.updatePredict(predict);
		}catch(Exception ex){
			logger.error(ex);
			throw ex; 
		}
	}

}
