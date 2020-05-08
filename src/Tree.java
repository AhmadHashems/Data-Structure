/**
* 
* @author Ahmad Hashem ID#: 40005574.
* Comp352 Section S - Winter 2020
* Assignment: 2,  Version : recursion. 
* Due 23:59 on Tuesday, February 25, 2020.
*/
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tree {
	
	Scanner scan = new Scanner (System.in);
	/**
	 * @param op
	 * @return the priority of an operator.
	 */
	static int priority (String op) {
		switch (op) {
		case "+"  : return 1;
		case "-"  : return 1;
		case "*"  : return 2;
		case "/"  : return 2;
		}
		return 0;
	}
	
	/**
	 * this function does a simple calculation between 2 numbers only 
	 * @param v1
	 * @param op
	 * @param v2
	 * @return v1 operator v2
	 */
	static String calculate ( String op,String v1, String v2) {
		switch (op) {
		case "+"  : return String.valueOf(Double.parseDouble(v1)+Double.parseDouble(v2));
		case "-"  : return String.valueOf(Double.parseDouble(v1)-Double.parseDouble(v2));
		case "*"  : return String.valueOf(Double.parseDouble(v1)*Double.parseDouble(v2));
		case "/"  : return String.valueOf(Double.parseDouble(v1)/Double.parseDouble(v2));
		}
		return String.valueOf(0);
	}
	
	
	
	
	
		/**
		 * creating a node class to have each variable as a node
		 * @author Ahmad Hashem
		 *
		 */
		private class Node{
			private String value;
			private Node parent;
			private Node leftChild;
			private Node rightChild;
			
			
			public Node() {
				super();
			}
			public Node(String v, Node parent, Node leftChild, Node rightChild) {
				super();
				if((v.charAt(0)>=65 && v.charAt(0)<=90)||( v.charAt(0)>=97 && v.charAt(0)<=122) ) {
					System.out.println("Please enter the value of "+ v.charAt(0));
					v=scan.next();
				}
				this.value = v;
				this.parent = parent;
				this.leftChild = leftChild;
				this.rightChild = rightChild;
			}
			public String getValue() {
				return value;
			}
			public void setValue(String v) {
				this.value = v;
			}
			public Node getParent() {
				return parent;
			}
			public void setParent(Node parent) {
				this.parent = parent;
			}
			public Node getLeftChild() {
				return leftChild;
			}
			public void setLeftChild(Node leftChild) {
				this.leftChild = leftChild;
			}
			public Node getRightChild() {
				return rightChild;
			}
			public void setRightChild(Node rightChild) {
				this.rightChild = rightChild;
			}
			
		}
		
		private Node head;
		private Node tail;
		
		public Tree() {
			super();
		}

		/**
		 * this method takes the expression and gets rid of the parentheses by evaluation all
		 * the inside value of the parentheses first.
		 * @param s
		 * @return an expression with no parentheses.
		 */	
		public StringTokenizer doParentheses(String s) {
			int count=1;
			boolean valid;
			StringTokenizer st;
			
			for (int j=0; j<= count;j++) {
				for(int i=0; i<s.length(); i++) {
					valid=true;
					String rest="";
					int start=0;
					if (s.charAt(i)=='(') {
						count++;
						start=i;
						i++;
						while(s.charAt(i)!=')') {
							if (s.charAt(i)=='(') {
								valid=false;
								i--;
								break;
							}
							rest= rest+ String.valueOf(s.charAt(i));
							i++;
						}	
						if(valid) {
							st= new StringTokenizer(rest);
							rest= String.valueOf(solve(st));
							s=s.substring(0, start) + rest + s.substring(i+1, s.length());						
						}
					}
				}
			}
			
			st= new StringTokenizer(s);
			return st;		
		}
		
		/**
		 * This method to go through the tree and visit each sub tree ands get its result
		 * @param temp
		 * @return
		 */
		public String traverse(Node temp) {
			
			if(temp.leftChild==null)
				return temp.getValue();
			
			return calculate(temp.getValue(),traverse(temp.leftChild),
					traverse(temp.rightChild));
			
		}

		/**
		 * this meathod reads an arithmetic expression and sotre it in a tree then return
		 * the the value of the expression
		 * @param st
		 * @return
		 */
		public String solve (StringTokenizer st) {
			String str="";
			while (st.hasMoreTokens()) {
				str= str+ st.nextToken() + " ";				
			}
			
			st= doParentheses(str);
			
			if(st.hasMoreTokens())
				tail= new Node (st.nextToken(),null,null,null);
			if(st.hasMoreTokens())
				head=tail=tail.parent= new Node (st.nextToken(),null,tail,null);
			if(st.hasMoreTokens())
				tail=tail.rightChild= new Node (st.nextToken(),tail,null,null);
			
			
			Node temp= tail;
			String s=null;
			String t=null;
			while(st.hasMoreTokens()) {
				s=st.nextToken();
				
			

				if (priority(s)> priority(tail.parent.getValue())) {
					t=tail.getValue();
					tail.setValue(s);
					tail.leftChild= new Node(t,tail,null,null);
					tail=tail.rightChild= new Node (st.nextToken(),tail,null,null);						
				}
				else {
					while (temp.parent!=null && priority(s)<= priority(temp.parent.getValue())) {
							temp=temp.parent;	
							
						}
					temp=tail= temp.parent=new Node (s,temp.parent,temp,null);
					tail=tail.rightChild=new Node(st.nextToken(),tail,null,null);
					if (temp.parent==null)
						head=temp;
					
				}
					
				
			}

			
			return traverse(head);		
			
		}
		
			
	public static void main(String[] args) {
		
		Tree tree= new Tree();
		StringTokenizer st= new StringTokenizer(" 2 +  2  * 5  / 3 - 2 * 10 * 10 / 100  ");
		System.out.print("2 +  2  * 5  / 3 - 2 * 10 * 10 / 100  =  ");
		System.out.println(tree.solve(st));
		
		StringTokenizer st1= new StringTokenizer("( 2 + ( 2  * 5 ) / 3 - 2 * 10 * 10 / 100 ) ");
		System.out.print("(2 + ( 2  * 5 ) / 3 - 2 * 10 * 10 / 100 )  =  ");
		System.out.println(tree.solve(st1));
		
		StringTokenizer st2= new StringTokenizer(" 2 *  2  * 5  / 3 - 2 * 10 + 10 - 100  ");
		System.out.print("2 *  2  * 5  / 3 - 2 * 10 + 10 - 100   =  ");
		System.out.println(tree.solve(st2));
		
		StringTokenizer st3= new StringTokenizer("( 2 * ( 2  * 5 ) / 3 - 2 * 10 + 10 - 100 ) ");
		System.out.print("(2 * ( 2  * 5 ) / 3 - 2 * 10 + 10 - 100 )  =  ");
		System.out.println(tree.solve(st3));
		
		StringTokenizer st4= new StringTokenizer("( 2 + ( 2  * 5 ) / ( 3 - 2 ) * 10 * 10 / 100 ) ");
		System.out.print("(2 + ( 2  * 5 ) / ( 3 - 2 ) * 10 * 10 / 100 )  =  ");
		System.out.println(tree.solve(st4));
		
		StringTokenizer st5= new StringTokenizer("( ( 2 + 2 )  * 5 ) / 3 - 2 + 10 * 10 / 100  ");
		System.out.print("((2 + 2 )  * 5 ) / 3 - 2 + 10 * 10 / 100  =  ");
		System.out.println(tree.solve(st5));
		
		StringTokenizer st6= new StringTokenizer(" 2 +  2  - 5 + 3 - 2 * 10 * 10 / 100  ");
		System.out.print("2 +  2  - 5 + 3 - 2 * 10 * 10 / 100  =  ");
		System.out.println(tree.solve(st6));
		
		StringTokenizer st7= new StringTokenizer(" 2 /  2  + 5  / ( 3 - 2 * 10 * 10 / 100 ) ");
		System.out.print("2 /  2  + 5  / ( 3 - 2 * 10 * 10 / 100 )  =  ");
		System.out.println(tree.solve(st7));
		
		StringTokenizer st8= new StringTokenizer("( 2 + 0 ) - ( ( 2  * 5 ) / 3 - 2 * 10 * 10 / 100 ) ");
		System.out.print("(2 + 0 ) - ( ( 2  * 5 ) / 3 - 2 * 10 * 10 / 100 )  =  ");
		System.out.println(tree.solve(st8));
		
		StringTokenizer st9= new StringTokenizer(" 9 - 9 / 2 +  2  * 5  / 3 - 2 * 10 * 10 / 100  ");
		System.out.print("9 - 9 / 2 +  2  * 5  / 3 - 2 * 10 * 10 / 100  =  ");
		System.out.println(tree.solve(st9));
		
		StringTokenizer st10= new StringTokenizer(" x - y / z +  2  * 5  / 3 - 2 * 10 * 10 / 100  ");
		System.out.print("\n\nx - y / z +  2  * 5  / 3 - 2 * 10 * 10 / 100  =  ");
		System.out.println("x - y / z +  2  * 5  / 3 - 2 * 10 * 10 / 100  =  "+tree.solve(st10));
		
		
		
	}

}
