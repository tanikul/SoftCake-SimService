package com.sim.api.service;

import com.sim.api.model.Predict;

public interface PredictService {

	public Predict getPredictById(int id);
	void updatePredict(Predict predict);
}
