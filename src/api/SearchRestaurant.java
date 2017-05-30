package api;
//调yelpAPI来获取信息
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchRestaurant
 */
@WebServlet(name = "SearchRestaurants", urlPatterns = { "/restaurants" })
public class SearchRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchRestaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private DBConnection connection = new MySQLDBConnection();//启动mongo 用MongoDBConnection()

	protected void doGet(HttpServletRequest request, HttpServletResponse response)//response是返回的，在这个位置可以写好多次不是只有一次就结束
			throws ServletException, IOException {

		/**response.setContentType("application/json");//返回的数据类型
		response.addHeader("Access-Control-Allow-Origin", "*");//运行谁访问，"*"所有人都可以看见
		String username = "";
		PrintWriter out = response.getWriter();
		if (request.getParameter("username") != null) {//往out里学东西
			username = request.getParameter("username");
			out.print("Hello " + username);
		}
		out.flush();//返回前端
		out.close();//关掉response
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>This is a HTML page</h1>");
		out.println("</body></html>");
		out.flush();
		out.close();
		
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		String username = "";
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("username", username);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.flush();
		out.close();
		
		JSONArray array = new JSONArray();
		try {
			if (request.getParameterMap().containsKey("user_id")
					&& request.getParameterMap().containsKey("lat")
					&& request.getParameterMap().containsKey("lon")) {
				String userId = request.getParameter("user_id");
				double lat = Double.parseDouble(request.getParameter("lat"));
				double lon = Double.parseDouble(request.getParameter("lon"));
				// return some fake restaurants
				array.put(new JSONObject().put("name", "Panda Express"));
				array.put(new JSONObject().put("name", "Hong Kong Express"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RpcParser.writeOutput(response, array);
		*/
		JSONArray array = new JSONArray();
		if (request.getParameterMap().containsKey("lat")
				&& request.getParameterMap().containsKey("lon")) {
			// term is null or empty by default
			String term = request.getParameter("term");
			//String userId = (String) session.getAttribute("user");
                                             String userId = "1111";
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lon = Double.parseDouble(request.getParameter("lon"));
			array = connection.searchRestaurants(userId, lat, lon, term);
		}
		RpcParser.writeOutput(response, array);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
