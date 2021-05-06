import java.util.ArrayList;

public class Library {

    private ArrayList<Book> library = new ArrayList<Book>();

    public Book getBook(int i) {
      return library.get(i);
    } 

    public int getSize() {
      return library.size();
    }

    public void addBook (Book b) {
        // Your code here
        library.add(b);
    }

    public void sort() {
        // Your code here
       BookComparator bc = new BookComparator();
        try{
        Book tmp = library.get(0);
        for(int i = 0;i<library.size() -1;i++){
          for( int j = i+1; j< library.size();j++){
            if( bc.compare(library.get(i),library.get(j)) < 0){
              tmp = library.get(i);
              library.set(i, library.get(j));
              library.set(j, tmp);
            }
          }
        }
        }catch (IndexOutOfBoundsException e ){

        }

    }


    public void printLibrary() {
        // Your code here
        if(library.size() == 1){
          System.out.println(library.get(0));
        }else{
          for (int i = 0; i< library.size() ; i++){
            System.out.println(library.get(i));
          }
        }
    }
    

}