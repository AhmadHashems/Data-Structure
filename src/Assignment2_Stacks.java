/**
* 
* @author Ahmad Hashem ID#: 40005574.
* Comp352 Section S - Winter 2020
* Assignment: 2,  Version : recursion. 
* Due 23:59 on Tuesday, February 25, 2020.
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assignment2_Stacks {

	static Stack opStk =new Stack();
	static Stack valStk =new Stack();
	
	/**
	 * to calculate the power.
	 * @param v1
	 * @param v2
	 * @return the power 
	 */
	public static double  power(double v1, double v2) {
		if (v2==0)
			return 1;
		return v1 * power (v1,v2-1);	
	}

	/**
	 * 
	 * @param v1
	 * @return the factorial of v1
	 */
	public static double  factorial(double v1) {
		if(v1<0) {
			if (v1==0|| v1==-1)
				return -1;
			return v1 * factorial (v1+1);
		}
		if (v1==0|| v1==1)
			return 1;
		return v1 * factorial (v1-1);
		
	}
	
	/**
	 * @param op
	 * @return the priority of an operator.
	 */
	static int prec (String op) {
		switch (op) {
		case "==" : return 1;
		case "!=" : return 1;
		case ">=" : return 2;
		case "<=" : return 2;
		case ">"  : return 2;
		case "<"  : return 2;
		case "+"  : return 3;
		case "-"  : return 3;
		case "*"  : return 4;
		case "/"  : return 4;
		case "^"  : return 5;
		case "!"  : return 6;
		case "("  : return 7;
		case ")"  : return 7;
		}
		return 0;
	}

	/**
	 * this function does a simple calculation between the first 2 numbers in valStk
	 * with the first operator in opStk 
	 * @param v1
	 * @param op
	 * @param v2
	 * @return top Stack-1 operator top stack
	 */
	static void doOp ( ) {
		double y= Double.parseDouble(valStk.pop());
		double x= Double.parseDouble(valStk.pop());
		String op= opStk.pop();
		
		switch (op) {
		case "==" : {
			valStk.push(String.valueOf(x==y));
			break;
		}
		case "!=" :{
			valStk.push(String.valueOf(x!=y));
			break;
		}
		case ">=" : {
			valStk.push(String.valueOf(x>=y));
			break;
		}
		case "<=" : {
			valStk.push(String.valueOf(x<=y));
			break;
		}
		case ">"  : {
			valStk.push(String.valueOf(x>y));
			break;
		}
		case "<"  : {
			valStk.push(String.valueOf(x<y));
			break;
		}
		case "+"  : {
			valStk.push(String.valueOf(x+y));
			break;
		}
		case "-"  : {
			valStk.push(String.valueOf(x-y));
			break;
		}
		case "*"  : {
			valStk.push(String.valueOf(x*y));
			break;
		}
		case "/"  :{
			valStk.push(String.valueOf(x/y));
			break;
		}
		case "^"  : {
			valStk.push(String.valueOf( power(x,y)));
			break;
		}
		
		}
		
		
	}

	/**
	 * the method to check if the new operator has less priority then the top opStk, then 
	 * do all the higher operators first  
	 * @param refOp
	 */
	static void repeatOps(String refOp) {
		while(valStk.size()>1 && prec(refOp)<=prec(opStk.top()))
			doOp();
			
	}
	
	/**
	 * this method takes the expression and gets rid of the parentheses by evaluation all
	 * the inside value of the parentheses first.
	 * @param s
	 * @return an expression with no parentheses.
	 */
	static String doParentheses(String s) {
		int count=1;
		boolean valid;
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
						rest= String.valueOf(calculator(rest));
						s=s.substring(0, start) + rest + s.substring(i+1, s.length());						
					}
				}
			}
		}
		return s;		
	}
	
	/**
	 * this method read the expression and take each number add it to the valStk
	 * and the operators to the opStk and does the calculations based on the priority
	 * of the operators.
	 * @param s
	 * @return
	 */
	static String calculator(String s) {
		s=doParentheses(s);
		if(s.equals("false")||s.equals("true"))
			return s;
		valStk.rest();
		opStk.rest();
		String v1,op1,rest="";
		
		
		while(true) {
			v1="";
			op1="";
			for(int i=0; i<s.length(); i++) {
				if((s.charAt(i)>47 && s.charAt(i)<58)|| s.charAt(i)=='.'||
						(s.charAt(i)=='-'&& v1.length()==0)) {
					v1=v1+ String.valueOf(s.charAt(i));
					continue;
				}
				break;
			}

			valStk.push(v1);			
			if(v1.length()==s.length())
				break;
			

			op1=s.substring(v1.length(),v1.length()+1);
			if(op1.equals("!")||op1.equals("=")||op1.equals("<")||op1.equals(">"))
				if(s.length()!=v1.length()+1 && s.charAt(v1.length()+1)== '=')
					op1= op1+s.substring(v1.length()+1,v1.length()+2);
			
			if(op1.equals("!")) {
				rest=String.valueOf(factorial(Double.parseDouble(v1)));
				valStk.pop();
				valStk.push(rest);
				if(v1.length()+1==s.length())
					break;
				op1=s.substring(v1.length()+1,v1.length()+2);
			}
			
			
			repeatOps(op1);
			opStk.push(op1);
			
			
			if(op1.equals("=="))
				s=s.substring(s.indexOf(op1.charAt(op1.length()-1))+2,s.length());
			else
			s=s.substring(s.indexOf(op1.charAt(op1.length()-1))+1,s.length());
			if(s.length()==0)
				break;
			
		}
		repeatOps("$");
		//System.out.println("= "+valStk.top());
		return valStk.top();
	
		
	}
		
		
	
	public static void main(String[] args) {
		

		
		Scanner s = null ;
		PrintWriter pw=null;
		String expression="";
		
		/**
		 * opening 2 files to read the expression from the input file and write
		 * the result into the output file.
		 */
		try {
			 s = new Scanner (new FileInputStream("inputstack.txt"));
			 pw = new PrintWriter(new FileOutputStream("outputStack.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(s.hasNext()) {
			expression = s.next();
			System.out.println(expression+" =\n"+calculator(expression)+"\n\n");
			pw.write(expression+" =\n"+calculator(expression)+"\n\n");
			
		}
		s.close();
		pw.close();

			
		
		
		
		
		
	}

}
