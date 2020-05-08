
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

/**
* 
* @author Ahmad Hashem ID#: 40005574.
* Comp352 Section S - Winter 2020
* Assignment: 4. 
* Due 23:59 on Sunday, April 19, 2020.
*/

public class Assignment4_CVR {
	private int size=0;
	private int threshold=0; 
	private static int keyLength;
	private String[] all;
    private int allIndex=0;
    static BigDecimal k=new BigDecimal("0");
    static BigDecimal m=new BigDecimal("999999999999999999999999999999999999999");
    pos sequence;
    public AVLTree avl;
    
   
	public Assignment4_CVR(int threshold, int keyLength) {
    	this.size=0;
    	this.keyLength=keyLength;
    	this.threshold = threshold;
    	this.sequence = new pos(threshold);
    	this.avl= new AVLTree();
    }
    public Assignment4_CVR() {
    	
    }
   
    /**
     * a Node class to represent each car will be used in the AVLTree and with Positions
     * for Sequence
     * @author Ahmad Hashem
     *
     */
public class Node {
        private Node left, right;
        private int height = 1;
        private int value;
        private String key;
        private BigDecimal hashed;
        private String[] accidents= new String[365];

        public Node(Node c) {
        	this.accidents=c.accidents;
        	this.hashed = c.hashed;
        	this.key = c.key;
        	this.value = c.value;
        	
        }
        public Node (String key, int value) {
        	
        	this.hashed = hash(key);
        	this.key=key;
        	this.value=value;
        	this.accidents[0]= "This car has no previous accidents YAY!";
        }
        public String[] getAccidents() {
			return accidents;
		}
		public void setAccidents(String[] accidents) {
			this.accidents = accidents;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public BigDecimal getHashed() {
			return hashed;
		}

		public void setHashed(BigDecimal key_value) {
			this.hashed = key_value;
		}
    }
    public Node root;
    
    /**
     * we will use it to create an array of postion to implement a sequence.
     * @author Ahmad Hashem
     *
     */
public class pos {
    	private Node node;
    	private double index;
    	public Node[] pos;
    	
    	public pos (int threshold) {
    		pos=new Node[threshold];
    	}
    	
    	public pos (Node node) {
    		this.index=0;
    		this.node=new Node (node);
    	}
    	public pos (pos p) {
    		this.node= new Node(p.node);
    		this.index=p.index;
    		
    	}
		public pos() {
			// TODO Auto-generated constructor stub
		}
    	
	}
	
	
	/**
	 * this class have all the main methods of AVLTree to add and remove and find 
	 * the minimum value, to traverse inOrder.
	 * 
	 * @author Ahmad Hashem
	 *
	 */
public class AVLTree {
		
		public int height (Node N) {
	        if (N == null)
	            return 0;
	        return N.height;
	    }

	    public Node insert(Node root, Node node) {
	       
	        if (root == null) {
	            return(node);
	        }

	        if (node.hashed.compareTo(root.hashed) < 0)
	            root.left  = insert(root.left, node);
	        else
	            root.right = insert(root.right, node);

	        
	        root.height = Math.max(height(root.left), height(root.right)) + 1;

	       
	        int balance = getBalance(root);

	       
	        if (balance > 1 && node.hashed.compareTo(root.left.hashed) < 0)
	            return rightRotate(root);

	        
	        if (balance < -1 && 0 > root.right.hashed.compareTo(node.hashed))
	            return leftRotate(root);

	        
	        if (balance > 1 && 0 > root.left.hashed.compareTo(node.hashed))
	        {
	            root.left =  leftRotate(root.left);
	            return rightRotate(root);
	        }

	        
	        if (balance < -1 && node.hashed.compareTo(root.right.hashed) < 0)
	        {
	            root.right = rightRotate(root.right);
	            return leftRotate(root);
	        }

	        
	        return root;
	    }

	    public Node rightRotate(Node y) {
	        Node x = y.left;
	        Node T2 = x.right;
	        x.right = y;
	        y.left = T2;
	        y.height = Math.max(height(y.left), height(y.right))+1;
	        x.height = Math.max(height(x.left), height(x.right))+1;
	        return x;
	    }

	    public Node leftRotate(Node x) {
	        Node y = x.right;
	        Node T2 = y.left;
	        y.left = x;
	        x.right = T2;
	        x.height = Math.max(height(x.left), height(x.right))+1;
	        y.height = Math.max(height(y.left), height(y.right))+1;
	        return y;
	    }

	    public int getBalance(Node N) {
	        if (N == null)
	            return 0;
	        return height(N.left) - height(N.right);
	    }

	    public void inOrder(Node root) {
	    	
	        if (root.left != null) { 
	        	
	        	inOrder(root.left);
	        }
	        
	        System.out.println("AVL-"+allIndex+")  "+ root.key);
	        allIndex++;
	        if (root.right != null) {
	        	inOrder(root.right);
	        }
	    }

	    public void prev(Node root, BigDecimal hashed) {
	    	
	        if (root.left != null) { 
	        	prev(root.left, hashed);
	        }
	        if(k.compareTo(root.hashed) < 0 && root.hashed.compareTo(hashed) < 0) {
	        	k=root.hashed;
	        	
	        }
	        
	        if (root.right != null) {
	        	prev(root.right,hashed);
	        }
	    }
	    public void next(Node root, BigDecimal hashed) {
	    	
	        if (root.left != null) { 
	        	next(root.left, hashed);
	        }
	        if(0 > root.hashed.compareTo(m) && 0 >hashed.compareTo(root.hashed)){
	        	m=root.hashed;    	
	        }
	        if (root.right != null) {
	        	next(root.right, hashed);
	        }
	    }

	    public void find(Node root, BigDecimal hashed) {
	    	
	    	if ( hashed.compareTo(root.hashed) == 0 ) {
	    		System.out.println(root.key);
	    		return ;
	    	}
	    	
	    	if (hashed.compareTo(root.hashed )< 0) 
	    		 find(root.left, hashed);
	    		 
	    	else if(root.left==null && root.right==null)
	    		return ;
	    	else
	    		 find(root.right, hashed);
	    }
	    

	    public void findnp(Node root, BigDecimal hashed) {
	    	
	    	if ( hashed.compareTo(root.hashed) == 0 ) {
	    		for(int i =0; i<root.accidents.length; i++)
	    			if(root.accidents[i] != null)
	    				System.out.println("AVL-car's key: ("+root.key+") "+root.accidents[i]);
	    		return ;
	    	}
	    	
	    	if (hashed.compareTo(root.hashed )< 0) 
	    		 findnp(root.left, hashed);
	    		 
	    	else if(root.left==null && root.right==null)
	    		return ;
	    	else
	    		 findnp(root.right, hashed);
	    }
	    
	    public int findv(Node root, BigDecimal hashed) {
	    	
	    	if ( hashed.compareTo(root.hashed) == 0 ) 
	    		return root.value;
	    	
	    	if (hashed.compareTo(root.hashed )< 0) 
	    		 return findv(root.left, hashed);
	    		 
	    	else if(root.left==null && root.right==null)
	    		return 0;
    	
	    	else
	    		 return findv(root.right, hashed);
	    }
		    
	    
	    public Node minNode(Node node) {
	        Node temp = node;
	        while (temp.left != null)
	            temp = temp.left;
	        return temp;
	    }

	    public Node maxNode(Node node) {
	        Node temp = node;
	        while (temp.right != null)
	            temp = temp.right;
	        return temp;
	    }
	    
	    public Node deleteNode(Node root , BigDecimal hashed) {
	        

	        if (root == null)
	            return root;

	        
	        if ( hashed.compareTo(root.hashed) < 0 )
	            root.left = deleteNode(root.left, hashed);

	        
	        else if( 0 > root.hashed.compareTo(hashed) )
	            root.right = deleteNode(root.right, hashed);

	        
	        else {
	           
	            if( (root.left == null) || (root.right == null) ) {

	                Node temp;
	                if (root.left != null)
	                        temp = root.left;
	                else
	                    temp = root.right;

	                
	                if(temp == null) {
	                    temp = root;
	                    root = null;
	                }
	                else 
	                    root = temp;

	                temp = null;
	            }
	            else {
	                
	                Node temp = minNode(root.right);

	                root.value = temp.value;
	                root.key = temp.key;
	                root.hashed = temp.hashed;
	                root.accidents = temp.accidents;

	                root.right = deleteNode(root.right, temp.hashed);
	            }
	        }

	       
	        if (root == null)
	            return root;

	        
	        root.height = Math.max(height(root.left), height(root.right)) + 1;

	        
	        int balance = getBalance(root);

	        
	        if (balance > 1 && getBalance(root.left) >= 0)
	            return rightRotate(root);

	        
	        if (balance > 1 && getBalance(root.left) < 0) {
	            root.left =  leftRotate(root.left);
	            return rightRotate(root);
	        }

	        
	        if (balance < -1 && getBalance(root.right) <= 0)
	            return leftRotate(root);

	        
	        if (balance < -1 && getBalance(root.right) > 0) {
	            root.right = rightRotate(root.right);
	            return leftRotate(root);
	        }

	        return root;
	    }
	
	}


    /**
     * converting each key to a number, the lowest will be the first 
     * in the lexicographic order
     * @param key
     * @return
     */
	public static BigDecimal hash (String key) {
		String v="";
		key=key.toUpperCase();
		
		for (int i=0; i<keyLength; i++) {
			
				v += (int)key.charAt(i);
			}//System.out.println(v);
		return new BigDecimal(v);
	}
	
	public void setThreshold(int threshold) {
			this.threshold = threshold;
		}
	public void setKeyLength(int keyLength) {
		this.keyLength = keyLength;
	}
	
	/**
	 * Generating a random key by taking random number then convert to char and built 
	 * each key string characters by characters
	 * @param n
	 * @return
	 */
	public String[] generate (int n) {
		String[] randomKeys= new String[n];
		for(int j =0; j<n; j++) {
			int numberOrLetter=0;
			for(int i=0; i<keyLength; i++) {
				numberOrLetter = (int)Math.random()*2 + 1;
				if (numberOrLetter == 1) 
					randomKeys[j] +=(char)((int)Math.random()*10 + 48);
				if (numberOrLetter == 2) 
					randomKeys[j] +=(char)((int)Math.random()*26 + 65);			
			}		
		}
		return randomKeys;
	}

	
	/**
	 * this will return all keys from the array if sequence or by traverse the avl tree
	 * @return
	 */
	public void allKeys() 	{

		if (size >= threshold ) {
			allIndex=1;
			avl.inOrder(root);
			allIndex=1;	
		}
		else {	
			for (int i=0; i< size; i++) {
				System.out.println("Seq.-"+(i+1)+")  "+ sequence.pos[i].key);
								
			}
			
		}
	}

	/**
	 * for avl tree will insert in tree based on the value in inOrder way
	 * for sequence it will be based on the number order.
	 * @param key
	 * @param value
	 */
	public void add(String key, int value) {
		
		if(key.length()>keyLength||key.length()<keyLength) {
			//System.out.println("The car with key "+key+" will not be add"
			//		+" key must be "+keyLength+" digits.");
			return;
		}

		Node node= new Node(key, value);
		if (size >= threshold) {
			root = avl.insert(root,node);
			size++;
			
		}
		else {
			
			if(size==0) {
				sequence.pos[0]=node;
				size++;
				return;
			}
			
			boolean done= false;
			for (int i=0; i< size; i++) 
				if(node.hashed.compareTo(sequence.pos[i].hashed) <0 ) {
					size++;
					done=true;
					for(int j=i; j<size; j++) {
						Node temp= sequence.pos[j];
						sequence.pos[j]=node;
						node=temp;
					}
					break;
				}
				
			if (!done) {
				size++;
				sequence.pos[size-1]=node;
			}
			

			if(size== threshold) 
				for (int i=0; i< size; i++) {
					add(sequence.pos[i].key , sequence.pos[i].value);
					size--;
				}
		}
	}
	
	/**
	 * for sequence we go directly then we shift each value.
	 * for avl tree we search to find it then adjust the tree
	 * @param key
	 */
	public void remove(String key) {
		if(key.length()>keyLength||key.length()<keyLength) {
			//System.out.println("The car with key "+key+"   has not been added before because "
			//		+" key must be "+keyLength+" digits.");
			return;
		}
		BigDecimal hashed = hash(key);
		if (size>= threshold ) {
			root=avl.deleteNode(root, hashed);
			size--;
			if(size< threshold) 
				for (int i=0; i< size; i++) {
					size--;
					add(root.key,root.value);
					root=avl.deleteNode(root, root.hashed);
					
				}
		}
		else {
			
			for (int i=0; i< size; i++) 
				if(hashed.compareTo(sequence.pos[i].hashed) == 0) {
					 sequence.pos[i] =sequence.pos[i+1];
					 for(int j=i+1; j<size-1; j++)
						 sequence.pos[j] =  sequence.pos[j+1];
					 sequence.pos[size-1] = null;
					 break;
				}
			size--;								
		}
		
	}

	/**
	 * for sequence we go directly then return the values.
	 * for avl tree we search to find it then return the values.
	 * @param key
	 */
	public int getValue(String key) {
		BigDecimal hashed = hash(key);
		if (size>= threshold) {
			int v= avl.findv(root, hashed);
			return v;
		}
		else {
			for(int i=0; i<size; i++)
				if(hashed.compareTo(sequence.pos[i].hashed) == 0 )
					return sequence.pos[i].value;
		}
		return 0;	
	}
	
	/**
	 * for sequence we go directly then return the next index values.
	 * for avl tree we search to find it then return the next key in iOrder values.
	 * @param key
	 */
	public void nextKey(String key) {
		BigDecimal hashed = hash(key);
		
		if (size>= threshold) {
			m= new BigDecimal("9999999999999999999999999999999999999999");
			avl.next(root, hashed);
			 avl.find(root,m);
			 
		}
		else {
			for(int i=0; i<size; i++)
				if(hashed.compareTo(sequence.pos[i].hashed) == 0)
					if (i!= size-1)
						System.out.println(sequence.pos[i+1].key);
		}
			
	}
	
	/**
	 * for sequence we go directly then return the previous index values.
	 * for avl tree we search to find it then return the previous key in iOrder values.
	 * @param key
	 */
	public void prevKey(String key) {
		BigDecimal hashed = hash(key);
		
		if (size>= threshold) {
			k= new BigDecimal("0");
			 avl.prev(root, hashed);
			 avl.find(root,k);
			 
		}
		else {
			for(int i=0; i<size; i++) {
				if(hashed.compareTo(sequence.pos[i].hashed) == 0)
					if(i!=0)
					System.out.println(sequence.pos[i-1].key);
				
			}
						
		}	
	}
	/**
	 * for sequence we go directly then return the accidents list.
	 * for avl tree we search to find it then return the accidents list.
	 * @param key
	 */
	public void prevAccids(String key) {
		BigDecimal d = hash (key);
		
		if(size>=threshold) {
			 avl.findnp(root, d);
		}
		else {
			for(int i=0; i<size; i++)
				if(d.compareTo(sequence.pos[i].hashed) == 0) 
					for(int j =0; j<sequence.pos[i].accidents.length; j++)
		    			if(sequence.pos[i].accidents[j] != null)
		    				System.out.println("Seq-car's key: ("+sequence.pos[i].key+") "
		    			+sequence.pos[i].accidents[j]);
		}
	}
	
	
	
	public static void main(String[] args) {
		
		Assignment4_CVR test = new Assignment4_CVR(3,10);
		Scanner s = null ;
		String key="";
		
		
		try {
			 s = new Scanner (new FileInputStream("ar_test_file2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(s.hasNext()) {
			key = s.next();
			test.add(key,(int)(Math.random()*2042));
			
		}
		s.close();
		
		
		
		test.allKeys();
		try {
			 s = new Scanner (new FileInputStream("ar_test_file2.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(s.hasNext()) {
			key = s.next();
			test.remove(key);
			
		}
		s.close();
		
		
		
		
 		
	 	
		
		
		test.add("aaaaaaaaaa", 1);
		test.add("aaaaaaaaab", 1222);
		test.add("caaaaaaaaa", 122211);
		test.add("0aaaaaaaad", 2211);
		test.add("daaaaaaaaa", 3341);
		test.add("aaaaaaaaaf", 3441);
		test.add("1aaaaaaaaa", 12123);
		test.add("wndlcierba", 67751);
		test.remove("aaaaaaaaab");
		test.allKeys();
		test.nextKey("1aaaaaaaaa");
		test.prevKey("1aaaaaaaaa");
		test.prevAccids("1aaaaaaaaa");
		System.out.println(test.getValue("1aaaaaaaaa"));
		
		
		
		
		
		
		
		
		}

}
