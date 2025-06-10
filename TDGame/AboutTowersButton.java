import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AboutTowersButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AboutTowersButton extends Button
{
    WelcomeWorld world;
    
    public AboutTowersButton(WelcomeWorld world, int size)
    {
        super();
        
        this.world = world;
        
        GreenfootImage picture = new GreenfootImage("About Towers", size, Color.BLACK, new Color(255, 230, 193, 255));
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        world.drawAboutTowersPage();
    }
}
