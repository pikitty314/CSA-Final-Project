import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaseWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaseWorld extends World
{
    // Global variables
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final int WINDOW_SCALE = 1;
    
    public final int GRID_X_SIZE;
    public final int GRID_Y_SIZE;
    
    /**
     * Default constructor for objects of class BaseWorld.
     */
    public BaseWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_SCALE); 
        GRID_X_SIZE = 12;
        GRID_Y_SIZE = 9;
    }
    
    /**
     * Constructor for BaseWorld with option to set GRID_X_SIZE and GRID_Y_SIZE
     */
    public BaseWorld(int gridX, int gridY)
    {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_SCALE);
        GRID_X_SIZE = gridX;
        GRID_Y_SIZE = gridY;
    }
    
    // Global variable getters
    public int getWindowWidth()
    {
        return WINDOW_WIDTH;
    }
    
    public int getWindowHeight()
    {
        return WINDOW_HEIGHT;
    }
    
    public int getWindowScale()
    {
        return WINDOW_SCALE;
    }
    
    public int getGridXSize()
    {
        return GRID_X_SIZE;
    }
    
    public int getGridYSize()
    {
        return GRID_Y_SIZE;
    }
    
    // Calculations based on global variables
    public int getTileSideLength()
    {
        if((WINDOW_WIDTH / (double)GRID_X_SIZE) < (WINDOW_HEIGHT / (double)GRID_Y_SIZE))
        {
            return (int)(WINDOW_WIDTH / (GRID_X_SIZE + 1.0));
        }
        return (int)(WINDOW_HEIGHT / (GRID_Y_SIZE + 1.0));
    }
}
