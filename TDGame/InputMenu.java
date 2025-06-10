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
    
    ArrayList<AddTowerButton> addTowerButtons = new ArrayList<AddTowerButton>();
    ArrayList<Button> upgradeTowerButtons = new ArrayList<Button>();
    ArrayList<Label> activeLabels = new ArrayList<Label>();
    
    public InputMenu(GameplayWorld world)
    {
        this.world = world;
        
        this.getImage().scale(world.getWindowWidth()/5, world.getWindowHeight());
        
        buttonSideLength = (int) (world.getWindowWidth()/18);
        
        world.addObject(new Counter("$", () -> world.getMoney(), world.getWindowWidth()/24), 9*(world.getWindowWidth()/10), world.getWindowHeight()/10);
        world.addObject(new Counter("Lives: ", () -> world.getLivesRemaining(), world.getWindowWidth()/40), 9*(world.getWindowWidth()/10), 4*world.getWindowHeight()/25);
        world.addObject(new Counter("Wave: ", () -> world.getWave(), world.getWindowWidth()/40), 9*(world.getWindowWidth()/10), world.getWindowHeight()/5);
        
        world.addObject(new PauseButton(world), (int)(world.getWindowWidth() * 0.9), 28 * (world.getWindowHeight() / 30));
        
        addTowerButtons.add(new AddTowerButton(world, buttonSideLength, TowerTypes.STINGER));
        addTowerButtons.add(new AddTowerButton(world, buttonSideLength, TowerTypes.POLLEN_TURRET));
        addTowerButtons.add(new AddTowerButton(world, buttonSideLength, TowerTypes.STAR_SLINGER));
        addTowerButtons.add(new AddTowerButton(world, buttonSideLength, TowerTypes.TASER));
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
        hideTowerMenu();
        
        int xPos = (int)(buttonSideLength * 15.59);
        int yPos = (int) (buttonSideLength * 3.5);
        
        boolean rowComplete = false;
        for (AddTowerButton button : addTowerButtons)
        {
            world.addObject(button, xPos, yPos);
            
            Label label = new Label("$" + button.getTowerPrice(), 25);
            activeLabels.add(label);
            world.addObject(label, xPos, yPos + 50);
            
            if(!rowComplete)
            {
                xPos += (int)(buttonSideLength * 1.3);
                rowComplete = true;
            }
            else
            {
                yPos += (int)(buttonSideLength * 1.6);
                xPos = (int)(buttonSideLength * 15.59);
                rowComplete = false;
            }
        }
    }
    
    public void showUpgradeTowerMenu()
    {
        hideTowerMenu();
        
        int xPos = (int)(buttonSideLength * 15.59);
        int yPos = (int) (buttonSideLength * 3.5);
        
        upgradeTowerButtons.add(new UpgradeTowerButton(world, buttonSideLength, world.getSelectedGridPoint().getTower()));
        upgradeTowerButtons.add(new SellTowerButton(world, buttonSideLength, world.getSelectedGridPoint().getTower()));
        
        boolean rowComplete = false;
        
        for (Button button : upgradeTowerButtons)
        {
            world.addObject(button, xPos, yPos);
            
            if (button instanceof SellTowerButton)
            {
                Label label = new Label("+$" + ((SellTowerButton)button).getTowerSaleValue(), 25);
                activeLabels.add(label);
                world.addObject(label, xPos, yPos + 50);
            }
            else if (button instanceof UpgradeTowerButton)
            {
                Label label = new Label("$" + ((UpgradeTowerButton)button).getTowerUpgradePrice(), 25);
                activeLabels.add(label);
                world.addObject(label, xPos, yPos + 50);
            }
            
            if(!rowComplete)
            {
                xPos += (int)(buttonSideLength * 1.3);
                rowComplete = true;
            }
            else
            {
                yPos += (int)(buttonSideLength * 1.6);
                xPos = (int)(buttonSideLength * 15.59);
                rowComplete = false;
            }
        }
    }
    
    public void hideTowerMenu()
    {
        for (AddTowerButton button : addTowerButtons)
        {
            world.removeObject(button);
        }
        
        for (int i = 0; i < upgradeTowerButtons.size();)
        {
            world.removeObject(upgradeTowerButtons.remove(i));
        }
        
        for (int i = 0; i < activeLabels.size();)
        {
            world.removeObject(activeLabels.remove(i));
        }
    }
}
