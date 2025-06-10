import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartEndlessButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartEndlessButton extends Button
{
    public StartEndlessButton(int size)
    {
        super();
        GreenfootImage picture = new GreenfootImage("images/start-endless-button.png");
        picture.scale(size, size);
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        Greenfoot.setWorld(new GameplayWorld(true));
    }
}
