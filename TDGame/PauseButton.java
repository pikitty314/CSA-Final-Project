import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PausePlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseButton extends Button
{
    private GameplayWorld world;
    
    public PauseButton(GameplayWorld world)
    {
        this.world = world;
        
        setImage(new GreenfootImage("images/pause-button.png"));
        
        getImage().scale(world.getWindowWidth()/6, world.getWindowWidth()/30);
    }
    
    @Override
    public void uponPress()
    {
        // Pause the game, show play button
        world.pause(true);
        world.addObject(new PlayButton(world), this.getX(), this.getY());
        world.removeObject(this);
    }
}
