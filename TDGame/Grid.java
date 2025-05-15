import greenfoot.*;
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
    public Grid(int length)
    {
        grid = new GridPoint[length][length];
    }
    
    /**
     * Generates the grid of tiles with a half tile of padding on top.
     * 
     * @return An array of tiles to be added to the world
     */
    public Tile[][]  generateGrid(GreenfootImage[][] tileImages, boolean[][] blocked)
    {
        int pixelX;
        int pixelY;
        int tileSideLength;
        Tile[][] tileGrid = new Tile[grid.length][grid[0].length];
        
        if (Globals.WindowConstants.windowWidth > Globals.WindowConstants.windowHeight)
        {
            tileSideLength = (int)(Globals.WindowConstants.windowHeight / (grid.length + 1));
            pixelX = tileSideLength;
            pixelY = tileSideLength;
        }
        
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[y].length; x++)
            {
                tileImages[y][x].scale(tileSideLength, tileSideLength);
                
                Tile tile = new Tile(tileImages[y][x], pixelX, pixelY);
                tileGrid[y][x] = tile;
                
                boolean isBlocked = blocked[y][x];
                
                grid[y][x] = new GridPoint(tile, y, x, pixelX, pixelY, !isBlocked, !isBlocked);
                
                pixelX += tileSideLength;
            }
            pixelX = tileSideLength;
            pixelY += tileSideLength;
        }
        
        return tileGrid;
    }
    
    /**
     * Gets the grid for the Pathfinder
     */
    public boolean[][] getPathfinderGrid()
    {
        boolean[][] pathfinderGrid = new boolean[grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                pathfinderGrid[i][j] = grid[i][j].canBeInEnemyPath();
            }
        }
        
        return pathfinderGrid;
    }
}
