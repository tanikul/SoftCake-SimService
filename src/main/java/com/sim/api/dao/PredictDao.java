package com.sim.api.dao;

import com.sim.api.model.Predict;

public interface PredictDao {

	public Predict getPredictById(int id);
	void updatePredict(Predict predict);
}
