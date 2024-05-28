/**
 * Vectors, with direction AND magnitude.
 * 
 * @author Ethan Yan
 * @version May 2024
 */
public class Vector implements Comparable<Vector>{
    private double x = 0;
    private double y = 0;
    private double cachedLength = -1;
    public Vector() {};
    public Vector(double x, double y) {set(x, y);}
    
    public void set(double x, double y) {
        if (this.x != x || this.y != y)
            cachedLength = -1;
        this.x = x;
        this.y = y;
    }
    
    public void rotate(double radians) {
        set(Math.cos(radians) * x - Math.sin(radians) * y, Math.sin(radians) * x + Math.cos(radians) * y);
    };

    public void add(double x, double y) {set(this.x + x, this.y + y);}
    public void subtract(double x, double y) {set(this.x - x, this.y - y);}
    
    public void multiply(double x, double y) {set(this.x * x, this.y * y);}
    public void divide(double x, double y) {set(this.x / x, this.y / y);}
    public void multiply(double s) {set(x * s, y * s);}
    public void divide(double s) {set(x / s, y / s);}

    public void add(Vector other) {add(other.getX(), other.getY());}
    public void subtract(Vector other) {subtract(other.getX(), other.getY());}
    public void multiply(Vector other) {multiply(other.getX(), other.getY());}
    public void divide(Vector other) {divide(other.getX(), other.getY());}
    
    public double getX() {return x;}
    
    public double getY() {return y;}
    
    // Square roots are commonly known to be slow, so might as well cache it
    public double getLength() {
        if (cachedLength < 0)
            cachedLength = Math.sqrt(x * x + y * y);
        return cachedLength;
    }
    
    public int compareTo(Vector other) {
        double diff = getLength() - other.getLength();
        return (int)Math.signum(diff);
    }
    
    public String toString() {
        return "Vector: " + x + ", " + y;
    }
}