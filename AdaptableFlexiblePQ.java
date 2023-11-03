
class Entry<K ,V>{	
	
	K key; 
	V value; 
		
	public Entry(K key, V value){
		this.key = key; 			
		this.value = value; 
	}
		
	public String toString(){
		return "(" + this.key + ", " + this.value + ")"; 
	}
		
}

public class AdaptableFlexiblePQ <K extends Comparable<K>,V> {
		
	private Entry<K,V>[] heap; 
	private int capacity; 
	private int size; 
	private boolean isMinHeap;
		
	public AdaptableFlexiblePQ(Entry<K, V>[] e, boolean isMinHeap) {
		this.capacity = e.length; 
		this.isMinHeap = isMinHeap; 
		this.heap = e; 
		this.size = e.length; 
		this.setProperty(); 
	}
		
	private void setProperty(){
		
		//Heapify 		
		for(int i = heap.length-1 ; i >= 0 ; i--) {
			swapDownHeap(i);
		}

	}
	
	//Returns current size 
	public int size() {
		System.out.println("The size is : " + this.size);
		return this.size; 
	}
	
	//Returns true if queue is empty and false if it is not 
	public boolean isEmpty() {
		return (this.size == 0);  
	}
	
	//Returns the index of the left child 
	private int leftChild(int index) {
		if((2*index)+1 < size) return (2*index)+1;
		else return -1;
	}
	
	//Returns the index of the right child 
	private int rightChild(int index) {
		if(((2*index)+2) < size) return ((2*index)+2);
		else return -1;
	}
	
	//Returns if the node has a right child 
	private boolean hasRightChild(int index) {
		if(rightChild(index) == -1) return false; 
		else return true;
	}
	
	//Returns the top of the queue 
	public Entry<K,V> top(){
		//Special case when the queue is empty 
		if(isEmpty()) {
			return null; 
		}
		else {
			System.out.println("The top element is : " + heap[0]);
			return (heap[0]); 
		}
	}
	
	
	//Returns the last element of the queue 
	public Entry<K,V> last(){
		if(isEmpty()) {
			return null; 
		}
		else{
			System.out.println("The last element is : " + heap[size-1]);
			return (heap[size-1]); 
		}
	}

	//Removes the top of the queue 
	public Entry<K,V> removeTop(){
		//Special case when the queue is empty 
		if(isEmpty()) {
			return null; 
		}
		//Removes the top 
		else {
			Entry<K,V> temp = heap[0]; 
			heap[0] = heap[size-1];
			heap[size-1] = null;
			size--; 
			swapDownHeap(0);
			
			System.out.println("The new top element is : " + heap[0]);
			System.out.println("After removing " + temp);
			this.display(); 
			return temp; 
		}
	}
	
	//Removes a specific entry e with a key k 
	public Entry<K,V> remove(Entry<K,V> e){
		int parent = 0;
		int index = 0; 
		Entry<K,V> remove = e; 
		boolean found = false; 
		
		//Special case when the queue is empty 
		if(isEmpty()) {
			System.out.println("Error the queue is empty");
			return null;
		}
		else {
			for(index = 0; index < size; index++) {
				if(heap[index].key == e.key) {
					heap[index] = heap[size-1]; 
					size--;
					found = true; 
					break;
				}
			}
		}
		
		//Special case when the element with the key k does not exist in the queue 
		if(!found) {
			System.out.println("The entry entered does not exist within the queue");
			return null; 
		}
		parent = (index-1)/2;
		
		//**Restoring the heap property 
		//Handles a min heap 
		if(isMinHeap) {
	
			if(heap[parent].key.compareTo(heap[index].key) > 0) {
				swapUpHeap(index);		
			}
			else {
				swapDownHeap(index); 
			}	
		}
		//Handles a max heap
		else {
			if(heap[parent].key.compareTo(heap[index].key) < 0) {
				swapUpHeap(index);		
			}
			else {
				swapDownHeap(index);
			}
		}			
		System.out.println("The queue after removing : " + remove );
		this.display(); 
		return remove; 		
	}
	
	

	
	//Inserts an entry in the queue 
	public Entry<K,V> insert(K k, V v){
		
		//if the queue is full 
		if(size() == heap.length) {
			System.out.println("The queue is full, we are going to call resize method and 10 spaces");
			resize(heap.length + 10);
			
			Entry<K,V> newEntry = new Entry(k,v); 
			//adding the element at the end 
			size++; 
			this.heap[size-1] = newEntry; 
			//restoring the heap property 
			swapUpHeap(size-1);
			System.out.println("The entry inserted : " + newEntry.toString());
			return newEntry;
			
		}
		//else if the queue is not full 
		else {
			size++;
			Entry<K,V> newEntry = new Entry(k,v); 
			//adding the element at the end 
			this.heap[size-1] = newEntry; 
			//restoring the heap property 
			swapUpHeap(size-1);
			System.out.println("The entry inserted : " + newEntry.toString());
			this.display(); 
			return newEntry;
		}
		 
	}
	
	//Method to resize the array 
	public void resize(int newSize) {
		Entry<K,V>[] temp = new Entry[newSize];

		for(int i = 0 ; i < heap.length ; i++) {
			temp[i] = this.heap[i];
		}
		this.heap = temp;
	}
	
	//Replaces a key with a specific k 
	public K replaceKey(Entry<K,V> e, K newKey){
		K oldKey = e.key;
		int index = 0; 
		int parent= 0;
		boolean found = false;
		
		//Special case if the queue is empty 
		if(isEmpty()) {
			System.out.println("Error the queue is empty");
			return null;
		}
		
		
		for(index = 0 ; index < size ; index++) {
			if(heap[index].key == e.key) {
				heap[index].key = newKey;
				found = true;
				break;
			}
		}
		
		parent = (index-1)/2;
		
		//Handles a min heap 
		if(isMinHeap) {
			if(heap[parent].key.compareTo(heap[index].key) > 0) {
				swapUpHeap(index);		
			}
			else {
				swapDownHeap(index);
			}	
		}
		//Handles a max heap
		else {
			if(heap[parent].key.compareTo(heap[index].key) < 0) {
				swapUpHeap(index);		
			}
			else {
				swapDownHeap(index);
			}
		}		
		//Special case if the entry does not exist in the key 
		if(!found) {
			System.out.println("The entry does not exist within the heap");
		}

		System.out.println("The queue after changing this key : " + oldKey + " into " + newKey);
		this.display(); 
		return oldKey; 			
	}
	
	//Replace a specific value at key k 
	public V replaceValue(Entry<K,V> e, V value){
		V oldValue = e.value;
		boolean found = false;
		//Special case if the queue is empty 
		if(isEmpty()) {
			System.out.println("Error the queue is empty");
			return null;
		}
		
		for(int i = 0 ; i < size-1 ; i++) {
			if(heap[i].key == e.key) {
				heap[i].value = value;
				found = true;
				break; 
			}
		}
		//Special case if the entry does not exist in the key 
		if(!found) {
			System.out.println("The entry that you are looking for does not exist within the heap");
			return null; 
		}
		System.out.println("The queue after changing this value : " + oldValue + " of the key " + e.key );
		this.display();
		return oldValue;
	}
		

	
	public String state() {
		if(this.isMinHeap) {
			System.out.println("The queue is a min heap");
			return "PQ is a Min Heap"; 
		}
		System.out.println("The queue is a max heap");
		return "PQ is a Max Heap"; 
	}
	
	
	public void toggle() {
		String before = "MinHeap"; 
		String after = "MaxHeap"; 
		if(isMinHeap) {
			isMinHeap = false; 
			before = "MinHeap"; 
			after = "MaxHeap"; 
		
		}
		else {
			isMinHeap = true; 
			before = "MaxHeap"; 
			after = "MinHeap"; 
			
		}
		//DownHeapify 
		for(int i = size-1; i >= 0; i--) {
			swapDownHeap(i); 
		}

		
		System.out.println("After switching from a " + before + " to a "+ after);
		this.display();
	}
	
	private void swapDownHeap(int index) {
		//Handles Min Heap 
		if(isMinHeap) {
			int right = rightChild(index); 
			int left = leftChild(index); 
			int swap = 0; 
			
			if(left==-1) {
//				System.out.println("We have reached a base case : leaf node");
				return;
			}
			else {
				if(hasRightChild(index) == false) {
					if(heap[left].key.compareTo(heap[index].key) < 0) swap = left; 
				}
				else if(heap[left].key.compareTo(heap[right].key) > 0) swap = right; 
				else if ((heap[left].key.compareTo(heap[right].key) < 0))  swap = left; 
				
				if (heap[swap].key.compareTo(heap[index].key) < 0) {
					Entry<K, V> temp = (Entry<K,V>) heap[index];
					heap[index] = heap[swap];
					heap[swap] = temp;
					swapDownHeap(swap);
				}
			}
//			System.out.println("the left child of " + heap[index] + " is " + left);
//			System.out.println("the right child of " + heap[index] + " is " + right);	
		}
		//Handles Max heap
		else {
			int right = rightChild(index); 
			int left = leftChild(index); 
			int swap = 0; 
			
			if(left==-1) {
//				System.out.println("We have reached a base case : leaf node");
				return;
			}
//			System.out.println("the left child of " + heap[index] + " is " + left);
//			System.out.println("the right child of " + heap[index] + " is " + right);
			else {
				if(hasRightChild(index) == false) {
					if(heap[left].key.compareTo(heap[index].key) > 0) swap = left; 
				}
				else if(heap[left].key.compareTo(heap[right].key) > 0) swap = left; 
				else if ((heap[left].key.compareTo(heap[right].key) < 0))  swap = right; 
				
				if (heap[swap].key.compareTo(heap[index].key) > 0) {
					Entry<K, V> temp = (Entry<K,V>) heap[index];
					heap[index] = heap[swap];
					heap[swap] = temp;
					swapDownHeap(swap);
				}
			}
		}
	}
		
	private void swapUpHeap(int index) {
		int parent = (index-1)/2;
		//Handles min heap case 
		if(isMinHeap) {
			if(heap[parent].key.compareTo(heap[index].key) > 0) {
				Entry<K, V> temp = (Entry<K,V>) heap[parent];
				heap[parent] = heap[index];
				heap[index] = temp;
				swapUpHeap(parent);
			}
		}
		//Handles max heap case
		else {
			if(heap[parent].key.compareTo(heap[index].key) < 0) {
				Entry<K, V> temp = (Entry<K,V>) heap[parent];
				heap[parent] = heap[index];
				heap[index] = temp;
				swapUpHeap(parent);
			}
		}
	}
	
	
	public void display() {
		for(int i=0; i< size; i++) {
			System.out.println("Key: " + this.heap[i].key + "\tValue: " + this.heap[i].value );
		}
	}	
}