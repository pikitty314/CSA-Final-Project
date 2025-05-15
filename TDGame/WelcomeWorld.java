import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeWorld extends World
{
    GreenfootImage title;
    /**
     * Constructor for objects of class Title.
     */
    public WelcomeWorld()
    {    
        super(Globals.WindowConstants.windowWidth, Globals.WindowConstants.windowHeight, Globals.WindowConstants.windowScale);
        title = new GreenfootImage("images/VeryBasicTitle.png");
        title.scale(Globals.WindowConstants.windowWidth, Globals.WindowConstants.windowHeight);
        setBackground(title);
        
        addObject(new PlayButton(), 640, 400);
    }
}
