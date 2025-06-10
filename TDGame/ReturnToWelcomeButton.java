import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ReturnToWelcomeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReturnToWelcomeButton extends Button
{
    public ReturnToWelcomeButton(int size)
    {
        super();
        GreenfootImage picture = new GreenfootImage("images/home-button.png");
        picture.scale(size, size);
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        Greenfoot.setWorld(new WelcomeWorld());
    }
}
