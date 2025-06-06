import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayButton extends Button
{
    public PlayButton(int size)
    {
        super();
        GreenfootImage picture = new GreenfootImage("images/play-button.png");
        picture.scale(size, size);
        this.setImage(picture);
        
    }
    
    @Override
    public void uponPress()
    {
        Greenfoot.setWorld(new GameplayWorld());
    }
}
