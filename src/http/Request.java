package http;

import java.net.Socket;
import http.HttpRequest;
import http.HttpResponse;

public class Request implements Runnable {


	private Socket socket;

	public Request(Socket socket)
	{
		this.socket = socket;
	}

	public void run()
	{
		try {
			
			HttpRequest request = new HttpRequest(socket.getInputStream());
			HttpResponse response = new HttpResponse(request);
			
			response.writeTheOutput(socket.getOutputStream());
			socket.close();
			
		} catch (Exception e) {
			System.out.println(e + "@ run");
		}
	}
}
