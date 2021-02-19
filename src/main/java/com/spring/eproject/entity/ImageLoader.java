package com.spring.eproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ImageLoader {
	@Id
	private long id;

	private String path;

	@Lob
	private byte[] byteFile;

	private String contentType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getByteFile() {
		return byteFile;
	}

	public void setByteFile(byte[] byteFile) {
		this.byteFile = byteFile;
	}

	
	public ImageLoader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageLoader(long id, String path, byte[] byteFile, String contentType) {
		super();
		this.id = id;
		this.path = path;
		this.byteFile = byteFile;
		this.contentType = contentType;
	}

}
