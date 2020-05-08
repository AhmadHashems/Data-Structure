/**
* 
* @author Ahmad Hashem ID#: 40005574.
* Comp352 Section S - Winter 2020
* Assignment: 1,  Version :2
* Due 23:59 on Friday, January 31, 2020
*/


public class Assigment1_Version2 {

	/**
	 * this method is to find any part of the long string that could be a permutation of the short
	 * string, by check from each character in the long string up to j (the length of short string) 
	 * number of characters if this combination of characters is a permutation of the short one the
	 * method will print it.
	 * with a complexity O(n^3) each one of the 2 for loops is O(n) and for the last if statement
	 * that each time we search for a length of a short string i decreases by the length of the short
	 * string with complexity O(n).
	 * 
	 * @param shortStr
	 * @param longStr
	 */
	public static void find (String shortStr, String longStr) {
		boolean ok=false;
		String s1=shortStr;
		
		for (int i=0; i<longStr.length(); i++) {
			ok=false;
			for(int j=0; j<s1.length(); j++) {
				if(longStr.charAt(i)==s1.charAt(j)) {
					ok=true;
					if (j==s1.length())
						s1=s1.substring(0, j);
					if (j==0)
						s1=s1.substring(j+1, s1.length());
					else
						s1=s1.substring(0, j)+s1.substring(j+1, s1.length());
					break;
				}
			}
			if(ok==false) {
				if(s1.length() < shortStr.length())
					i=i-1;
				s1=shortStr;
			}
			if (s1.length()==0) {
				System.out.println("Found one match: "+longStr.substring(i-shortStr.length()+1, i+1)+" is in "+longStr+" at location "+(i-shortStr.length()+1));
				if(shortStr.length() < (longStr.length()-i))
				   i=i-shortStr.length()+1;
				
			}
		}
			
	}
	
	public static void main(String[] args) {
		
		long t0,t1;
        t0=System.currentTimeMillis();	
		find ("ckkkk",
				"hhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbca");	
		t1=System.currentTimeMillis();
		System.out.println("The execution time for this run:"+(t1-t0)+" milliseconds("
		                   +((t1-t0)/1000.0)+ " seconds).\n" );
		
		
		
		
/**
 * this part to increment the short string by 5 each time.
 * 
 * String s="ckkkk";
		for (int i=0; i<40; i++) {

			t0=System.currentTimeMillis();
			
			System.out.println((i+1)+"- A run with short string of "+(5*(i+1))+" characters.");
			find (s,"hhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbca");
			s=s+s;
			
			t1=System.currentTimeMillis();
			System.out.println("The execution time for this run number "+(i+1)+": "+(t1-t0)+" milliseconds("
			                   +((t1-t0)/1000.0)+ " seconds).\n" );

		}
		
	*/			

	}
	
	
	


}
