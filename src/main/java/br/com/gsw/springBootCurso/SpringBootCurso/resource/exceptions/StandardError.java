package br.com.gsw.springBootCurso.SpringBootCurso.resource.exceptions;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Calendar timeStamp;
	
	public Calendar getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Calendar timeStamp) {
		
		this.timeStamp = timeStamp;
	}
	public String getMsg()  {
		
				
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public StandardError(Integer status, String msg, Calendar l) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = l;
	}
	
	
	
}
