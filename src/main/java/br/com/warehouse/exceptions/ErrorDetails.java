package br.com.warehouse.exceptions;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String message;
	private String details;
	private Map<String, String> erros = new HashMap<String, String>();
	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
}
