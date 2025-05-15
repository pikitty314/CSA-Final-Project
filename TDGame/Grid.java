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
     * Constructor for objects of class Grid if not a square
     */
    public Grid(int xLength, int yLength)
    {
        grid = new GridPoint[yLength][xLength];
    }
    
    /**
     * Generates the grid of tiles with a half tile of padding on top.
     * 
     * @return An array of tiles to be added to the world
     */
    public Tile[][]  generateGrid(GreenfootImage[][] tileImages, boolean[][] blocked)
    {
        int tileSideLength = Globals.WorldConstants.gridTileSizePixels;
        int pixelX = tileSideLength;
        int pixelY = tileSideLength;
        Tile[][] tileGrid = new Tile[grid.length][grid[0].length];
        
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
