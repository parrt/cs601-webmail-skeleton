package cs601.webmail.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage extends Page {
	public HomePage(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public void verify() { }

	@Override
	public void header() {
		out.println("<html>");
		out.println("<body>");
	}

	@Override
	public void body() {
		out.println("Home page");
	}

	@Override
	public void footer() {
		out.println("</body>");
		out.println("</html>");
	}
}
