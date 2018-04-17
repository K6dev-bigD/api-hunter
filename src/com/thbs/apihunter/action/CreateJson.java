package com.thbs.apihunter.action;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CreateJson extends HttpServlet {
        
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
    	
    	String path = System.getProperty("user.home");
        File dir=new File(path+"/apihunter/");
        if(!dir.exists()){
        	dir.mkdir();
        }
        
    	System.out.println("In CreateJson doGet");
    	System.out.println(path+"/apihunter/");
		Map<String, Map<String, Integer>> responseToWrite = new HashMap<String, Map<String,Integer>>();
		
		File folder = new File(path+"/apihunter/");
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
		File file;
		try {
			file = new File(request.getServletContext().getRealPath("/")+"filecount.js");
			fop = new FileOutputStream(file);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
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
    	
    	
    }

}