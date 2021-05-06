

public class DynamicArrayStack<E> implements Stack<E> {

    // Instance variables

    private E[] elems;  // Used to store the elements of this ArrayStack
    private int top;    // Designates the first free cell
    private static final int DEFAULT_INC = 25;   //Used to store default increment / decrement

    @SuppressWarnings( "unchecked" )

    // Constructor
    public DynamicArrayStack( int capacity ) {
        // Your code here.
        if( capacity<DEFAULT_INC){
            elems = (E[])new Object[DEFAULT_INC];
            top=0;
        }else{
        elems = (E[])new Object[capacity];
        top=0;
        }
    }

    // Gets current capacity of the array
    public int getCapacity() {
        return elems.length;
    }

    // Returns true if this DynamicArrayStack is empty
    public boolean isEmpty() {
        return ( top == 0 );
    }

    // Returns the top element of this ArrayStack without removing it
    public E peek() {
        return elems[ top-1 ];
    }

    @SuppressWarnings( "unchecked" )

    // Removes and returns the top element of this stack
    public E pop() {
        // Your code here.
        E item = peek();
        elems[--top] = null;
        if(top < elems.length-25){
            Object[] tmp = new Object[elems.length-DEFAULT_INC];
            for(int i = 0; i<top-1;i++){
                tmp[i] = elems[i];
            }
            elems = (E[]) tmp; 
        }
        return item;
        
    }

    @SuppressWarnings( "unchecked" )

    // Puts the element onto the top of this stack.
    public void push( E element ) {
        // Your code here.
        if(top == elems.length){
            Object[] tmp = new Object[elems.length+DEFAULT_INC];
            for(int i = 0; i<elems.length;i++){
                tmp[i] = elems[i];
            }
            elems = (E[]) tmp; 
        }

        elems[top++] = element;
    }

    @SuppressWarnings( "unchecked" )

    public void clear() {
        // Your code here.
        Object[] tmp = new Object[DEFAULT_INC];
        elems = (E[]) tmp;
        top=0;
    }

    public static void main(String[] args){
        DynamicArrayStack<Integer> myStack = new DynamicArrayStack<Integer>(0);
        int c = myStack.getCapacity();

    }

}