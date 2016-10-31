package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable 
{
	
	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;
	
	public NoteBook()
	{
		folders = new ArrayList<Folder>();
	}

	public boolean insertNote(String folderName, Note note)
	{
		Folder f = null;
		
		for(Folder f1 : folders)
		{
			if(f1.getName().equals(folderName))
			{
				f = f1;
			}
		}
		
		if (f == null)
		{
			f = new Folder(folderName);
			f.addNote(note);
			folders.add(f);
			return true;
		}
		boolean repeated = false;
		for (Note n : f.getNotes())
		{
		
			if (n.equals(note))
			{
				repeated = note.getTitle().equals(n.getTitle());
				if (repeated == true)
				{
					System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
					return false;
				}
			}

		}
		
		f.addNote(note);
		return true;

	}
	
	public boolean createTextNote(String folderName, String title)
	{
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title,String content)
	{
		TextNote note = new TextNote(title,content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title)
	{
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders()
	{
		return folders;
	}
	
	public Folder getFolderByName(String folderName)
	{
		for(Folder f:folders)
		{
			if(f.getName().equals(folderName))
				return f;
		}
		return null;
	}
	
	public void sortFolders()
	{
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords)
	{
		List<Note> targetNote = new ArrayList<Note>();
		for(Folder f:folders)
			targetNote.addAll(f.searchNotes(keywords));
		return targetNote;
	}
	
	public boolean save(String file)
	{
		java.io.File saveFile = new java.io.File(file);
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(saveFile);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public NoteBook(String file)
	{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try 
		{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook object= (NoteBook) in.readObject();
			folders = object.getFolders();
			in.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int getNoteNum()
	{
		int noteNum = 0;
		for(Folder f:folders)
		{
			noteNum += f.getNotes().size();
		}
		return noteNum;
	}
	
	public void addFolder(String folderName)
	{
		// TODO
		folders.add(new Folder(folderName));
	}
}
