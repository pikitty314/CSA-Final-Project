import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.IntSupplier;
import java.util.ArrayList;

/**
 * Write a description of class InputMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InputMenu extends Actor
{
    GameplayWorld world;
    
    final int buttonSideLength;
    
    GridPoint selected = null;
    
    public InputMenu(GameplayWorld world)
    {
        this.world = world;
        
        this.getImage().scale(world.getWindowWidth()/5, world.getWindowHeight());
        
        buttonSideLength = world.getWindowWidth()/20;
        
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
        if (selected != gridPoint)
        {
            selected = gridPoint;
            showAddTowerMenu();
            System.out.println(gridPoint.getTilePoint());    
        }
        else
        {
            selected = gridPoint;
            System.out.println("Deselected: " + gridPoint.getTilePoint());
        }
    }
    
    public void showAddTowerMenu()
    {
        int xPos = buttonSideLength * 17;
        int yPos = buttonSideLength * 4;
        world.addObject(new AddTowerButton(world, buttonSideLength, new GreenfootImage("images/lighthouse.png")), xPos, yPos);
    }
}
