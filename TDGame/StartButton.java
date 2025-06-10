import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Button
{
    public StartButton(int size)
    {
        super();
        GreenfootImage picture = new GreenfootImage("images/start-button.png");
        picture.scale(size, size);
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        Greenfoot.setWorld(new GameplayWorld());
    }
}
