/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author Shubham
 */
public class SLLSet {
    
    private int size; //integer to store size of set
    private SLLNode head; //ref to start of Linked list
    
    public SLLSet(){ //constructor for empty set
        size = 0;
        head = null;
    }
    
    public SLLSet(int [] sortedArray){ //constructor set in increasting order NO Repeations 
        size = sortedArray.length;
        head = new SLLNode(sortedArray[0], null); //sets head to first value in array and next node to null
        
        SLLNode current = head; //class node ref to head now
        
        for(int i=1; i<size; i++){ //starts from value after head
            current.next = new SLLNode(sortedArray[i],null); //stores value and addes null as next node
            current = current.next; //moving to next node
        }
    }
    
    public int getSize(){ //method gets size of the array
        return size;
    }
    
    public SLLSet copy(){ //creates a copy of the set
        if(size == 0){
            return new SLLSet(); //empty set constructor
        }
        
        int[] newList = new int[size]; //create new listarray
        SLLNode current = head;
        
        for(int i=0; current != null;i++){ //goes through the list looks for tail
            newList[i] = current.value;
            current = current.next; //moving to next node
        }
        return new SLLSet(newList);
    }
    
    public boolean isIn(int v){ //finds the element in set
        SLLNode current = head;
        
        while(current != null){ //checks if head is not null
            if(current.next == null){ //checks the next node too see if its a tail
                if(current.value == v){ //checks if the value is the last element!
                    return true; 
                } else{
                    break;
                }
            }
            if(current.value == v){ //checks if the value equals the input
                return true;
            }
            current = current.next;//incremeants to next node
        }
        return false;
    }
    
    public void add(int v){ //adds an element if it already doesnt exist
        if(head == null || v < head.value){ //checks if head is null or v is smaller
            head = new SLLNode(v, head); //adds it right before head
            size++; //increases list size
        }
        else if(isIn(v)){
            return;
        } //does nothing if exists
        else{
            SLLNode current = head;
            
            while(current.next != null){ //loop to find tail
                if(current.next.value > v){ //checks if current value is greater and adds itself before it
                    current.next = new SLLNode(v,current.next); //adds it right before that next
                    size++;
                    return;
                }
                current = current.next;
            }
            current.next = new SLLNode(v,null); //next node will be null
            size++;
        }
    }
    
    public void remove(int v){ //removes if found
        
        if(isIn(v)){ //method to check if its in set
            if(head.value == v){ //checks case if value is first(head)
                SLLNode current = head; //class nodes references to head now
                head = head.next; //moves the node to point to next as head
                current = null; 
                size--; //decreases the size of list
            }
            else{ //for elements that are not first (middle or last)
            SLLNode current = head;
            while(current.next != null){ //checks if next node is tail
                if(current.next.value == v){
                    current.next = current.next.next; //set the node "pointer" to next node
                    size--;
                    return;
                }
                current = current.next; //move the current to next node
            }
            }
        }
    }
    
    public SLLSet union(SLLSet s){  //returns union of input set (s) and this set
        SLLSet unionOutput = s.copy(); //makes a copy of the input set and stores it for output
        
        SLLNode current = head;
        while(current != null){ //loops to find the tail
            unionOutput.add(current.value); //adds to this array in our class 
            current = current.next;
        }
        return unionOutput;
    }
    
    public SLLSet intersection(SLLSet s){ //returns intersection of input set (s) and this set
        SLLSet interOutput = new SLLSet(); //creates empty set
        
        SLLNode current = head;
        
        while(current != null){ //loops to find the tail
            if(s.isIn(current.value)){ //finds the input set values that exist in this set
                interOutput.add(current.value); //adds it 
            }
            current = current.next; //incremeants to next node
        }
        return interOutput;
    }
    
    public SLLSet difference(SLLSet s){ //returns new set that is difference b/w this set and input (s)
        SLLSet diffOutput = new SLLSet(); //creates an empty set
        
        SLLNode current = head; //head for this set
        
        while(current !=null){ //loops to find tail in this set
            if(!(s.isIn(current.value))){ //finds the input set values that do not exist in this set (False)
                diffOutput.add(current.value); //adds them
            }
            current = current.next; //increments to next node
        }
        return diffOutput;
    }
    
    public static SLLSet union(SLLSet[] sArray){ //returns new object with union of sets
        SLLSet unionOut = new SLLSet(); //empty set 
        
        for(int i=0; i<sArray.length;i++){
            unionOut = unionOut.union(sArray[i]); //makes a copy and then adds the array objects 1 by 1 
        } //also checks using isIn() to ensure set has no repeating elements
        return unionOut;
    }
    
    public String toString(){
        if(head == null){
            return " "; 
        }
        else{
            String set = new String();
            for(SLLNode current = head; current != null; current = current.next){ //loops through the set until null
                set = set + current.value + ","; //adds set element value and loops to next
            }
            return set;
        }
        
    }
}
