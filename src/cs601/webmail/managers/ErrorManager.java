package cs601.webmail.managers;

public class ErrorManager {
	private static ErrorManager instance;

	public static synchronized ErrorManager instance() {
		if( instance == null ) {
			instance = new ErrorManager();
		}
		return instance;
	}

	public void panic(String msg, Exception e) {
	}

	public void error(String msg) {

	}
	public void error(Exception e) {

	}
	public void error(String msg, Exception e) {
	}
}
