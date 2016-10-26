package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import base.Note;
import base.NoteBook;
import base.TextNote;

public class JUnitTest {

	@Test
	public void testSearchNote() {
		NoteBook nb = new NoteBook();
		nb.createTextNote("Note1", "Java", "comp3021");
		nb.createTextNote("Note2", "Assignment", "due on 2016-10-16");
		nb.createTextNote("Note3", "lab","need to attend weekly");
		nb.createTextNote("Note4", "lab4","testing");
		List<Note> notes = nb.searchNotes("java or DUE or testing or lab4");
		System.out.println(notes.size());
		assertEquals("The size of the search results is not match", 3, notes.size(), 0.0);
		HashSet<String> titles = new HashSet<String>();
		for (Note note : notes) {
			titles.add(note.getTitle());
		}
		HashSet<String> expectedOutputs = new HashSet<String>();
		expectedOutputs.add("Java");
		expectedOutputs.add("Assignment");
		expectedOutputs.add("lab4");
		assertEquals("The search results is not match", expectedOutputs, titles);
	}
	
	// To do
	// Design the second test case which reveals the bug in function unknownFunction()
	@Test
	public void testCountLetters()
	{
		TextNote tn1 = new TextNote("Java Programming: ", "Java is the most common and promising programming language nowadays. Thus, it is rational to master Java to cater the needs of the IT market.");
		Integer expectedLetterCount_1 = 129;
		System.out.println(tn1.countLetters());
		assertEquals("The letter counting result is not match", expectedLetterCount_1, tn1.countLetters(), 0.000001);
		
		TextNote tn2 = new TextNote("Second test: ", "xxxxxxxxxxxxxxxxx vs yyyyyyyyyyyyyyyyy, both x and y appears 18 times.");
		Integer expectedLetterCount_2 = 69;
		System.out.println(tn2.countLetters());
		assertEquals("The letter counting result is not match", expectedLetterCount_2, tn2.countLetters(), 0.000001);
	}
	


}
