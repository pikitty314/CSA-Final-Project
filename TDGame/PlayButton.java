import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PausePlayButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayButton extends Button
{
    private GameplayWorld world;
    
    public PlayButton(GameplayWorld world)
    {
        this.world = world;
        
        setImage(new GreenfootImage("images/play-button.png"));
        
        getImage().scale(world.getWindowWidth()/6, world.getWindowWidth()/30);
    }
    
    @Override
    public void uponPress()
    {
        // Resume the game, show pause button
        world.play(true);
        world.addObject(new PauseButton(world), this.getX(), this.getY());
        world.removeObject(this);
    }
}
