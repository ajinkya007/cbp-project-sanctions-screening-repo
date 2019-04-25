package com.citi.bridge.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="uploadedFiles")
@JsonIgnoreProperties(allowGetters = true)
public class UploadedFiles {
	
	@Id
	String fileName;
	
	String dateOfUploading;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UploadedFiles() {
		super();
	}

	public String getDateOfUploading() {
		return dateOfUploading;
	}

	public void setDateOfUploading(String dateOfUploading) {
		this.dateOfUploading = dateOfUploading;
	}

	public UploadedFiles(String fileName, String dateOfUploading) {
		super();
		this.fileName = fileName;
		this.dateOfUploading = dateOfUploading;
	}

	@Override
	public String toString() {
		return "UploadedFiles [fileName=" + fileName + ", dateOfUploading=" + dateOfUploading + "]";
	}

}
