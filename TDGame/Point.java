import java.util.Objects;
/**
 * A point on the XY plane.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point  
{
    private int x;
    private int y;

    /**
     * Constructor for objects of class Point
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    
    public boolean equals(Object obj)
    {
        Point other = (Point) obj;
        if (this.getX() == other.getX() && this.getY() == other.getY())
        {
            return true;
        }
        return false;
    }
}
