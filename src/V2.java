
public class V2 {
	
		
	public static void find (String shortStr, String longStr) {
		boolean ok=false;
		int count=0;
		for (int i=0; i<(longStr.length()-shortStr.length()+1); i++) {
			ok=false;
			while(ok!=true){
				for(int j=0; j<shortStr.length(); j++) {
					if(longStr.charAt(i)==shortStr.charAt(j)) {	
						ok=true;
						count++;
						break;
					}
					if(j==shortStr.length()-1) {
						count=0;
					}
				}
			}
			if (count>=shortStr.length()) {
				System.out.println("Found one match: "+longStr.substring(i-shortStr.length()+1, i+1)+" is in "+longStr+" at location "+(i-shortStr.length()+1));
			}
		}
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		find ("sdadsdsa",
				"hhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbcahhhlajkjgabckkkkcbakkdfjknbbca");

	}
	
	
	

}
