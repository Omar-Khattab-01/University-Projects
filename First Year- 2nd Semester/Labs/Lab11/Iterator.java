public interface Iterator {
    int current = 0;
    public abstract boolean hasNext();
    public abstract int next();
    public abstract void add( int bit );
}