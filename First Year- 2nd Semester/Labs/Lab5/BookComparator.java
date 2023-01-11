import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    BookComparator(){

    }
    public int compare(Book a, Book b) {
        if (a.equals(b)){
            return 0;
        }else{
            if (a.author.compareTo(b.author) ==0){
                if(a.title.compareTo(b.title) == 0){
                    if(a.year == b.year){
                        return 0;
                    }else{
                    if(a.year <b.year){
                        return 1 ;
                    }else{
                        return -1;
                    }
                    }
                }else if(a.title.compareTo(b.title) > 0 ){
                    return -1;
                }else{
                    return 1;
                } 
            }else if(a.author.compareTo(b.author) > 0 ){
                return -1;
            }else{
                return 1;
            } 
                
            }
            
        }
    }
