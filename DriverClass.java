import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) {
		
		Entry<Integer,String>[] entries = new Entry[10];
		
		Entry<Integer,String> e1 = new Entry<Integer,String>(1,"a"); 
		Entry<Integer,String> e2 = new Entry<Integer,String>(3,"b"); 
		Entry<Integer,String> e3 = new Entry<Integer,String>(5,"c"); 
		Entry<Integer,String> e4 = new Entry<Integer,String>(4,"d"); 
		Entry<Integer,String> e5 = new Entry<Integer,String>(51,"e"); 
		Entry<Integer,String> e6 = new Entry<Integer,String>(30,"f"); 
		Entry<Integer,String> e7 = new Entry<Integer,String>(12,"g"); 
		Entry<Integer,String> e8 = new Entry<Integer,String>(7,"h"); 
		Entry<Integer,String> e9 = new Entry<Integer,String>(9,"k"); 
		Entry<Integer,String> e10 = new Entry<Integer,String>(11,"z"); 
		
		entries[0] = e1;
		entries[1] = e2;
		entries[2] = e3;
		entries[3] = e4;
		entries[4] = e5;
		entries[5] = e6;
		entries[6] = e7;
		entries[7] = e8;
		entries[8] = e9;
		entries[9] = e10;
		

		Scanner scan = new Scanner(System.in); 
		System.out.println("Choose between 'MinHeap' and 'MaxHeap' : ");
		String input = scan.next();
		boolean choice = false; 
		
		if(input.equals("MinHeap")) {
			choice = true; 
		}
		else {
			choice = false; 
		}
		
		AdaptableFlexiblePQ <Integer,String> AFPQ = new AdaptableFlexiblePQ(entries,choice);
		
		System.out.println("Creating the heap... ");
		AFPQ.display(); 
		System.out.println();
		
		System.out.println("Insertion...");
		AFPQ.insert(6, "new"); 
		AFPQ.display(); 
		System.out.println();
		
		System.out.println("Testing state...");
		AFPQ.state(); 
		System.out.println();
		
		System.out.println("Insertion...");
		AFPQ.insert(31, "secondNew"); 
		System.out.println();
		AFPQ.toggle();
		
		System.out.println("Testing state...");
		AFPQ.state(); 
		
		System.out.println();
		System.out.println("What is the top?");
		AFPQ.top(); 
		
		System.out.println();
		AFPQ.toggle();
		
		System.out.println();
		System.out.println("What is the top?");
		AFPQ.top();
		
		System.out.println("What is the size ? ");
		AFPQ.size(); 
		
		System.out.println();
		System.out.println("Remove an entry...");
		AFPQ.remove(e8); 
		System.out.println();
		
		AFPQ.replaceKey(e9, 18); 
		System.out.println();
		
		AFPQ.replaceValue(e3, "modifiedValue");
		System.out.println();
		
		System.out.println("Removing the top: ");
		AFPQ.removeTop(); 
		System.out.println();
		
		AFPQ.toggle();
		
		System.out.println("Testing state...");
		AFPQ.state(); 
		System.out.println();
		
		System.out.println();
		AFPQ.toggle(); 
		System.out.println();
		
		AFPQ.replaceKey(e2, 90); 
		System.out.println();
	
		scan.close(); 
	}
}
