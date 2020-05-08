
public class Stack {
	
	private String arr[]=new String [1000];
	private int index;
	

	public Stack() {
		super();
		this.index =0;
	}

	

	public void push(String s) {
		arr[index]=s;
		//System.out.println("isz  "+index);
		index++;		
	}

	public String pop() {
		index--;
		//System.out.println("i  "+index);
		return arr[index];
	}

	public int size() {
		return index;
	}
	
	public String top() {
		return arr[index-1];
	}
	
	public void rest() {
		index=0;
	}
	
	
}
