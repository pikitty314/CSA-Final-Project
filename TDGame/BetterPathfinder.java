import java.util.ArrayList;

public class BetterPathfinder
{
    static int count = 0;
    public static boolean legalPoint(boolean[][] grid, Point point)
    {
        int x = point.getX();
        int y = point.getY();
        
        if (x < 0 ||
            y < 0 || 
            x >= grid[0].length || 
            y >= grid.length || 
            !grid[y][x])
        {
            return false;
        }
        return true;
    }
    
    public static ArrayList<Point> findNeighbors(boolean[][] grid, Point point)
    {
        ArrayList<Point> neighbors = new ArrayList<Point>();
        int x = point.getX();
        int y = point.getY();
        
        Point up = new Point(x, y + 1);
        Point down = new Point(x, y - 1);
        Point right = new Point(x + 1, y);
        Point left = new Point(x - 1, y);
        
        if (legalPoint(grid, up))
        {
            neighbors.add(up);
        }
        
        if (legalPoint(grid, down))
        {
            neighbors.add(down);
        }
        
        if (legalPoint(grid, right))
        {
            neighbors.add(right);
        }
        
        if (legalPoint(grid, left))
        {
            neighbors.add(left);
        }
        
        return neighbors;
    }
    
    
    /**
     * A hopefully better pathfinder.
     * 
     * @param grid A 2D array containing a boolean to represent each point that you are or are not allowed to go.
     * @param start The point to start at.
     * @param end The point to end at.
     * @return The path, in order with the first step first and the last step last in the List.
     */
    public static ArrayList<Point> pathfinder(boolean[][] grid, Point start, Point end)
    {    
        boolean pathFound = false;
        
        ArrayList<Point> usedPoints = new ArrayList<Point>();
        usedPoints.add(start);
        
        while(!pathFound)
        {
            ArrayList<Point> newOpen = new ArrayList<Point>();
            for (int i = 0; i < usedPoints.size(); i++)
            {
                Point currentPoint = usedPoints.get(i);
                for (Point neighbor : findNeighbors(grid, currentPoint))
                {
                    if (!usedPoints.contains(neighbor) && !newOpen.contains(neighbor))
                    {
                        newOpen.add(neighbor);
                    }
                }
            }
            
            for (Point point : newOpen)
            {
                usedPoints.add(point);
                if (end.equals(point))
                {
                    pathFound = true;
                    break;
                }
            }
            
            if (!pathFound && newOpen.isEmpty())
            {
                return null;
            }
        }
        
        ArrayList<Point> path = new ArrayList<Point>();
        Point point = usedPoints.get(usedPoints.size() - 1);
        //while (poin

        return null;
    }
}
