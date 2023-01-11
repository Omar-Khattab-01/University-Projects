public class Rational {

    private int numerator;
    private int denominator;

    // constructors

    public Rational(int numerator) {
         // Your code here
        this.numerator = numerator;
        this.denominator = 1;
        
    }

    public Rational(int numerator, int denominator) {
         // Your code here
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    // getters

    public int getNumerator() {
         return numerator;
    }

    public int getDenominator() {
         return denominator;
    }

    // instance methods

    public Rational plus(Rational other) {
         // Your code here
        Rational res = new Rational(((this.numerator*other.denominator)+(other.numerator*this.denominator)),(this.denominator*other.denominator));
        return res;
    }

    public static Rational plus(Rational a, Rational b) {
        // Your code here
        Rational res = new Rational(((a.numerator*b.denominator)+(b.numerator*a.denominator)),(a.denominator*b.denominator));
        return res;
    }

    // Transforms this number into its reduced form

    private void reduce() {
      // Your code here
        int cd = gcd(this.numerator,this.denominator);
        if (this.denominator <0 && this.numerator <0){
            this.denominator =  Math.abs(this.denominator);this.numerator=Math.abs(this.numerator);
        } 
        this.numerator  = (int)(this.numerator/cd);
        this.denominator = denominator/cd;
    }

    // Euclid's algorithm for calculating the greatest common divisor
    private int gcd(int a, int b) {
      // Note that the loop below, as-is, will time out on negative inputs.
      // The gcd should always be a positive number.
      // Add code here to pre-process the inputs so this doesn't happen.
        a= Math.abs(a);b=Math.abs(b);
        while (a != b)
            if (a > b)
                 a = a - b;
            else
                 b = b - a;
        
        return a;
    }

    public int compareTo(Rational other) {
      // Your code here
        int res = ((this.numerator*other.denominator)-(other.numerator*this.denominator));
        if  (res > 0){return 1;}else if (res < 0){return -1;}else{
            return 0;
        }

    }

    public boolean equals(Rational other) {
      // Your code here
        return (this.denominator == other.denominator) && (this.numerator == other.numerator);
    }

    public String toString() {
        String result;
        if (denominator == 1) {
            // Your code here
            result = String.valueOf(this.numerator);
        } else {
            // Your code here
            result = String.valueOf(this.numerator) +"/" + String.valueOf(this.denominator);
        }
        return result;
    }

}