package com.citi.bridge.storage;

public class StorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7666180219752250935L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
