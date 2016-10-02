package base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note 
{
	
	private String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote(String title)
	{
		super(title);
	}
	
	public TextNote(String title, String content)
	{
		super(title);
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public TextNote(File f)
	{
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath)
	{
		String result = "";
		FileInputStream fis = null;
		BufferedReader buf = null;
		try 
		{
			fis = new FileInputStream(absolutePath);
			buf = new BufferedReader(new InputStreamReader(fis)); 
			result += buf.readLine();
			buf.close();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder)
	{
		String title = getTitle().replaceAll(" ","_");
		if(pathFolder.equals(""))
		{
			pathFolder = "C:\\Users\\Fong\\git\\comp3021lab";
		}
		else if( (pathFolder.length() < 3 || !pathFolder.substring(0, 3).equals("C:\\")) && !pathFolder.substring(0, 1).equals("\\") )
		{
			pathFolder = "C:\\Users\\Fong\\git\\comp3021lab" + File.separator + pathFolder;
		}
		File file = new File( pathFolder + File.separator + title + ".txt");
		FileWriter fw = null;
		BufferedWriter buf = null;
		try
		{
			fw = new FileWriter(file);
			buf = new BufferedWriter(fw);
			buf.write(content);
			buf.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
}
