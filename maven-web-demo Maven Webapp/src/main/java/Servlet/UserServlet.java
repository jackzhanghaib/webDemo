package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Response;

/** 
 * @ClassName: UserServlet 
 * @Description: TODO
 * @author zhb
 * @date 2018-3-31 ÉÏÎç12:01:23 
 * @type 
 */

public class UserServlet extends HttpServlet{
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if("getProvince".equals(action)){
			this.getProvince(req,resp);
		}else if("getCity".equals(action)){
			this.getCity(req,resp);
		}
	}
	public void getProvince(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String result = "";
		CityMap citymap = new CityMap();
		Map<String,String[]> map = citymap.model;
		Set<String> set = map.keySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			result = result+it.next()+",";
		}
		result = result.substring(0,result.length()-1);
		resp.setContentType("text/html");
		PrintWriter out= resp.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	public void getCity(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String result = "";
		String selProvince = new String(req.getParameter("parProcince").getBytes("UTF-8"),"GBK");
		CityMap citymap = new CityMap();
		Map<String,String[]> map = citymap.model;
		Set<String> set = map.keySet();
		String[] arrCity = map.get(selProvince);
		for(int i = 0;i<arrCity.length;i++){
			result = result+arrCity[i]+",";
		}
		result = result.substring(0,result.length()-1);
		resp.setContentType("text/html");
		PrintWriter out= resp.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
}


