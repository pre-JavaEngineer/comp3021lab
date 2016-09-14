package base;
import java.util.Date;
public class Note
{
	
	private Date date;
	private String title;
	
	public Note(String title)
	{
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle()
	{
		return title;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Note other = (Note) obj;
		if (title == null) 
		{
			if (other.title != null)
			{
				return false;
			}
		} 
		else if (!title.equals(other.getTitle()))
		{
			return false;
		}
		return true;
	}
	

}

