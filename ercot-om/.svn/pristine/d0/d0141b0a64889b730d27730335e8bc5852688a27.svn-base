package com.softwarelikeyou.cache;

import java.util.ArrayList;
import java.util.List;

import com.softwarelikeyou.facade.FileFacade;
import com.softwarelikeyou.model.entity.File.FileType;

public class FileCache {

	@SuppressWarnings("unused")
	private static final FileCache instance = new FileCache();

	private static List<String> files = new ArrayList<String>();

	private FileCache() { }
			
	public static void initialize(final FileType type) {
		try {			
			files = FileFacade.findNamesByType(type);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getFiles() {
		return files;
	}
}
