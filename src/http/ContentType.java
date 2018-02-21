package http;


public enum ContentType
{ 
	HTM("HTM"), HTML("HTML"), JPG("JPG"), JPEG("JPEG"), PNG("PNG");

	@SuppressWarnings("unused")
	private final String fileExtension;

	ContentType(String extension)
	{
		this.fileExtension = extension;
	}

	@Override
	public String toString() {
		
		if(this.equals(HTM) || this.equals(HTML))
		{
			return "Content-Type: text/html";
		}
		
			if(this.equals(JPG) || this.equals(JPEG))
			{
				return "Content-Type: image/jpeg";
			}
		
				if(this.equals(PNG))
				{
					return "Content-Type: image/png";
				}
					else
					{
						return null;
					}
	}
}
