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
    
    ArrayList<AddTowerButton> towerButtons = new ArrayList<AddTowerButton>();
    
    public InputMenu(GameplayWorld world)
    {
        this.world = world;
        
        this.getImage().scale(world.getWindowWidth()/5, world.getWindowHeight());
        
        buttonSideLength = (int) (world.getWindowWidth()/18);
        
        world.addObject(new Counter("$", () -> world.getMoney(), world.getWindowWidth()/24), 9*(world.getWindowWidth()/10), world.getWindowHeight()/10);
        world.addObject(new Counter("Lives: ", () -> world.getLivesRemaining(), world.getWindowWidth()/40), 9*(world.getWindowWidth()/10), 4*world.getWindowHeight()/25);
        world.addObject(new Counter("Wave: ", () -> world.getWave(), world.getWindowWidth()/40), 9*(world.getWindowWidth()/10), world.getWindowHeight()/5);
        
        towerButtons.add(new AddTowerButton(world, buttonSideLength, TowerTypes.LIGHTHOUSE));
        towerButtons.add(new AddTowerButton(world, buttonSideLength, new GreenfootImage("images/button-green.png")));
        towerButtons.add(new AddTowerButton(world, buttonSideLength, new GreenfootImage("images/VeryBasicTitle.png")));
    }
    
    
    /**
     * Act - do whatever the InputMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void showAddTowerMenu()
    {
        int xPos = (int)(buttonSideLength * 15.59);
        int yPos = (int) (buttonSideLength * 3.5);
        
        boolean rowComplete = false;
        for (AddTowerButton button : towerButtons)
        {
            world.addObject(button, xPos, yPos);
            
            if(!rowComplete)
            {
                xPos += (int)(buttonSideLength * 1.3);
                rowComplete = true;
            }
            else
            {
                yPos += (int)(buttonSideLength * 1.3);
                xPos = (int)(buttonSideLength * 15.59);
                rowComplete = false;
            }
        }
    }
    
    public void hideAddTowerMenu()
    {
        for (AddTowerButton button : towerButtons)
        {
            world.removeObject(button);
        }
    }
}
