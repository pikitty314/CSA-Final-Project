import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    Player player;
    public static Target target;
    
    public static int targetX = 640;
    public static int targetY = 680;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        player = new Player();
        target = new Target();
        addObject(player, 100, 100);
        addObject(target, targetX, targetY);
        addObject(new Enemy(), 500, 500);
    }
}
