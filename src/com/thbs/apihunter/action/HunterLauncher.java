package com.thbs.apihunter.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
/**
 * Servlet implementation class HunterLauncher
 */
@WebServlet("/HunterLauncher")
public class HunterLauncher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HunterLauncher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		if(cmd != null)
		{
			if(cmd.equals("run"))
			{
				String inputDir = System.getProperty("user.home") + "/apihunter";
				String toolCmdPath = System.getProperty("user.home") + "/apihunter-app";
				String outputDir = getServletContext().getRealPath("/");		
				boolean status = executeCmd("java -jar " + toolCmdPath + "/apihunter.jar " + inputDir + " " + outputDir +  " " + toolCmdPath + "/dataModel");

				Map<String, String> map = new HashMap<String, String>();
		        map.put("status", Boolean.toString(status));

		    	Gson gson = new Gson();
		    	String jsonString = gson.toJson(map);
		        response.getWriter().write(jsonString);
		        
			}
			else if(cmd.equals("terminate"))
			{
				
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	 private boolean executeCmd(String string) {

		 try {
	            Process aProcess = Runtime.getRuntime().exec(string);
	 
	            // These two thread shall stop by themselves when the process end
	            Thread pipeThread = new Thread(new StreamGobber(aProcess.getInputStream()));
	            Thread errorThread = new Thread(new StreamGobber(aProcess.getErrorStream()));
	 
	            pipeThread.start();
	            errorThread.start();
	 
	            aProcess.waitFor();
	            if(aProcess.exitValue() == 0)
	            {
	            	return true;
	            }
	            else
	            {
	            	return false;
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        } catch (InterruptedException ie) {
	            ie.printStackTrace();
	            return false;
	        }
	    }
	
	//Replace the following thread with your intends reader
	class StreamGobber implements Runnable {
	 
	    private InputStream Pipe;
	 
	    public StreamGobber(InputStream pipe) {
	        if(pipe == null) {
	            throw new NullPointerException("bad pipe");
	        }
	        Pipe = pipe;
	    }
	 
	    public void run() {
	        try {
	            byte buffer[] = new byte[2048];
	 
	            int read = Pipe.read(buffer);
	            while(read >= 0) {
	                System.out.write(buffer, 0, read);
	 
	                read = Pipe.read(buffer);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if(Pipe != null) {
	                try {
	                    Pipe.close();
	                } catch (IOException e) {
	                }
	            }
	        }
	    }
	}

}
