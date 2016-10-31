package base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;


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
		String root = "C:\\Users\\Fong\\git\\comp3021lab";
		String title = getTitle().replaceAll(" ","_");
		if(pathFolder.equals(""))
		{
			pathFolder = root;
		}
		else if( (pathFolder.length() < 3 || !pathFolder.substring(0, 3).equals("C:\\")) && !pathFolder.substring(0, 1).equals("\\") )
		{
			pathFolder = root + File.separator + pathFolder;
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
	
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) 
		{
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') 
			{
				if (!count.containsKey(c))
				{
					count.put(c, 1);
				} 
				else 
				{
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) 
					{
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}
	
	public void setContent(String inputContent)
	{
		content = inputContent; 
	}
}
