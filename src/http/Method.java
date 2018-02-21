package http;

public enum Method
{
	GET("GET"), POST("POST"), PUT("PUT");

	@SuppressWarnings("unused")
	private final String method;

	Method(String method)
	{
		this.method = method;
	}
}
