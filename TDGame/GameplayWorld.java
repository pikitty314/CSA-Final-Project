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
        grid = new Grid(Globals.WorldConstants.gridSideLength);
        GreenfootImage[][] images = {{new GreenfootImage("images/blue-box.png"),new GreenfootImage("images/blue-box.png")},{new GreenfootImage("images/blue-box.png"),new GreenfootImage("images/blue-box.png")}};
        boolean[][] block = {{false,false},{false,false}};
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
