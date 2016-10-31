package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;;

public class Folder implements Comparable<Folder>,Serializable
{
	
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public Folder(String name)
	{
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note)
	{
		notes.add(note);
	}
	
	public boolean removeNotes(String title) 
	{
		// TODO
		// Given the title of the note, delete it from the folder.
		// Return true if it is deleted successfully, otherwise return false.
		return notes.remove(getNoteByTitle(title));
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
	
	public Note getNoteByTitle(String title)
	{
		for(Note n:notes)
		{
			if(n.getTitle().equals(title))
				return n;
		}
		return null;
	}
	
	public List<Note> searchNotes(String keywords)
	{
		List<Note> targetNotes = new ArrayList<Note>();
		String[] keyword = keywords.split(" ");

		for(Note note:notes)
		{
			Boolean containTitle = true;
			Boolean containContent = true;
			
			//First check if the title of each note contains the keywords
			for(int i = 0; i < keyword.length; i++)
			{
				//keyword[i] is not associated with "or", so it must be contained in the Note
				if(!keyword[i].toLowerCase().equals("or") && !( ( i-1 > 0 && keyword[i-1].toLowerCase().equals("or") ) || ( i+2 < keyword.length && keyword[i+1].toLowerCase().equals("or") ) ) )
				{
					if (!note.getTitle().toLowerCase().contains(keyword[i].toLowerCase()))
					{
						containTitle = false;
					}
				}
			
				//keyword[i] has or-relationship with another(other) keyword(s)
				//Firstly, check how many keyword[n](n<i) having or-relationship with keyword[i]
				if( ( i-1 > 0 && keyword[i-1].toLowerCase().equals("or") ) && !( i+2 < keyword.length && keyword[i+1].toLowerCase().equals("or") ) )
				{
					int numOfKeyWordInChain = 1;
					boolean allNotContain = true;
					for(int k = i-1; k > 0; k -= 2)
					{
						if(keyword[k].equals("or") || keyword[k].equals("OR") || keyword[k].equals("oR") || keyword[k].equals("Or"))
							numOfKeyWordInChain += 1;
					}

					for(int j = numOfKeyWordInChain - 1, m = 0; j >= 0; j--, m += 2)
					{
						if(note.getTitle().toLowerCase().contains(keyword[i-m].toLowerCase()))
							allNotContain = false;
						
					}
					if(allNotContain)
						containTitle = false;
				}
				
				//Secondly, check if the note is a text note, if so check the content if it contain the keywords
				if(note instanceof TextNote)
				{
					if(!keyword[i].toLowerCase().equals("or") && !( ( i-1 > 0 && keyword[i-1].toLowerCase().equals("or") ) || ( i+2 < keyword.length && keyword[i+1].toLowerCase().equals("or") ) ) )
					{
						if (!note.getContent().toLowerCase().contains(keyword[i].toLowerCase()))
							{
								containContent = false;
							}
						}
				
					//keyword[i] has or-relationship with another(other) keyword(s)
					//Firstly, check how many keyword[n](n<i) having or-relationship with keyword[i]
					if( ( i-1 > 0 && keyword[i-1].toLowerCase().equals("or") ) && !( i+2 < keyword.length && keyword[i+1].toLowerCase().equals("or") ) )
					{
						int numOfKeyWordInChain = 1;
						boolean allNotContain = true;
						for(int k = i-1; k > 0; k -= 2)
						{
							if(keyword[k].equals("or") || keyword[k].equals("OR") || keyword[k].equals("oR") || keyword[k].equals("Or"))
								numOfKeyWordInChain += 1;
						}

						for(int j = numOfKeyWordInChain - 1, m = 0; j >= 0; j--, m += 2)
						{
							if(note.getContent().toLowerCase().contains(keyword[i-m].toLowerCase()))
								allNotContain = false;
							
						}
						if(allNotContain)
							containContent = false;
					}
				}
				else 
					containContent = false;
			}
			if (containTitle || containContent)
				targetNotes.add(note);
		}
		return targetNotes;
	}
}
