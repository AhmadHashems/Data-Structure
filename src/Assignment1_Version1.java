/**
* 
* @author Ahmad Hashem ID#: 40005574.
* Comp352 Section S - Winter 2020
* Assignment: 1,  Version :1
* Due 23:59 on Friday, January 31, 2020
*/

public class Assignment1_Version1 {

	/**
	 * This method will create all the permutation of a short string and the will find anyone of 
	 * them exist in the long string.the program is using recursion to find all the permutation.
	 * each string has (n!) number of permutation depend on the number of characters.
	 * For example, 4 characters will have 4! =24 permutation.
	 * for each run O(n!)
	 * @param prefix  
	 * @param suffix
	 * @param longStr
	 */
	public static void permu (String prefix, String suffix, String longStr) {
		if (suffix.length()==0) {
			System.out.println(prefix);
			find(prefix, longStr);
		}
			
		for(int i =0; i<suffix.length(); i++)
			permu (prefix+ suffix.charAt(i), suffix.substring(0, i)+suffix.substring(i+1, suffix.length()),longStr);		
	}
	
	/**
	 * This method will find id a short string is a part of a long string 
	 * each run is O(n)
	 * @param shortStr
	 * @param longStr
	 */
	public static void find (String shortStr, String longStr) {
		for (int i=0; i<(longStr.length()-shortStr.length()+1); i++) {
			if(shortStr.equals(longStr.substring(i, i+shortStr.length()))) {
				System.out.println("Found one match: "+shortStr+" is in "+longStr+" at location "+i);
				break;  				
			}
		}		
	}
	
	public static void main(String[] args) {
		
		
		long t0,t1;
		t0=System.currentTimeMillis();
		
		permu("","ckkk","hhhlajkjgabckkkkcbakkdfjknbbca");

		t1=System.currentTimeMillis();
		System.out.println("The execution time for this run: "+(t1-t0)+" milliseconds("
		                   +((t1-t0)/1000.0)+ " seconds).\n" );
	}

}














