import edu.uwm.cs351.WordCounter;
import junit.framework.TestCase;


public class TestWordCounter extends TestCase {
	WordCounter wf = new WordCounter();

	public void testEmpty() {
		assertEquals(0,wf.getCount("Goodbye"));
		String expected = "";
		assertEquals(expected, wf.getContents());
	}	
	
	public void testCountOne() {
		wf.addWords("Hello my name is Hello");
		
		assertEquals(4,wf.numberDistinctWords());
		assertEquals(2,wf.getCount("Hello"));
		assertEquals(1,wf.getCount("my"));
		assertEquals(0,wf.getCount("Goodbye"));
		
		String expected = "Hello : 2\nis : 1\nmy : 1\nname : 1\n";
		assertEquals(expected, wf.getContents());
	}
	
	
	public void testCountTwice() {
		wf.addWords("Hello my name is Hello");
		wf.addWords("Hello my name is Hello");
		
		assertEquals(4,wf.numberDistinctWords());
		assertEquals(4,wf.getCount("Hello"));
		assertEquals(2,wf.getCount("my"));
		assertEquals(0,wf.getCount("Goodbye"));
		
		String expected = "Hello : 4\nis : 2\nmy : 2\nname : 2\n";
		assertEquals(expected, wf.getContents());
	}
	
	public void testCountTwo() {
		wf.addWords("Hello my name is Hello");
		wf.addWords("Good morning my name is Goodbye");
		
		assertEquals(7,wf.numberDistinctWords());
		assertEquals(2,wf.getCount("Hello"));
		assertEquals(2,wf.getCount("my"));
		assertEquals(1,wf.getCount("morning"));
		assertEquals(1,wf.getCount("Goodbye"));
		assertEquals(0,wf.getCount("has"));
		
		String expected = "Good : 1\nGoodbye : 1\nHello : 2\nis : 2\nmorning : 1\nmy : 2\nname : 2\n";
		assertEquals(expected, wf.getContents());
	}
}
