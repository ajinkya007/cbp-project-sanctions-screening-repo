package com.citi.bridge.storage;

public class StorageFileNotFoundException extends StorageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2909795093477438078L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}