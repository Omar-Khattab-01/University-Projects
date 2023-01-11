public class Dictionary implements Map<String, Integer> {

    private final static int INITIAL_CAPACITY = 10;
    private final static int INCREMENT = 5;
    private int count;

    private Pair[] elems;

    public int getCount() {
      return count;
    }

    public int getCapacity() {
      return elems.length;
    }

    public Dictionary() {
        /* Your code here */
        elems = new Pair[INITIAL_CAPACITY];
        count = 0;
    }

    @Override
    public void put(String key, Integer value) {
        /* Your code here */
        if (getCount() != getCapacity()){
        elems[count] = new Pair(key, value);
        count++;
        }else{
            increaseCapacity();
            elems[count] = new Pair(key, value);
            count++;
        }
    }

    private void increaseCapacity() {
        /* Your code here.  Use this in put() where necessary. */
        Pair[] tmp = new Pair[getCapacity()+INCREMENT];
        for (int i =0; i<getCapacity();i++){
            tmp[i] = elems[i];
        } 
        elems = tmp;
    }

    @Override
    public boolean contains(String key) {
        /* Your code here. */ 
        for (int i = getCapacity()-1; i>=0;i--){
            if(elems[i]!=null){
                if (elems[i].getKey().equals(key)){
                    return true;
                }
            }  
        }
        return false;
    }

    @Override
    public Integer get(String key) {
        /* Your code here. */
        int res = -1;
        boolean notFound = true;
        int countCopy = getCapacity()-1;
        while(notFound){
            if(elems[countCopy]!= null){
                if (elems[countCopy].getKey().equals(key)){
                    res = elems[countCopy].getValue();
                    notFound = false;
                }
            }      
            countCopy--;

        }
        return res;
    }

    @Override
    public void replace(String key, Integer value) {
        /* Your code here. */
        boolean notFound = true;
        int countCopy = count-1;
        while(notFound){
            if (elems[countCopy]!= null){
                if (elems[countCopy].getKey().equals(key)){
                    elems[countCopy].setValue(value);
                    notFound = false;
                }
            }
            countCopy--;

        }
        
    }

    @Override
    public Integer remove(String key) {
        /* Your code here. */
        int removed =-1;
        boolean notFound = true;
        int countCopy = count-1;
        while(notFound){
            if(elems[countCopy] != null){
                if (elems[countCopy].getKey().equals(key)){
                    removed = elems[countCopy].getValue();
                    elems[countCopy] = null;
                    count--;
                    notFound = false;
                }
                countCopy--;
             }
        }
        return removed;
        
    }
    

    @Override
    public String toString() {
      String res;
      res = "Dictionary: {elems = [";
      for (int i = count-1; i >= 0 ; i--) {
          res += elems[i];
          if(i > 0) {
              res += ", ";
          }
      }
      return res +"]}";
    }
}