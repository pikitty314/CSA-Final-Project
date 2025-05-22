import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.IntSupplier;

/**
 * Write a description of class InputMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InputMenu extends Actor
{
    public InputMenu(GameplayWorld world, IntSupplier money)
    {
        this.getImage().scale(world.getWindowWidth()/5, world.getWindowHeight());
        getWorld().addObject(new Counter("$"), _int_, _int_)
    }
    
    /**
     * Act - do whatever the InputMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
