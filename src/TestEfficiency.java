import edu.uwm.cs351.SortedArrayMap;
import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Map.Entry;

public class TestEfficiency extends TestCase {
	
	private SortedArrayMap<Integer, Boolean> map = new SortedArrayMap<Integer, Boolean>(
			new Comparator<Integer>() {
				@Override
				public int compare(Integer s1, Integer s2) {
					return s1.compareTo(s2);
				}
			}
	);
	
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception{
		super.setUp();
		
		try {
			assert map.size() == 0 : "cannot run test with assertions enabled";
		} catch (NullPointerException ex) {
			System.out.println("Go to Run > Run Configurations");
			System.out.println("Then go to the 'Arguments' tab.");
			System.out.println("Remove '-ea' from the VM Arguments box.");
			throw new IllegalStateException("Cannot run test with assertions enabled");
		}
		
		Entry<Integer,Boolean> entry = map.makeEntry(null, null);
		Entry<Integer,Boolean>[] data = (Entry<Integer,Boolean>[]) Array.newInstance(entry.getClass(), 1000000);
		for (int i=0; i<1000000; i++) {
			data[i] = map.makeEntry(i, true);
		}
		map.setData(data);
		map.setSize(1000000);
	}
	
	public void testGetIndex() {
		for (int i=999999; i>=0; i--) {
			assertNotNull(map.getIndex(i));
		}
	}
	
}
