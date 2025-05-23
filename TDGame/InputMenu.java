import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.IntSupplier;

/**
 * Write a description of class InputMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InputMenu extends Actor
{
    GridPoint selected = null;
    
    public InputMenu(GameplayWorld world)
    {
        this.getImage().scale(world.getWindowWidth()/5, world.getWindowHeight());
        world.addObject(new Counter("$", () -> world.getMoney(), world.getWindowWidth()/24), 9*(world.getWindowWidth()/10), world.getWindowHeight()/10);
        world.addObject(new Counter("Lives: ", () -> world.getLivesRemaining(), world.getWindowWidth()/40), 9*(world.getWindowWidth()/10), 4*world.getWindowHeight()/25);
        world.addObject(new Counter("Wave: ", () -> world.getWave(), world.getWindowWidth()/40), 9*(world.getWindowWidth()/10), world.getWindowHeight()/5);
    }
    
    
    /**
     * Act - do whatever the InputMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void setSelectedGridPoint(GridPoint gridPoint)
    {
        selected = gridPoint;
        System.out.println(gridPoint.getPixelPoint().getX() + ", " + gridPoint.getPixelPoint().getY()); 
    }
}
