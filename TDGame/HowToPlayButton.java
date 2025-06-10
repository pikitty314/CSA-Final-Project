import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HowToPlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HowToPlayButton extends Button
{
    WelcomeWorld world;
    
    public HowToPlayButton(WelcomeWorld world, int size)
    {
        super();
        
        this.world = world;
        
        GreenfootImage picture = new GreenfootImage("How To Play", size, Color.BLACK, new Color(255, 230, 193, 255));
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        world.drawHowToPlayPage();
    }
}
