import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {        
        if(Greenfoot.mouseClicked(this))
        {
            uponPress();
        }
    }
    
    /** Method to be overridden by subclasses to have functionality upon click */
    public void uponPress()
    {
        System.out.println("button pressed");
    }
}
