package br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions;

public class DataIntegrityException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg,Throwable cause) {
		super(msg,cause);
	}
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

}
