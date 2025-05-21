import java.util.*;

/** 
 * I'd like to thank Agniva for helping me with this monstrosity. 
 * He provided me with some notes and psuedocode on A*, and pushed me into actually doing it.
 */
public class AStarPathfinder
{
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
        HashMap<Point, Point> cameFrom = new HashMap<>();
        
        // Map holding the cost to reach each node
        HashMap<Point, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);
        
        // Map holding the estimated cost to reach the goal
        HashMap<Point, Integer> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, end));
        
        /* The nasty bit basically puts the point with the lowest fScore first 
           It's a lambda with point passed in that returns the fScore of that point or the max int if not found
           I feel like there has to be a better way to do this
           */
        PriorityQueue<Point> openSet = new PriorityQueue<>(Comparator.comparingInt(point -> fScore.getOrDefault(point, Integer.MAX_VALUE)));
        openSet.add(start);
        
        int pointsChecked = 0;
        
        while (!openSet.isEmpty())
        {
            Point current = openSet.poll();
            //System.out.println(current);
            
            if (current.equals(end))
            {
                ArrayList<Point> path = reconstructPath(cameFrom, current);
                System.out.println(path);
                System.out.println("Path found after checking " + pointsChecked + " neighboring points.");
                return path;
            }
            
            for (Point neighbor : findNeighbors(grid, current))
            {
                pointsChecked++;
                // Agniva put an if statement that checked if neighbors are blocked, but I think I did that in the findNeighbors() method
                
                // The +1 is because Agniva said to put the distance between the two points, which is always one
                int tentativeGScore= gScore.getOrDefault(current, Integer.MAX_VALUE - 1) + 1;
                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE))
                {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, end));
                    
                    if (!openSet.contains(neighbor))
                    {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        
        
        return new ArrayList<Point>(); // Failure, so sad
    }
    
    public static int heuristic(Point node, Point goal)
    {
        // Manhattan distance
        return Math.abs(node.getX() - goal.getX()) + Math.abs(node.getY() -  goal.getY());
    }
    
    public static ArrayList<Point> reconstructPath(HashMap<Point, Point> map, Point current)
    {
        ArrayList<Point> totalPath = new ArrayList<Point>();
        totalPath.add(current);
        
        while (map.containsKey(current))
        {
            current = map.get(current);
            totalPath.add(0, current);
        }
        
        return totalPath;
    }
}
