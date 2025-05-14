import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Target here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Target extends Actor
{
    /**
     * Act - do whatever the Target wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        List<Enemy> intersecting = new ArrayList<Enemy>();
        if (!getObjectsInRange(10, Enemy.class).isEmpty())
        {
            intersecting = getObjectsInRange(10, Enemy.class);
        }
        
        for(Enemy x : intersecting)
        {
            getWorld().removeObject(x);
        }
    }
}
