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

public class Assignment2_recursion {
	
		
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
	static int isPriority (String op) {
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
	 * this function does a simple calculation between 2 numbers only 
	 * @param v1
	 * @param op
	 * @param v2
	 * @return v1 operator v2
	 */
	static String cal ( double v1,String op, double v2) {
			switch (op) {
			case "==" : return String.valueOf(v1==v2);
			case "!=" : return String.valueOf(v1!=v2);
			case ">=" : return String.valueOf(v1>=v2);
			case "<=" : return String.valueOf(v1<=v2);
			case ">"  : return String.valueOf(v1>v2);
			case "<"  : return String.valueOf(v1<v2);
			case "+"  : return String.valueOf(v1+v2);
			case "-"  : return String.valueOf(v1-v2);
			case "*"  : return String.valueOf(v1*v2);
			case "/"  : return String.valueOf(v1/v2);
			case "^"  : return String.valueOf(power(v1,v2));
			}
			return String.valueOf(0);
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
							rest= calculator(rest);
							s=s.substring(0, start) + rest + s.substring(i+1, s.length());						
						}
					}
				}
			}
			return s;		
		}
	
	/**
	 * this is method to calculate an expression using 2 recursion method itself
	 * (tail recursion)and calculator_forward
	 * if the first operator higher than the second then the method will do the first then
	 * continue with the rest. However, if the second operator is higher than the first one,
	 * then the method will call  calculator_forward to calculate the second operation first
	 * then return the result to do the first operations.  
	 * @param s
	 * @return the result of an expression .
	 */
	static String calculator(String s) {
			s=doParentheses(s);
			String v1="",v2="",op1="",op2="",rest="";
			if(s.equals("false")||s.equals("true"))
				return s;
			
			for(int i=0; i<s.length(); i++) {
				
				if((s.charAt(i)>47 && s.charAt(i)<58)|| s.charAt(i)=='.'||
						(s.charAt(i)=='-'&& v1.length()==0)) {
					v1=v1+ String.valueOf(s.charAt(i));
					continue;
				}
				break;
			}
			
			if(v1.length()==s.length())
				return String.valueOf(Double.parseDouble(v1));
			
			op1=s.substring(v1.length(),v1.length()+1);
			if(op1.equals("!")||op1.equals("=")||op1.equals("<")||op1.equals(">"))
				if(s.length()!=v1.length()+1 && s.charAt(v1.length()+1)== '=')
					op1= op1+s.substring(v1.length()+1,v1.length()+2);
				
			if (op1.equals("!")) {
				rest= ""+s.substring(v1.length()+1,s.length());
				v1= String.valueOf(factorial(Double.parseDouble(v1)));
				s=v1+rest;
				if(v1.length()==s.length())
					return String.valueOf(Double.parseDouble(v1));
				op1=s.substring(v1.length(),v1.length()+1);
			}
			
			for(int i=v1.length()+op1.length(); i<s.length(); i++) {
				if((s.charAt(i)>47 && s.charAt(i)<58)|| s.charAt(i)=='.'||
						(s.charAt(i)=='-'&& v2.length()==0)) {
					v2=v2+ String.valueOf(s.charAt(i));
					continue;
				}
				break;
			}
			
			if(v1.length()+v2.length()+op1.length()==s.length())
				return cal(Double.parseDouble(v1), op1 ,Double.parseDouble(v2));
			
			op2=s.substring(v1.length()+v2.length()+op1.length(),v1.length()+v2.length()+op1.length()+1);
			if(op2.equals("!")||op2.equals("=")||op2.equals("<")||op2.equals(">"))
				if(v1.length()+v2.length()+op1.length()+1!=s.length() && s.charAt(v1.length()+v2.length()+op1.length()+1)== '=')
					op2= op2+ s.substring(v1.length()+v2.length()+op1.length()+1,v1.length()+v2.length()+op1.length()+2);
			
			if (op2.equals("!")) {
				rest= ""+s.substring(v1.length()+v2.length()+op1.length()+1,s.length());
				v2= String.valueOf(factorial(Double.parseDouble(v2)));
				s=v1+op1+v2+rest;
				if(v1.length()+v2.length()+op1.length()==s.length())
					return cal(Double.parseDouble(v1), op1 ,Double.parseDouble(v2));
				op2=s.substring(v1.length()+v2.length()+op1.length(),v1.length()+v2.length()+op1.length()+1);
			}
	
			if (isPriority(op1) >= isPriority(op2)) {
				rest=cal(Double.parseDouble(v1), op1 ,Double.parseDouble(v2));
				rest= rest + s.substring(v1.length()+v2.length()+op1.length(), s.length());
				return calculator(rest);
			}
			
			if (isPriority(op1) < isPriority(op2)) {	
				rest= ""+s.substring(v1.length()+op1.length(), s.length());
				rest= calculator_forward(rest);
				rest= v1+op1+rest;
				return calculator(rest);
			}
			return String.valueOf(0000000000000);
		}
	
	/**
	 * this method does the highest operation first the return the value to do the 
	 * less priority operations	
	 * @param s
	 * @return
	 */
	static String calculator_forward(String s) {
			String v1="",v2="",op1="",op2="",rest="";
			for(int i=0; i<s.length(); i++) {
				if((s.charAt(i)>47 && s.charAt(i)<58)|| s.charAt(i)=='.'||
						(s.charAt(i)=='-'&& v1.length()==0)) {
					v1=v1+ String.valueOf(s.charAt(i));
					continue;
				}
				break;
			}
			
			op1=s.substring(v1.length(),v1.length()+1);
			if(op1.equals("!")||op1.equals("=")||op1.equals("<")||op1.equals(">"))
				if(s.length()!=v1.length()+1 && s.charAt(v1.length()+1)== '=')
					op1= op1+s.substring(v1.length()+1,v1.length()+2);
				
			if (op1.equals("!")) {
				rest= ""+s.substring(v1.length()+1,s.length());
				v1= String.valueOf(factorial(Double.parseDouble(v1)));
				s=v1+rest;
				if(v1.length()==s.length())
					return String.valueOf(Double.parseDouble(v1));
				op1=s.substring(v1.length(),v1.length()+1);
			}
			
			for(int i=v1.length()+op1.length(); i<s.length(); i++) {
				if((s.charAt(i)>47 && s.charAt(i)<58)|| s.charAt(i)=='.'||
						(s.charAt(i)=='-'&& v2.length()==0)) {
					v2=v2+ String.valueOf(s.charAt(i));
					continue;
				}
				break;
			}
			
			if(v1.length()+v2.length()+op1.length()==s.length())
				return cal(Double.parseDouble(v1), op1 ,Double.parseDouble(v2));
			
			op2=s.substring(v1.length()+v2.length()+op1.length(),v1.length()+v2.length()+op1.length()+1);
			if(op2.equals("!")||op2.equals("=")||op2.equals("<")||op2.equals(">"))
				if(v1.length()+v2.length()+op1.length()+1!=s.length() && s.charAt(v1.length()+v2.length()+op1.length()+1)== '=')
					op2= op2+ s.substring(v1.length()+v2.length()+op1.length()+1,v1.length()+v2.length()+op1.length()+2);
			
			if (op2.equals("!")) {
				rest= ""+s.substring(v1.length()+v2.length()+op1.length()+1,s.length());
				v2= String.valueOf(factorial(Double.parseDouble(v2)));
				s=v1+op1+v2+rest;
				if(v1.length()+v2.length()+op1.length()==s.length())
					return cal(Double.parseDouble(v1), op1 ,Double.parseDouble(v2));
				op2=s.substring(v1.length()+v2.length()+op1.length(),v1.length()+v2.length()+op1.length()+1);
			}

			if (isPriority(op1) < isPriority(op2)) {
				rest= ""+ s.substring(v1.length()+1, s.length());
				rest= calculator_forward(rest);
				return v1+op1+rest;
				
			}
			
			if (isPriority(op1) >= isPriority(op2)) {
				rest=cal(Double.parseDouble(v1), op1 ,Double.parseDouble(v2));
				rest= rest+s.substring(v1.length()+v2.length()+1, s.length());
				return rest;
			}
			
			return "0000000000000";		
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
				 s = new Scanner (new FileInputStream("input.txt"));
				 pw = new PrintWriter(new FileOutputStream("output.txt"));
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
			

			
			
			//System.out.println(calculator("((-3^2)+4+5==(-3^2)+4+5)"));
			
			
			
			
			
			
			
			
			
		}

	}
