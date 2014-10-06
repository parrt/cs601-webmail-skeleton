package cs601.webmail.pages;

import cs601.webmail.managers.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DispatchServlet extends HttpServlet {
	public static Map<String,Class> mapping = new HashMap<String, Class>();
	static {
		mapping.put("/", HomePage.class);
		mapping.put("/news", NewsPage.class);
	}

	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
		throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		Page p = createPage(uri, request, response);
		if ( p==null ) {
			response.sendRedirect("/files/error.html");
			return;
		}
		response.setContentType("text/html");
		p.generate();
	}

	public Page createPage(String uri,
						   HttpServletRequest request,
						   HttpServletResponse response)
	{
		Class pageClass = mapping.get(uri);
		try {
			Constructor<Page> ctor = pageClass.getConstructor(HttpServletRequest.class,
															  HttpServletResponse.class);
			return ctor.newInstance(request, response);
		}
		catch (Exception e) {
			ErrorManager.instance().error(e);
		}
		return null;
	}

}
