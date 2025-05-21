import java.util.ArrayList;

public class BruteForcePathfinder
{
    static int count = 0;
    
    public static ArrayList<Point> pathfinder(boolean[][] grid, Point start, Point end)
    {
        int max = 0;
        count = 0;
        
        max += Math.abs(start.getX() - end.getX());
        max += Math.abs(start.getY() - end.getY());
        
        for (boolean[] row : grid)
        {
            for (boolean pt : row)
            {
                if (!pt)
                {
                    max += 2;
                }
            }
        }
        
        return pathfinder(grid, start, end, 0, max);
    }
    
    
    /**
     * A very simple and probably relatively inefficent pathfinder.
     * 
     * @param grid A 2D array containing a boolean to represent each point that you are or are not allowed to go.
     * @param start The point to start at.
     * @param end The point to end at.
     * @return The path, in order with the first step first and the last step last in the List.
     */
    public static ArrayList<Point> pathfinder(boolean[][] grid, Point start, Point end, int length, int maxLengthAllowed)
    {    
        int x = start.getX();
        int y = start.getY();
        
        count++;
        
        if (x < 0 ||
            y < 0 || 
            x >= grid[0].length || 
            y >= grid.length || 
            !grid[y][x] ||
            length > maxLengthAllowed)
        {
            // illegal location
            return new ArrayList<>();
        }

        ArrayList<Point> path = new ArrayList<>();
        
        if (start.equals(end))
        {
            // found the end!
            path.add(0, start);
            return path;
        }
        
        boolean[][] newGrid = copy2DBooleanArray(grid);
        newGrid[y][x] = false;

        // check all directions
        ArrayList<Point> up = new ArrayList<Point>(pathfinder(newGrid, new Point(x, y + 1), end, length + 1, maxLengthAllowed));
        ArrayList<Point> down = new ArrayList<Point>(pathfinder(newGrid, new Point(x, y - 1), end, length + 1, maxLengthAllowed));
        ArrayList<Point> right = new ArrayList<Point>(pathfinder(newGrid, new Point(x + 1, y), end, length + 1, maxLengthAllowed));
        ArrayList<Point> left = new ArrayList<Point>(pathfinder(newGrid, new Point(x - 1, y), end, length + 1, maxLengthAllowed));
        
        path = new ArrayList<Point>(getShortest(up, down, right, left));
        if (!path.isEmpty())
        {
            // only add this point if a valid path is found
            path.add(0, start);
        }
        // newGrid[y][x] = true; // Should be unnecessary because the ArrayList is deleted anyways?
        System.out.println("Path at " + count + " calls: " + path);
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
