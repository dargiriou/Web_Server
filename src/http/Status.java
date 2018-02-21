package http;

public enum Status
{
	OK("200 OK"), FOUND("302 Found"), FORBIDDEN("403 Forbidden"), NOT_FOUND("404 Not Found"), INTERNAL_ERROR("500 Internal Server Error");

	private final String status;

	Status(String status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return status;
	}
}
