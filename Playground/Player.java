import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        userControl();
    }
    
    public void userControl()
    {
        int translationalBoost = 1;
        int rotationalBoost = 1;
        
        if (Greenfoot.isKeyDown("shift"))
        {
            translationalBoost = 3;
        }
        
        if (Greenfoot.isKeyDown("up"))
        {
            move(3 * translationalBoost);
        }
        else if (Greenfoot.isKeyDown("down"))
        {
            move(-1 * translationalBoost);
        }
        
        if (Greenfoot.isKeyDown("left"))
        {
            turn(-3);
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            turn(3);
        }
    }
}
