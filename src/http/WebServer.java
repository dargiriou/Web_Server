package http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.ServerSocket;

public class WebServer extends Thread {

	private static final int DEFAULT_PORT = 8888;

	private static final int THREADS = 5;

	public static void main(String args[])
	{
		try {
			new WebServer().start(portValidation(args));
		} catch (Exception e) {
			System.out.println(e + "@ start of main.");
		}
	}

	public void start(int port) throws IOException
	{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(port);
		
		System.out.println("Server has started on port: " + port);
		
		ExecutorService exec = Executors.newFixedThreadPool(THREADS);
		
		while (true)
		{
			exec.submit(new Request(ss.accept()));
		}
	}

	static int portValidation(String args[]) throws NumberFormatException
	{
		if (args.length > 0)
		{			
			if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < 65535)
			{
				return Integer.parseInt(args[0]);
			} 
				else 
				{
					throw new NumberFormatException("Invalid port number!");
				}
		}
		return DEFAULT_PORT;
	}
}
