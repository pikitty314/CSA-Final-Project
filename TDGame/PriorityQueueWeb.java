import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class PriorityQueueWeb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PriorityQueueWeb 
{
    // instance variables - replace the example below with your own
    private ArrayList<Point> queue = new ArrayList<Point>();
    private HashMap<Point, Integer> scoresMap;
    
    public PriorityQueueWeb(HashMap<Point, Integer> scoresMap)
    {
        this.scoresMap = scoresMap;
    }
    
    public void add(Point point)
    {
        queue.add(point);
    }
    
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    
    public Point poll()
    {
        Point lowestPt = queue.get(0);
        int lowestScore = scoresMap.get(lowestPt);
        
        for (Point pt : queue)
        {
            if (scoresMap.get(lowestPt) < lowestScore)
            {
                lowestPt = pt;
                lowestScore = scoresMap.get(lowestPt);
            }
        }
        
        queue.remove(lowestPt);
        return lowestPt;
    }
    
    public boolean contains(Point pt)
    {
        return queue.contains(pt);
    }
}
