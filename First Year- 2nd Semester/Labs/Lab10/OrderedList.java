import java.util.NoSuchElementException;

public class OrderedList implements OrderedStructure {

    // Implementation of the doubly linked nodes (nested-class)

    private static class Node {

      	private Comparable value;
      	private Node previous;
      	private Node next;

      	private Node ( Comparable value, Node previous, Node next ) {
      	    this.value = value;
      	    this.previous = previous;
      	    this.next = next;
      	}
    }

    // Instance variables

    private Node head;

    // Representation of the empty list.

    public OrderedList() {
        // Your code here.
        head = new Node(null,null,null);
    }

    // Calculates the size of the list

    public int size() {
      	// Remove line below and add your implementation.
        int counter = 0;
        if(head.next == null){
            return counter;
        }else{
            counter++;
            Node p = head.next;
            while (p.next.value != null ){
                p = p.next;
                counter++;
            }
            return counter;



        }
    }

    
    public Object get( int pos ) throws IndexOutOfBoundsException {
        // Remove line below and add your implementation.
        if ( pos  > size()-1 || pos < 0){
            throw new IndexOutOfBoundsException("Index not found!");
        }
        Node p = head.next;
        for (int i =0; i< pos; i++){
            p=p.next;
        }
        return  p.value;
        

    }

    // Adding an element while preserving the order
    @SuppressWarnings("unchecked")
    public boolean add( Comparable o ) throws IllegalArgumentException {
        // Remove line below and add your implementation.
        if (o ==  null){
            throw new IllegalArgumentException("Can not add Null");
        }
        
        if(size() ==0 ){
            Node newNode = new Node(o, head,head);
            head.next = newNode;
            head.previous = newNode;
            return true;
        }
        if(head.next.value.getClass() != o.getClass())
            return false;
        Node p = head.previous;
        
        while(p.value != null){
            int trueFalse = p.value.compareTo(o);
            if (trueFalse <=0){
            Node newNode = new Node(o,p,p.next);
            p.next.previous = newNode;
            p.next = newNode;
            return true;
        }else{
            p=p.previous;
        }
        }
        Node begNode = new Node(o,head,p.next);
        p.next = begNode;
        p.previous.previous=begNode;
        

        return true;
    }

    //Removes one item from the position pos.

    public void remove( int pos ) throws IndexOutOfBoundsException{
      // Remove line below and add your implementation.
      if ( pos  > size()-1 || pos < 0){
        throw new IndexOutOfBoundsException("Index not found!");

    }
    Node p = head.next;
        for (int i =0; i< pos; i++){
            p=p.next;
        }
        p.previous.next = p.next;
        p.next.previous = p.previous; 
    }

    // Knowing that both lists store their elements in increasing
    // order, both lists can be traversed simultaneously.
    @SuppressWarnings("unchecked")
    public void merge( OrderedList other ) {
      // Remove line below and add your implementation.
      Node p = other.head.next;
      Node k = head.next; 
      while(p.value!= null){
          if(k.value != null){
            int comp = p.value.compareTo(k.value);
          if( comp <0){
            Node newNode = new Node (p.value,k.previous,k);
            k.previous.next = newNode;
            k.previous = newNode;
            p=p.next;
          }else {
            k = k.next;
          }
          }else{
              Node newNode = new Node(p.value,k.previous,head);
              k.previous.next = newNode;
              p = p.next;
          }
          
      }
    }
}   