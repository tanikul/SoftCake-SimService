package com.sim.api.dao;

import com.sim.api.model.ErrorMessage;

public interface ErrorMessageDao {

	public ErrorMessage getErrorMsgByCode(String errorCode);
}
