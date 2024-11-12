import java.util.Comparator;

import edu.uwm.cs351.SortedArrayMap;
import junit.framework.TestCase;


public class TestSortedArrayMap extends TestCase {
	SortedArrayMap<String, Integer> sam = new SortedArrayMap<String, Integer>(new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}});
	
	public void testEmpty() {
		assertEquals(null, sam.getIndex("a"));
	}
	
	public void testOne() {
		sam.put("a", 0);
		assertEquals((Integer)0, sam.getIndex("a"));
		assertEquals(null, sam.getIndex("b"));
	}
	
	public void testTwo() {
		sam.put("a", 0);
		sam.put("b", 0);
		assertEquals((Integer)0, sam.getIndex("a"));
		assertEquals((Integer)1, sam.getIndex("b"));
		assertEquals(null, sam.getIndex("c"));	
	}
	
	public void testThree() {
		sam.put("a", 0);
		sam.put("b", 0);
		sam.put("c", 0);
		assertEquals((Integer)0, sam.getIndex("a"));
		assertEquals((Integer)1, sam.getIndex("b"));
		assertEquals((Integer)2, sam.getIndex("c"));
		assertEquals(null, sam.getIndex("d"));	
	}
	
	public void testFour() {
		sam.put("a", 0);
		sam.put("b", 0);
		sam.put("c", 0);
		sam.put("d", 0);
		assertEquals((Integer)0, sam.getIndex("a"));
		assertEquals((Integer)1, sam.getIndex("b"));
		assertEquals((Integer)2, sam.getIndex("c"));
		assertEquals((Integer)3, sam.getIndex("d"));
		assertEquals(null, sam.getIndex("e"));	
	}
}
