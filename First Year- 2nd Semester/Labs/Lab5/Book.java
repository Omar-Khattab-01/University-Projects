public class Book {

    // Your variables declaration here
    String author;
    String title;
    int year;
	

    public Book (String author, String title, int year) {
        // Your code here
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        // Your code here
        return author;
    }

    public String getTitle() {
        // Your code here
        return title;
    }

    public int getYear() {
        // Your code here
        return year;
    }

    public boolean equals(Object other) {
        // Your code here
        if (other == null || getClass() != other.getClass()){
            return false;
        }else{
            Book book = (Book)other;
            if(this == book){
                return true;
            }else if(book.author == null && book.title == null){
                if (author == null && title == null ){
                    return year == book.year;
                }
            return false;
        }else if(book.author == null){
            if(author  == null){
                return year == (book.year) && title.equals(book.title);
            }
            return false;
        }else if (book.title == null){
            if(title == null){
                return year == (book.year) && author.equals(book.author);
            }
            return false;
        }else if ( author == null || title == null){
        	return false;
        }
        return year == book.year && author.equals(book.author) && title.equals(book.title);
    }
 }

    public String toString() {
        // Your code here
        return author+":"+title+"("+year+")";

    }

    public static void main(String[] args){

        Book myBook = new Book("Shakespeare", "Hamlet", 1600);
        Book myBook2 = new Book(null, "Macbeth", 1600);
        boolean a=myBook.equals(myBook2);
        System.out.println(a);  
    }
}