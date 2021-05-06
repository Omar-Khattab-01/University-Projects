public class Combination {

    // Instance variables.
    int x,y,z;


    // Constructor
    public Combination( int first, int second, int third ) {
        // Your code here
        this.x= first;
        this.y= second;
        this.z= third;
    }

    // An instance method that compares this object
    // to other.
    // Always check that other is not null, i)
    // an error would occur if you tried to
    // access other.first if other was null, ii)
    // null is a valid argument, the method should
    // simply return false.

    public boolean equals( Combination other ) {
        // Put your code here and remove the line below
        if (other == null){
            return false;
        }
        return (this.x==other.x)&&(this.y==other.y)&&(this.z==other.z);
    }

    // Returns a String representation of this Combination.

    public String toString() {
        // Put your code here and remove the line below
        String repr =String.valueOf(x)+":"+String.valueOf(y)+":"+String.valueOf(z);
        return repr;
    }

}