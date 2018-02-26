package code;

import java.util.ArrayList;
import java.util.Random;

public class Testing {

	public static void main(String[] args) {
		
        // Instantiate the Models class to access its methods
        Model m = new Model("dictionaries/small.txt");
        Random v = new Random();
        
        v.nextInt();
        System.out.println(v.nextInt(10));
        ArrayList<Character> f = new ArrayList<>();
        f.add('b');
        f.add('u');
        f.add('l');
        f.add('l');
        System.out.println(m.charList2string(f));
        
        // Add calls to the other methods similar to the above method call to test your code
		
	}
	
}
