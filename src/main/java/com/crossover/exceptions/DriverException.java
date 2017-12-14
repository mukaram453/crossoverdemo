package com.crossover.exceptions;


@SuppressWarnings("serial")
public class DriverException extends RuntimeException {

	
	public DriverException() {
		super();
	}

	public DriverException(String string) {
		super(string);
	}

	public DriverException(Throwable t) {
		super(t);
	}

	public DriverException(String string, Throwable t) {
		super(string, t);
	}
}
