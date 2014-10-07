package cs601.webmail.pages;

import cs601.webmail.managers.ErrorManager;
import cs601.webmail.misc.VerifyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class Page {
	HttpServletRequest request;
	HttpServletResponse response;
	PrintWriter out;
	int pageNum;

	public Page(HttpServletRequest request,
				HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		try {
			out = response.getWriter();
		}
		catch (IOException ioe) {
			ErrorManager.instance().error(ioe);
		}
	}

	public void verify() throws VerifyException {
		// handle default args like page number, etc...
		// verify that arguments make sense
		// implemented by subclass typically
		// VerifyException is a custom Exception subclass
	}

	public void handleDefaultArgs() {
		// handle default args like page number, etc...
		String pageStr = request.getParameter("page");
		if ( pageStr!=null ) {
			pageNum = Integer.valueOf(pageStr);
		}
	}

	public void generate() {
		handleDefaultArgs();
		try {
			verify(); // check args before generation
			header();
			body();
			footer();
		}
		catch (VerifyException ve) {
			try {
				response.sendRedirect("/files/error.html");
			}
			catch (IOException ioe) {
				ErrorManager.instance().error(ioe);
			}
		}
		catch (Exception e) {
			ErrorManager.instance().error(e);
		}
		finally {
			out.close();
		}
	}

	public void header() {
		out.println("<html>");
		out.println("<body>");
	}

	public abstract void body();

	public void footer() {
		out.println("</body>");
		out.println("</html>");
	}
}
