import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class PathfindTest
{
    public static void main(String[] args)
    {
        boolean[][] grid = {
            {true,false,true,true,true},
            {true,true,true,false,true},
            {false,false,true,false,true},
        };

        Point start = new Point(0,0);
        Point end = new Point(4,3);

        ArrayList<Point> shortestPath = pathfinder(grid, start, end);
        Collections.reverse(shortestPath);
        for (Point pt : shortestPath)
        {
            System.out.println((int)pt.getX() + " " + (int)pt.getY());
        }
    }

    /**
     * A very simple and probably relatively inefficent pathfinder.
     * 
     * @param grid A 2D array containing a boolean to represent each point that you are or are not allowed to go.
     * @param start The point to start at.
     * @param end The point to end at.
     * @return The path, in inverted order (first step at the end), so the next step can be accessed through the remove() method
     */
    public static ArrayList<Point> pathfinder(boolean[][] grid, Point start, Point end)
    {    
        int x = (int)start.getX();
        int y = (int)start.getY();
        if (x < 0 ||
            y < 0 || 
            x >= grid[0].length || 
            y >= grid.length || 
            !grid[y][x])
        {
            // illegal location
            return new ArrayList<>();
        }

        ArrayList<Point> path = new ArrayList<>();
        
        if (start.equals(end))
        {
            // found the end!
            path.add(start);
            return path;
        }
        
        boolean[][] newGrid = copy2DBooleanArray(grid);
        newGrid[y][x] = false;

        // check all directions
        ArrayList<Point> up = new ArrayList<Point>(pathfinder(newGrid, new Point(x, y + 1), end));
        ArrayList<Point> down = new ArrayList<Point>(pathfinder(newGrid, new Point(x, y - 1), end));
        ArrayList<Point> right = new ArrayList<Point>(pathfinder(newGrid, new Point(x + 1, y), end));
        ArrayList<Point> left = new ArrayList<Point>(pathfinder(newGrid, new Point(x - 1, y), end));
        
        path = new ArrayList<Point>(getShortest(up, down, right, left));
        if (!path.isEmpty())
        {
            // only add this point if a valid path is found
            path.add(start);
        }
        // newGrid[y][x] = true; // Should be unnecessary because the ArrayList is deleted anyways?
        return path;
    }
    /**
     * Takes four ArrayList<Point> and returns the shortest one.
     * There has got to be a better way to do this, right?
     */
    public static ArrayList<Point> getShortest(ArrayList<Point> a, ArrayList<Point> b, ArrayList<Point> c, ArrayList<Point> d)
    {
        int aLen = a.size();
        int bLen = b.size();
        int cLen = c.size();
        int dLen = d.size();

        if(a.isEmpty())
        {
            aLen = Integer.MAX_VALUE;
        }
        if(b.isEmpty())
        {
            bLen = Integer.MAX_VALUE;
        }
        if(c.isEmpty())
        {
            cLen = Integer.MAX_VALUE;
        }
        if(d.isEmpty())
        {
            dLen = Integer.MAX_VALUE;
        }

        if (aLen < bLen && aLen < cLen && aLen < dLen)
        {
            return a;
        } else if (bLen < cLen && bLen < dLen)
        {
            return b;
        } else if (cLen < dLen)
        {
            return c;
        }
        else
        {
            return d;
        }
    }

    public static boolean[][] copy2DBooleanArray(boolean[][] original)
    {
        boolean[][] newArray = new boolean[original.length][original[0].length];
        for (int i = 0; i < original.length; i++)
        {
            for (int j = 0; j < original[0].length; j++)
            {
                newArray[i][j] = original[i][j];
            }
        }

        return newArray;
    }
}
