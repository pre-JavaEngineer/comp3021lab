package base;
import java.util.Date;
import java.io.Serializable;;

public class Note implements Comparable<Note>,Serializable
{
	
	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
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
	
	public int compareTo(Note o)
	{
		int output = date.compareTo(o.date);
		if (output == -1)
			return 1;
		else if (output == 1)
			return -1;
		else
			return 0;
	}
	
	public String toString()
	{
		return date.toString() + "\t" + title;
	}
	
	public String getContent()
	{
		return "Nothing";
	}
}

