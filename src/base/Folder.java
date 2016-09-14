package base;
import java.util.ArrayList;

public class Folder 
{
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name)
	{
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note)
	{
		notes.add(note);
	}
	
	public String getName()
	{
		return name;
	}
	
	public ArrayList<Note> getNotes()
	{
		return notes;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		return true;
	}

	public String toString()
	{
		int nText = 0;
		int nImage = 0;
		for(Note n:notes) 
		{
			if(n instanceof TextNote)
			{
				nText += 1;
			}
			if(n instanceof ImageNote)
			{
				nImage += 1;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}

	
}
