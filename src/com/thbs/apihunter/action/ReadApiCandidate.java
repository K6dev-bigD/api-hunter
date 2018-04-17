package com.thbs.apihunter.action;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;


public class ReadApiCandidate extends HttpServlet {
        
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
    	response.setContentType("text/json");
    	PrintWriter writer = response.getWriter();
    	
    	JSONParser parser = new JSONParser();
    	Object obj = null;
		try {
			obj = parser.parse(new FileReader(request.getServletContext().getRealPath("/")+"apicandidates.json"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONObject jsonObject =  (JSONObject) obj;
        String flagFirst = (String) jsonObject.get("flagFirst");
//        System.out.println(flagFirst);
        String number = (String) jsonObject.get("number");
//        System.out.println(number);
        
        Map<String, String> temp = new HashMap<String, String>();
        temp.put("flagFirst",flagFirst);
        temp.put("number",number);

    	Gson gson = new Gson();
    	String jsonString = gson.toJson(temp);
        writer.write(jsonString);
    	
    }

}