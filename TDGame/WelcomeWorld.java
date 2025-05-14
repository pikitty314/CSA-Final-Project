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
     * 
     */
    public WelcomeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        title = new GreenfootImage("images/VeryBasicTitle.png");
        title.scale(1280,720);
        setBackground(title);
        
        addObject(new PlayButton(), 640, 400);
    }
}
