package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {

	List<String> headerList = new ArrayList<String>();

	Method method;
	String uri;
	String version;

	public HttpRequest(InputStream is) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String str = reader.readLine();
		requestLineParser(str);

		while (!str.isEmpty())
		{
			str = reader.readLine();
			headerList.add(str);
		}
	}

	private void requestLineParser(String str)
	{
		String[] split = str.split("\\s+");
		
		try {
			
			method = Method.valueOf(split[0]);
			
		} catch (Exception e) {
			System.out.println("INVALID");
		}
		
		uri = split[1];
		version = split[2];
	}
}
