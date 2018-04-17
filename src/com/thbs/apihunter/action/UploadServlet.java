package com.thbs.apihunter.action;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class UploadServlet extends HttpServlet {
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	/**
        * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("In doGet");
        
        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
        	System.out.println("filename : "+request.getParameter("getfile"));
        	String path = System.getProperty("user.home");
            File dir=new File(path+"/apihunter");
            if(!dir.exists()){
            	dir.mkdir();
            }
        	
            File file = new File(path+"/apihunter/"+request.getParameter("getfile"));
            if (file.exists()) {
                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file));
                response.setContentLength((int) file.length());
                response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            }
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
        	String path = System.getProperty("user.home");
            File dir=new File(path+"/apihunter/");
            if(!dir.exists()){
            	dir.mkdir();
            }
            
            File file = new File(path+"/apihunter/"+ request.getParameter("delfile"));
            if (file.exists()) {
                file.delete(); // TODO:check and report success
            }
            
            //Code to regenerate json file in context path
            Map<String, Map<String, Integer>> responseToWrite = new HashMap<String, Map<String,Integer>>();
    		
    		File folder = new File(path+"/apihunter");
        	File[] listOfFiles = folder.listFiles();
        	int countForBpmn = 0;
        	int countForDoc = 0;
        	for (int i = 0; i< listOfFiles.length; i++)
        	{
        		if (listOfFiles[i].getName().contains(".bpmn"))
        		{
        			countForBpmn++;
        		}else{
        			countForDoc++;
        		}
          	}
        	
        	Map<String, Integer> temp = new HashMap<String, Integer>();
        	for (int i = 0; i < listOfFiles.length; i++) {
        		temp.put("RequirementDocs", countForDoc);
        		temp.put("BusinessProcesses", countForBpmn);
      	    }
        	responseToWrite.put("filesCount", temp);
        	
        	FileOutputStream fop = null;
    		File file1;
    		try {
    			file1 = new File(request.getServletContext().getRealPath("/")+"filecount.js");
    			fop = new FileOutputStream(file1);
    			// if file doesnt exists, then create it
    			if (!file1.exists()) {
    				file1.createNewFile();
    			}
    			String content = "var temp = ";
    			// get the content in bytes
    			Gson gson = new Gson();
    			byte[] contentInBytes = (content+gson.toJson(responseToWrite)).getBytes();
    			fop.write(contentInBytes);
    			fop.flush();
    			fop.close();
    			System.out.println("File Done");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            
            
            
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
        	String path = System.getProperty("user.home");
            File dir=new File(path+"/apihunter/");
            if(!dir.exists()){
            	dir.mkdir();
            }
            
            File file = new File(path+"/apihunter"+ request.getParameter("getthumb"));
                if (file.exists()) {
                    System.out.println(file.getAbsolutePath());
                    String mimetype = getMimeType(file);
                    if (mimetype.endsWith("png") || mimetype.endsWith("jpeg")|| mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
                        BufferedImage im = ImageIO.read(file);
                        if (im != null) {
                            BufferedImage thumb = Scalr.resize(im, 75); 
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            if (mimetype.endsWith("png")) {
                                ImageIO.write(thumb, "PNG" , os);
                                response.setContentType("image/png");
                            } else if (mimetype.endsWith("jpeg")) {
                                ImageIO.write(thumb, "jpg" , os);
                                response.setContentType("image/jpeg");
                            } else if (mimetype.endsWith("jpg")) {
                                ImageIO.write(thumb, "jpg" , os);
                                response.setContentType("image/jpeg");
                            } else {
                                ImageIO.write(thumb, "GIF" , os);
                                response.setContentType("image/gif");
                            }
                            ServletOutputStream srvos = response.getOutputStream();
                            response.setContentLength(os.size());
                            response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
                            os.writeTo(srvos);
                            srvos.flush();
                            srvos.close();
                        }
                    }
            } // TODO: check and report success
        } else if(request.getParameter("getfiles") != null && !request.getParameter("getfiles").isEmpty()){
        	String path = System.getProperty("user.home");
            File dir=new File(path+"/apihunter/");
            if(!dir.exists()){
            	dir.mkdir();
            }
            
        	PrintWriter writer = response.getWriter();
        	File folder = new File(path+"/apihunter/");
        	System.out.println("Path : "+path+"/apihunter/");
        	File[] listOfFiles = folder.listFiles();
        	response.setContentType("text/json");
        	
        	Gson gson = new Gson();
        	
        	Arrays.sort(listOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            
        		List<Fileinfo> fileinfos = new ArrayList<Fileinfo>();
        	    for (int i = 0; i < listOfFiles.length; i++) {
                  Fileinfo fileinfo = new Fileinfo();
        	      if (listOfFiles[i].isFile()) {
        	        System.out.println("File " + listOfFiles[i].getName());
        	        fileinfo.setFileName(listOfFiles[i].getName());
        	        fileinfo.setFileSize(listOfFiles[i].length());
        	        fileinfos.add(fileinfo);
        	      } else if (listOfFiles[i].isDirectory()) {
        	        System.out.println("Directory " + listOfFiles[i].getName());
        	        fileinfo.setFileName(listOfFiles[i].getName());
        	        fileinfo.setFileSize(listOfFiles[i].length());
        	        fileinfos.add(fileinfo);
        	      }
        	    }
        	 
        	 Map<String, List<Fileinfo>> map = new HashMap<String, List<Fileinfo>>();
        	 map.put("files", fileinfos);
        	 String jsonString = gson.toJson(map);
             writer.write(jsonString);
        }else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
    }
    
    /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = System.getProperty("user.home");
        File dir=new File(path+"/apihunter");
        if(!dir.exists()){
        	dir.mkdir();
        }
        System.out.println("Path : "+path);
        
    	System.out.println("In doPost");
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                        File file = new File(path+"/apihunter/", item.getName());
                        item.write(file);
                        JSONObject jsono = new JSONObject();
                        jsono.put("name", item.getName());
                        jsono.put("size", item.getSize());
                        jsono.put("url", "UploadServlet?getfile=" + item.getName());
                        jsono.put("thumbnail_url", "UploadServlet?getthumb=" + item.getName());
                        jsono.put("delete_url", "UploadServlet?delfile=" + item.getName());
                        jsono.put("delete_type", "GET");
                        json.put(jsono);
                        System.out.println(json.toString());
                }
            }
        } catch (FileUploadException e) {
                throw new RuntimeException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }

    }

    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpg")){
                mimetype = "image/jpg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpeg")){
                mimetype = "image/jpeg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("gif")){
                mimetype = "image/gif";
            }else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }



    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }
}