package edu.uwm.cs351;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class SortedArrayMap<K,V> extends AbstractMap<K,V> implements Map<K,V>{
	private static final int INITIAL_SIZE = 10;
	
	/**
	 * Creates an entry array of a given size
	 */
	@SuppressWarnings("unchecked")
	private Entry<K, V>[] makeArray(int size) {
		SortedArrayEntry<K,V> entry = new SortedArrayEntry<K,V>(null,null);
		try {
			return (SortedArrayEntry<K, V>[]) Array.newInstance(entry.getClass(), size);
		} catch (NegativeArraySizeException e) {
			e.printStackTrace();
			return (SortedArrayEntry<K, V>[]) Array.newInstance(entry.getClass(), INITIAL_SIZE);
		}
	}
	
	/**
	 * Checks if x is an instance of K
	 */
	@SuppressWarnings("unchecked")
	private K asKey(Object x) {
		if (data[0] == null) return (K) x;
		if (x == null) return null;
		try {
			comparator.compare(data[0].getKey(),(K)x);
			comparator.compare((K)x,data[0].getKey());
			return (K)x;
		} catch (ClassCastException ex) {
			return null;
		} 
	}
	
	private Entry<K,V>[] data = makeArray(INITIAL_SIZE);
	private int numItems = 0;
	private Comparator<K> comparator;
	
	/**
	 * Using recursive binary search, search within the low and high bounds
	 * over an array to find the key. If found return the position in the
	 * array that entry is found. Else return null.
	 * 
	 * @param key, key to search for
	 * @param lo, inclusive lower bound of array to search
	 * @param hi, exclusive upper bound of array to search
	 * @return the position of entry in the array, or null if not found
	 */
	private Integer binarySearch(K key, int lo, int hi) {
		if(lo < hi) {
			int mid = (lo + hi) / 2;
			// TODO implement the binarySearch
			// Do NOT change the above lines or the return statement
			
		}
		return null;
	}
	
	
	/**
	 * Using iterative linear search, search within the low and high bounds
	 * over an array to find the key. If found return the position in the
	 * array that entry is found. Else return null.
	 * 
	 * @param key, key to search for
	 * @param lo, inclusive lower bound of array to search
	 * @param hi, exclusive upper bound of array to search
	 * @return the position of entry in the array, or null if not found
	 */
	private Integer linearSearch(K key, int lo, int hi) {
		for (int i=lo; i<hi; i++)
			if(comparator.compare(key, data[i].getKey()) == 0)
				return i;
		return null;
	}
	
	/**
	 * Returns what the private search helper method would return.
	 * This is public for the sake of testing.
	 * We're violating encapsulation for the sake of testing here!
	 * 
	 * @param key, key to search for
	 * @return the position of entry in the array, or null if not found
	 */
	public Integer getIndex(K key) {
		// TODO call binarySearch instead of linearSearch
		
		return linearSearch(key, 0, numItems);
	}
	
	/**
	 * Default constructor
	 */
	public SortedArrayMap(Comparator<K> comparator) {
		if (comparator == null) throw new IllegalArgumentException("Comparator can't be null");
		this.comparator = comparator;
	}
	
	/**
	 * Clears map
	 */
	@Override
	public void clear() {
		numItems = 0;
	}
	
	/**
	 * Checks if this map contains a specified key
	 */
	@Override
	public boolean containsKey(Object key) {
		K k = asKey(key);
		if (k == null) throw new IllegalArgumentException("key is invalid");
		
		return getIndex(k) != null;
	}
	
	/**
	 * Returns the size of the map
	 */
	@Override
	public int size() {
		return numItems;
	}
	
	/**
	 * Checks if the map is empty
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	/**
	 * Attempts to retrieve the value for a given key. If the key is not found
	 * null will be returned instead.
	 */
	@Override
	public V get(Object key) {
		K k = asKey(key);
		if (k == null) throw new IllegalArgumentException("key can't be null");
		
		Integer position = getIndex(k);
		return position == null ? null : data[position].getValue();
	}
	
	/**
	 * Adds the key the map with a given value and return null. 
	 * If the key already exists simply update the value and return the old value. 
	 */
	@Override
	public V put(K key, V value) {
		// try to find the current key if found simply change the value
		Integer position = getIndex(key);
		if (position != null) {
			return data[position].setValue(value);
		}
		
		// if the key is new, add it the array
		ensureCapacity(numItems + 1);
		
		// create new entry and find where it should be inserted
		Entry<K,V> newEntry = new SortedArrayEntry<K,V>(key,value);
		position = 0;
		while (position < numItems && comparator.compare(key, data[position].getKey()) > 0) {
			position++;
		}
		
		// shift forward
		for(int i =numItems; i > position; i--) {
			data[i] = data[i-1];
		}

		numItems++;
		data[position] = newEntry;
		
		return null;
	}
	
	/**
	 * Ensures the array can fit a given size
	 */
	private void ensureCapacity(int newCapacity) {
		if (newCapacity * 2 > data.length) {
			Entry<K,V>[] newArray = data.length * 4 + 1 > newCapacity * 2 ? 
					makeArray(data.length * 4 + 1) : makeArray(newCapacity * 2);
			System.arraycopy(data, 0, newArray, 0, numItems);
			data = newArray;
		}
	}
	
	/**
	 * Returns a set of all the entries in the map
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		return new MySet();
	}
	
	/**
	 * Private Set class, backed by the SortedArrayMap
	 */
	private class MySet extends AbstractSet<Entry<K,V>> implements Set<Entry<K,V>> {

		@Override
		public Iterator<Entry<K, V>> iterator() {
			return new MyEntryIterator();
		}

		@Override
		public int size() {
			return SortedArrayMap.this.size();
		}
		
	}
	
	/**
	 * Iterator over entries in the map
	 */
	private class MyEntryIterator implements Iterator<Entry<K, V>> {
		int cursor = -1;
		
		@Override
		public boolean hasNext() {
			return cursor + 1 < SortedArrayMap.this.size();
		}

		@Override
		public Entry<K, V> next() {
			if (!hasNext()) throw new NoSuchElementException("No next element");
			cursor++;
			return SortedArrayMap.this.data[cursor];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove is not allowed");
		}
	}
	
	@SuppressWarnings("hiding")
	private class SortedArrayEntry<K,V> implements Entry<K,V> {
		K key;
		V value;
		
		public SortedArrayEntry(K k, V v) {
			key = k;
			value = v;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V v) {
			V previous = value;
			value = v;
			return previous;
		}
		
	}
	
	/**
	 * data array setter for testing
	 */
	public void setData(Entry<K,V>[] newData) {
		data = newData;
	}
	
	/**
	 * size setter for testing
	 */
	public void setSize(int n) {
		numItems = n;
	}
	
	/**
	 * entry object creator for testing
	 */
	public Entry<K,V> makeEntry(K k, V v) {
		return new SortedArrayEntry<K,V>(k, v);
	}
}
