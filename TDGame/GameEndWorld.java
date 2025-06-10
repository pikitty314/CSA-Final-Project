import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameEndWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameEndWorld extends BaseWorld
{
    /**
     * Constructor for objects of class GameEndWorld.
     * (world shown after win or lose)
     */
    public GameEndWorld(boolean win)
    {
        GreenfootImage background;
        if(win)
        {
            background =  new GreenfootImage("images/win-screen.png");
            GreenfootSound playMe = new GreenfootSound("sounds/win.wav");
            playMe.setVolume(75);
            playMe.play();
        }
        else
        {
            background = new GreenfootImage("images/lose-screen.png");
        }
        background.scale(super.getWindowWidth(), super.getWindowHeight());
        setBackground(background);
        
        addObject(new ReturnToWelcomeButton(super.getTileSideLength() * 2), super.getWindowWidth() / 2,(int)(super.getWindowHeight() * 0.85));
    }
}
