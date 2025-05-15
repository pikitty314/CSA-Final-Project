/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grid  
{
    GridPoint[][] grid;

    /**
     * Constructor for objects of class Grid
     */
    public Grid(int xSize,int ySize)
    {
        grid = new GridPoint[ySize][xSize];
    }
}
