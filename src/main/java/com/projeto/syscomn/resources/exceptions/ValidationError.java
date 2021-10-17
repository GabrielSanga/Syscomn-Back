package com.projeto.syscomn.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> lstErrors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getLstErrors() {
		return lstErrors;
	}

	public void addError(String fieldName, String message) {
		this.lstErrors.add(new FieldMessage(fieldName, message));
	}

}
