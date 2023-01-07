package gr.hua.dit.datastructures;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashTable<K, V> implements Dictionary<K,V> {

	public static final int DEFAULT_INITIAL_SIZE = 200;
	
    private Entry<K, V>[] array;
    private  int m[][];
    private int size;
    private static int b;
    

    @SuppressWarnings("unchecked")
	public HashTable(int m) {
        if (m <= 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        this.size = 0;
        this.array = (Entry<K, V>[]) Array.newInstance(Entry.class, m);
        
        for (int i=0; i<m; i++) {
            this.array[i] = null;
        }
    }

    
    /**
     * Insert new entries in table
     */
	@Override
	public void put(K key, V value) {
		
		if (size == array.length) {
			doubleCapacity();
        }
    }
	
	
	/**
	 * Remove entries from table
	 */
	@Override
	public V remove(K key) {
		
		if (isEmpty() == true) {
			throw new IllegalArgumentException("Table is empty");
		}
		
		if (size <= array.length/4) {
			halfCapacity();
		}
		
		return null;
	}

	
	/**
	 * Get element associated with specified key
	 */
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 
	 */
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * Get current hash size
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	
	/**
	 * Get current hash size
	 */
	@Override
	public int size() {
		return size;
	}

	
	/**
	 * Remove all elements from array
	 */
	@Override
	public void clear() {
		size = 0;
        for (int i=0; i<size; i++) {
            array[i] = null;
        }
	}

	
	/**
	 * Iterate all elements of array
	 * @return HashIterator
	 */
	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new HashIterator();
	}

	
	/**
     * Creating HashIterator object
     */
    private class HashIterator implements Iterator<Entry<K, V>> {
   
    
    	private Iterator<Entry<K, V>> current;

	    public HashIterator() {
	        current = ((Dictionary<K, V>) array[0]).iterator();
	    }
	
	        @Override
	        public boolean hasNext() {
	            for(int i=0; i<array.length; i++) {
	            	i++;
	                if (current == null){
	                	return false;
	                }
	            }
	                return true;
	        }
	
	        @Override
	        public Entry<K, V> next() {
	        if(!hasNext()){
	             throw new NoSuchElementException();
	        }
	        return current.next();
	        }
	    }
	
	
	/**
	 * Create hash iterator
	 * @param <K> key
	 * @param <V> value
	 */
	private static class EntryImpl<K, V> implements Dictionary.Entry<K, V> {

		private K key;
		private V value;
		
		public EntryImpl(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
	
	/**
	 * Create a random matrix
	 * @param rows
	 * @return matrix m
	 */
	private int[][] randomMatrix(int rows){
        for(int i=0; i<rows; i++){
            for(int j=0; j<32; j++){
                m[i][j] = (int) Math.round(Math.random());
            }
        }
        return  m;
    }
	
	
	/**
	 * Convert value int to an array of bits
	 * @param value
	 * @return array bits of boolean values
	 */
	private int[] convertIntToBit(int value) {
        int[] bits = new int[32];
        for (int i=0; i<32; i++) {
            bits[i] = ((value & 1<<31-i) == 0 ? 0 : 1);
        }
        return bits;
    }
	
	
	/**
	 * Implement universal hashing function
	 * @param key
	 * @return position of last element
	 */
	private int  matrixSum(K key) {
        
		int[] bits = convertIntToBit(key.hashCode());; 
        int[] h = new int[b]; 	// Output table
        int sum = 0;
        
        for (int i=0; i<b; i++) {
            for (int j=0; j<32; j++) {
                sum = (sum + m[i][j] * bits[j]) % 2;
            }
            h[i] = sum;
        }

        // Convert to int
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<b; i++){
            stringBuilder.append(h[i]);
        }
        String newString=stringBuilder.toString();
        int position = Integer.parseInt(newString, 2);
        return position;
    }
	
	
	/**
	 * Double table's capacity
	 */
	private void doubleCapacity() {
		
	}
	
	
	/**
	 * Reduce table's capacity by half
	 */
	private void halfCapacity() {
		
	}
}	
	