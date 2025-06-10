import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoToTitleScreenButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoToTitleScreenButton extends Button
{
    WelcomeWorld world;
    
    public GoToTitleScreenButton(WelcomeWorld world, int size)
    {
        super();
        
        this.world = world;
        
        GreenfootImage picture = new GreenfootImage("Back", size, Color.BLACK, new Color(255, 230, 193, 255));
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        world.removeObject(this);
        world.drawWelcomePage();
    }
}
