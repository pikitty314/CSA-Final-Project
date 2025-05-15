import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameplayWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameplayWorld extends World
{
    Grid grid;
    /**
     * Constructor for objects of class GameplayWorld.
     * 
     */
    public GameplayWorld()
    {
        super(Globals.WindowConstants.windowWidth, Globals.WindowConstants.windowHeight, Globals.WindowConstants.windowScale);
        grid = new Grid(Globals.WorldConstants.gridXSize, Globals.WorldConstants.gridYSize);
        GreenfootImage[][] images = new GreenfootImage[Globals.WorldConstants.gridYSize][Globals.WorldConstants.gridXSize];
        boolean[][] block = new boolean[Globals.WorldConstants.gridYSize][Globals.WorldConstants.gridXSize];
        for (int i = 0; i < images.length; i++)
        {
            for (int j = 0; j < images[i].length; j++)
            {
                images[i][j] = new GreenfootImage("images/blue-box.png");
                block[i][j] = false;
            }
        }
        Tile[][] tileGrid = grid.generateGrid(images, block);
        for (Tile[] row : tileGrid)
        {
            for(Tile tile : row)
            {
                addObject(tile,tile.getPixelPose().getX(),tile.getPixelPose().getY());
            }
        }
    }
    
    public void act()
    {
        
    }
}
