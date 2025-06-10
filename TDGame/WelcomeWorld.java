import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeWorld extends BaseWorld
{
    GreenfootImage title;
    /**
     * Constructor for objects of class Title.
     */
    public WelcomeWorld()
    {    
        super();
        title = new GreenfootImage("images/title-page.png");
        title.scale(super.getWindowWidth(), super.getWindowHeight());
        setBackground(title);
        
        addObject(new StartButton(super.getTileSideLength()), super.getWindowWidth() / 2 , (int)(super.getWindowHeight() / 1.7));
    }
}
