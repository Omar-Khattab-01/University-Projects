/* *
 * Use static array for NewsFeed
 * with constant MAX_SIZE
 * */

public class NewsFeed {

    private Post[] messages;
    private int size;
    public static final int MAX_SIZE = 25;

    public NewsFeed() {
    	// Your code here.
      this.messages = new Post[MAX_SIZE];
      this.size = 0;
    }

    public void add(Post message) {
      // Your code here.
      if(this.size !=MAX_SIZE){
        this.messages[size] = message;
        this.size++;
      
      }
    }

    public Post get(int index) {
	     return messages[index];
    }

    public int size() {
	     return size;
    }

	  public void sort(){
			int i, j, argMin;
			Post tmp;
			for (i = 0; i < size - 1; i++) {
				argMin = i;
				for (j = i + 1; j < size(); j++) {
					if (messages[j].compareTo(messages[argMin]) < 0) {
						argMin = j;
					}
				}

  			tmp = messages[argMin];
  			messages[argMin] = messages[i];
  			messages[i] = tmp;
		  }

	  }

  	public NewsFeed getPhotoPost(){
  		// Your code here
      NewsFeed res = new NewsFeed();
      for (int i = 0;i<size;i++){
        if (messages[i] instanceof PhotoPost){
          res.add(messages[i]);
        }
      }
      return res;

  	}

  	public NewsFeed plus(NewsFeed other){

  	  // Your code here
      int flag = 0;
      int index=0;
      NewsFeed res = new NewsFeed();
      if(this.size == 0 && other.size ==0){
        return res;
      }
      if(this.size !=1 && this.size !=0){
      for(int i = 0; i< this.size; i++){
        res.add(this.messages[i]);
      }
      }else{
        res.add(this.messages[0]);
      }
      

      if (this.size == MAX_SIZE-1){
        res.add(other.messages[0]);
      }else if (this.size != MAX_SIZE){
        if(other.size != 1 && other.size !=0)
          for (int i = this.size;i<MAX_SIZE;i++){
            res.add(other.messages[i]);
        }else{
          res.add(other.messages[0]);
        }
      }
      
      return res;
  	}
    
}
