package com.softwarelikeyou.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softwarelikeyou.util.FileUtil;
import com.softwarelikeyou.util.ResourceUtil;

import static java.nio.file.StandardCopyOption.*;

public class FileArchiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private PrintWriter writer = null;

    private SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
    
    public FileArchiveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/xml");
		writer = response.getWriter();
		writer.write("<?xml version=\"1.0\"?>" + "\n");
		try {
			String type = request.getParameter("type");
			String file = request.getParameter("file");
			if (type == null || file == null)
				writer.write("<error>" + "Type and file cannot be empty" + "</error>" + "\n");
			else {
				String date = FileUtil.getDate(file);
				if (date.length() != 8)
					date = format.format(new Date());
				String targetDirectory = ResourceUtil.get().getString("ercot." + FileUtil.getOSType()) + FileUtil.getOSType().getSlash() + "ercot" + FileUtil.getOSType().getSlash() + "archive" + FileUtil.getOSType().getSlash() + type + FileUtil.getOSType().getSlash() + date;
                File directory = new File(targetDirectory);
                if (!directory.exists())
                	directory.mkdirs();
                
		        String sourceString = ResourceUtil.get().getString("ercot." + FileUtil.getOSType()) + FileUtil.getOSType().getSlash() + "ercot" + FileUtil.getOSType().getSlash() + type + FileUtil.getOSType().getSlash() + file;
		        String targetString = targetDirectory + FileUtil.getOSType().getSlash() + file;
                Path source = Paths.get(sourceString);
                Path target = Paths.get(targetString);
                Files.move(source, target, REPLACE_EXISTING);
                
                sourceString = sourceString.replaceAll("csv", "xml");
                targetString = targetString.replaceAll("csv", "xml");
                source = Paths.get(sourceString);
                target = Paths.get(targetString);
                Files.move(source, target, REPLACE_EXISTING);

                writer.write("<success>" + "Archived" + "</success>" + "\n");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			writer.write("<error>" + e.getMessage() + "</error>" + "\n");
		}
		finally {
		    writer.flush();
			writer.close();
		}
	}
}
