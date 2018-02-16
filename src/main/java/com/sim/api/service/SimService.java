package com.sim.api.service;

import java.util.List;

import com.sim.api.model.ResultDataSim;
import com.sim.api.model.Sim;
import com.sim.api.model.User;

public interface SimService {

	public ResultDataSim SearchSim(int page, Sim sim);
}
