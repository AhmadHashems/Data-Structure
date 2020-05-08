

/**
* 
* @author Ahmad Hashem ID#: 40005574.
* Comp352 Section S - Winter 2020
* Assignment: 4. 
* Due 23:59 on Sunday, April 19, 2020.
*/

public class tes {
	private int size=0;
	private int threshold=0; 
	private static int keyLength;
	private String[] all= new String [size];
    private int allIndex=0;
    
    public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public tes(int threshold, int keyLength) {
    	this.size=0;
    	this.keyLength=keyLength;
    	this.threshold = threshold;
    }
    public tes() {
    	
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
        private double hashed;
        private String[] accidents;

        public Node(Node c) {
        	this.accidents=c.accidents;
        	this.hashed = c.hashed;
        	this.key = c.key;
        	this.value = c.value;
        	
        }
        public Node (String key, int value) {
        	hashed = hash(key);
        	this.key=key;
        	this.value=value;
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
		public double getHashed() {
			return hashed;
		}

		public void setHashed(double key_value) {
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

	        if (node.hashed < root.hashed)
	            root.left  = insert(root.left, node);
	        else
	            root.right = insert(root.right, node);

	        
	        root.height = Math.max(height(root.left), height(root.right)) + 1;

	       
	        int balance = getBalance(root);

	       
	        if (balance > 1 && node.hashed < root.left.hashed)
	            return rightRotate(root);

	        
	        if (balance < -1 && node.hashed > root.right.hashed)
	            return leftRotate(root);

	        
	        if (balance > 1 && node.hashed > root.left.hashed)
	        {
	            root.left =  leftRotate(root.left);
	            return rightRotate(root);
	        }

	        
	        if (balance < -1 && node.hashed < root.right.hashed)
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
	    	
	        if (root.left != null) 
	        	inOrder(root.left);
	        all[allIndex] = root.key;
	        allIndex++;
	        if (root.right != null) 
	        	inOrder(root.right);
	    }

	    public Node find(Node root, double hashed) {
	    	if(root.left==null && root.right==null)
	    		return null;
	    	if (hashed< root.hashed)
	    		return find(root.left, hashed);
	    	else if ( hashed == root.hashed)
	    		return root;
	    	else
	    		return find(root.right, hashed);
	    }
	    public Node findNext(Node root, double hashed) {
	    	if(root.left==null && root.right==null)
	    		return null;
	    	if ( hashed == root.hashed)
	    		return minNode(root.right);
	    	else if (hashed< root.hashed)
	    		if(hashed == root.left.hashed)
	    			return root;
	    		else
	    			return find(root.left, hashed);
	    	else
	    		return minNode(find(root.right, hashed));
	    }
	    
	    public Node findPrev(Node root, double hashed) {
	    	if(root.left==null && root.right==null)
	    		return null;
	    	if (hashed< root.left.hashed)
	    		return find(root.left, hashed);
	    	else if ( hashed == root.right.hashed)
	    		return root;
	    	else
	    		return find(root.right, hashed);
	    }
	    
	    public Node minNode(Node node) {
	        Node temp = node;
	        while (temp.left != null)
	            temp = temp.left;
	        return temp;
	    }

	    
	    public Node deleteNode(Node root , double hashed) {
	        

	        if (root == null)
	            return root;

	        
	        if ( hashed < root.hashed )
	            root.left = deleteNode(root.left, hashed);

	        
	        else if( hashed > root.hashed )
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
	public AVLTree avl= new AVLTree();
	
	
    
     pos[] sequence = new pos[30];



	

    /**
     * coverting each key to a number, the lowest will be the first 
     * in the lexicographic order
     * @param key
     * @return
     */
	public static double hash (String key) {
		double v=0;
		//System.out.println("*******************************************");
		for (int i=0; i<keyLength; i++) {
			v += ( (key.charAt(i))*Math.pow(33,i));
			//System.out.print(key.charAt(i));System.out.println(((double) (key.charAt(i))*Math.pow(3,i)));
				
			}//System.out.println("*******"+v);
		return v;
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

	public String[] allKeys() 	{
		String [] sequ= new String[size];
		if (size >= 3) {
			
			avl.inOrder(root);
			allIndex=0;
			return all;
		}
		else {
			for (int i=0; i< size; i++) 
				sequ[i] =  sequence[i].node.key;				
			return sequ;
		}
	}

	/**
	 * for avl tree will insert in tree based on the value in inOrder way
	 * for sequence it will be based on the number order.
	 * @param key
	 * @param value
	 */
	public void add(String key, int value) {
		Node node= new Node(key, value);
		pos p = new pos (node);
		if (size>= 30) {
			root = avl.insert(root,node);
			size++;
		}
		else {System.out.println(sequence.length);
			if(size==0)
				sequence[0]=new pos (p);	
			for (int i=0; i< size; i++) 
				if(node.hashed < sequence[i].node.hashed) {
					size++;
					for(int j=i+1; j<size; j++) {
						Node temp= sequence[j-1].node;
						sequence[j-1].node=node;
						if(sequence[j]!=null) {
							sequence[j].node=temp;
						}
						else {
							sequence[j]=new pos (temp);
						}
					}
					break;
				}
			

			if(size== 30) 
				for (int i=0; i< size; i++) {
					add(sequence[i].node.key,sequence[i].node.value);
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
		double hashed = hash(key);
		if (size>= 30) {
			root=avl.deleteNode(root, hashed);
			size--;
			if(size< 30) 
				for (int i=0; i< size; i++) {
					add(avl.minNode(root).key,avl.minNode(root).value);
					root=avl.deleteNode(root, avl.minNode(root).hashed);
					size--;
				}
		}
		else {
			
			for (int i=0; i< size; i++) 
				if(hashed == sequence[i].node.hashed) {
					 sequence[i].node =sequence[i+1].node;
					 for(int j=i+1; j<size-1; j++)
						 sequence[j].node =  sequence[j+1].node;
					 sequence[size-1].node = null;
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
		double hashed = hash(key);
		if (size>= 30) {
			Node temp= avl.find(root, hashed);
			return temp.value;
		}
		else {
			for(int i=0; i<size; i++)
				if(hashed == sequence[i].node.hashed)
					return sequence[i].node.value;
		}
		return 0;	
	}
	
	/**
	 * for sequence we go directly then return the next index values.
	 * for avl tree we search to find it then return the next key in iOrder values.
	 * @param key
	 */
	public String nextKey(String key) {
		double hashed = hash(key);
		if (size>=threshold) {
			return avl.findNext(root, hashed).key;
		}
		else {
			for(int i=0; i<size; i++)
				if(hashed == sequence[i].node.hashed)
					if (i!= size-1)
						return sequence[i+1].node.key;
		}
		return null;	
	}
	
	/**
	 * for sequence we go directly then return the previous index values.
	 * for avl tree we search to find it then return the previous key in iOrder values.
	 * @param key
	 */
	public String prevKey(String key) {
		double hashed = hash(key);
		
		if (size>= 30) {
			return avl.findPrev(root, hashed).key;
		}
		else {
			for(int i=0; i<size; i++) {
				if(hashed == sequence[i].node.hashed)
					if(i!=0)
					return sequence[i-1].node.key;
				
			}
						
		}
		return null;
		
	}
	/**
	 * for sequence we go directly then return the accidents list.
	 * for avl tree we search to find it then return the accidents list.
	 * @param key
	 */
	public String[] prevAccids(String key) {
		double hashed = hash (key);
		Node temp;
		if(size>=threshold) {
			temp= avl.find(root, hashed);
			return temp.accidents;
		}
		else {
			for(int i=0; i<size; i++)
				if(hashed == sequence[i].node.hashed)
					return sequence[i].node.accidents;			
		}
		return null;
	}
	
	
	
 	public static void main(String[] args) {
		
 		
	tes test = new tes(2133,10);
	test.setThreshold(102039);
	
	test.add("2asdfghjklq", 1);
	test.add("3sdfgks6lq", 1222);
	test.add("fsdcbgjklq", 122211);
	test.add("w34fghjklq", 2211);
	test.add("cstfghjklq", 3341);
	test.add("gsdwghjklq", 3441);
	test.add("hskeghjklq", 641);
	test.add("wndlcierba", 67751);
	test.remove("wndlcierba");

	//System.out.println(test.getValue("wndlcierba"));
	
	
	
	
	
	
	
	}

}
