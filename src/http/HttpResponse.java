package http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HttpResponse
{

	public static final String VERSION = "HTTP/1.0";
	private static final String CRLF = "\r\n";

	List<String> headerList = new ArrayList<String>();

	byte[] body;

	public HttpResponse(HttpRequest request) throws IOException
	{
		if (request.method == Method.GET)
		{
			headerList.add(HttpResponse.VERSION + " " + Status.OK.toString());
					
					File file = new File("." + request.uri);
					
					if (file.isDirectory())
					{
						headerList.add(ContentType.HTML.toString());
						StringBuilder sb = new StringBuilder("<html><head><title>Index of ");
						sb.append(request.uri);
						sb.append("</title></head><body><h1>Index of ");
						sb.append(request.uri);
						sb.append("</h1><hr><pre>");
						
						File[] files = file.listFiles();
						for (File subfile : files)
						{
							sb.append(" <a href=\"" + subfile.getPath() + "\">" + subfile.getPath() + "</a>\n");
						}
						sb.append("<hr></pre></body></html>");
						body = sb.toString().getBytes();
					}
					else if (file.exists())
					{					
						try {
							
							String ext = request.uri.substring(request.uri.indexOf(".") + 1);
							headerList.add(ContentType.valueOf(ext.toUpperCase()).toString());
							
						} catch (Exception e) {

						}
						
						int length = (int) file.length();
						body = new byte[length];
						InputStream in = new FileInputStream(file);
						int offset = 0;
						while (offset < length)
						{
							int count = in.read(body, offset, (length - offset));
							offset += count;
						}
						
						in.close();
					}
					else
					{
						headerList.add(HttpResponse.VERSION + " " + Status.NOT_FOUND.toString());
						body = Status.NOT_FOUND.toString().toString().getBytes();					
					}			
		}

	}

	public void writeTheOutput(OutputStream os) throws IOException
	{
		DataOutputStream dos = new DataOutputStream(os);
		
		for (String header : headerList)
		{
			dos.writeBytes(header + CRLF);
		}
		
		dos.writeBytes("\r\n");
			
			if (body != null)
			{
				dos.write(body);
			}
			
			dos.writeBytes(CRLF);
			dos.flush();
	}

}
