package com.softwarelikeyou.facade;

import java.util.Date;
import java.util.List;

import com.softwarelikeyou.exception.FileException;
import com.softwarelikeyou.model.dao.FileDAO;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.File.FileType;
import com.softwarelikeyou.model.entity.File.MimeType;

public class FileFacade {		
	public static File findByName(final String name) throws FileException {
	    return new FileDAO().findByName(name);
	}
	
	public static List<String> findNamesByType(final FileType type) throws FileException {
	    return new FileDAO().findNamesByType(type);
	}
	
	public static List<File> findByType(final FileType type) throws FileException {
	    return new FileDAO().findByType(type);
	}
	
	public static List<File> findAll() throws FileException {
		return new FileDAO().findAll();
	}
	
	public static File createOrUpdate(File file) throws FileException {
		return new FileDAO().createOrUpdate(file);
	}
	
	public static void save(String name, String url, MimeType mime, FileType type) throws FileException {
    	File file = new File();
    	file.setName(name);
		file.setDownloaded(new Date());
		file.setUrl(url);
		file.setMimeType(mime);
		file.setType(type);
		file = FileFacade.createOrUpdate(file);
	}
}
