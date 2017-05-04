package com.softwarelikeyou.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	static final int BUFFER = 2048;
	
	public static byte[] zip (final InputStream input, final String fileName) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zout = new ZipOutputStream(out);
		byte data[] = new byte[BUFFER];
        zout.putNextEntry(new ZipEntry(fileName));
		int count;
		while((count = input.read(data, 0, BUFFER)) != -1)
		    zout.write(data, 0, count);  
		zout.closeEntry();
		zout.flush();
		out.flush();
		zout.close();
		out.close();
        return out.toByteArray();
    }
	
	public static byte[] unzip(final InputStream input) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipInputStream zin = new ZipInputStream(input);
		while((zin.getNextEntry()) != null) {
            int count;
            byte data[] = new byte[BUFFER];
			BufferedOutputStream dest = new BufferedOutputStream(out, BUFFER);
			while ((count = zin.read(data, 0, BUFFER)) != -1)
		        dest.write(data, 0, count);
		    dest.flush();
		    dest.close();
		}
		out.flush();
		zin.close();
		out.close();
		return out.toByteArray();
    }
}
