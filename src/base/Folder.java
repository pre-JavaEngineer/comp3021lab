package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>
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

	public int compareTo(Folder o)
	{
		return name.compareTo(o.name);
	}
	
	public void sortFolders()
	{
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords)
	{
		List<Note> targetNotes = new ArrayList<Note>();
		String[] keyword = keywords.split(" ");
		Boolean contain;
		Boolean containContent = false;
		for(Note note:notes)
		{
			//check the title of the note.
			String title = note.getTitle();
			contain = true;
			for(int i=0; i < keyword.length; i++)
			{
				if(keyword[i].equals("or") || keyword[i].equals("OR") || keyword[i].equals("Or") || keyword[i].equals("oR") )
				{

					if( !((title.toLowerCase().contains(keyword[i-1].toLowerCase())) 
							||(title.toLowerCase().contains(keyword[i+1].toLowerCase()) )))
					{
						contain = false;
						break;
					}
					else
						continue;
				}
				else if( !((i-1 > 0 && (keyword[i-1] == "or" || keyword[i-1] != "OR" || keyword[i-1] != "oR" || keyword[i-1] != "Or") )
						|| (i+2 < keyword.length && (keyword[i+1] == "or" || keyword[i+1] != "OR" || keyword[i+1] != "oR" || keyword[i+1] != "Or"))) )
				{
					if(!(title.toLowerCase().contains(keyword[i].toLowerCase())))
					{
						contain = false;
						break;
					}
				}
			}
			if (note instanceof TextNote)
			{
				//check the title of the note.
				String content = note.getContent();
				containContent = true;
				for(int i=0; i < keyword.length; i++)
				{
					if(keyword[i].equals("or") || keyword[i].equals("OR") || keyword[i].equals("Or") || keyword[i].equals("oR") )
					{

						if( !((content.toLowerCase().contains(keyword[i-1].toLowerCase())) 
								||(content.toLowerCase().contains(keyword[i+1].toLowerCase()) )))
						{
							containContent = false;
							break;
						}
						else
							continue;
					}
					else if( !((i-1 > 0 && (keyword[i-1] == "or" || keyword[i-1] != "OR" || keyword[i-1] != "oR" || keyword[i-1] != "Or") )
							|| (i+2 < keyword.length && (keyword[i+1] == "or" || keyword[i+1] != "OR" || keyword[i+1] != "oR" || keyword[i+1] != "Or"))) )
					{
						if(!(content.toLowerCase().contains(keyword[i].toLowerCase())))
						{
							containContent = false;
							break;
						}
					}
				}
			}
			if (contain || containContent)
			{
				targetNotes.add(note);
			}
		}
		
		return targetNotes;
	}
	
	
}
