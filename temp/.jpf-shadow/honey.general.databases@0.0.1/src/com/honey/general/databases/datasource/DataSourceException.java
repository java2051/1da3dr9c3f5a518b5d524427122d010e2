package com.honey.general.databases.datasource;

import com.honey.core.exception.HoneyException;

public class DataSourceException extends HoneyException{

	
	public DataSourceException(String message) {
		super(message);
		
	}
	public DataSourceException(Exception e) {
		super(e.getMessage());
	}

	private static final long serialVersionUID = 1L;

	
	
}
