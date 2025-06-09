import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class GameplayWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameplayWorld extends BaseWorld
{
    Grid grid;
    InputMenu menu;
    
    GridPoint selected = null;
    
    int money = 250;
    int lives = 20;
    int wave = 0;
    
    // wave related variables
    int enemiesRemainingInWave = 0;
    int enemyWait = 0;
    EnemyTypes currentEnemyType = EnemyTypes.BEE;
    
    /**
     * Constructor for objects of class GameplayWorld.
     * 
     */
    public GameplayWorld()
    {
        super(12, 9);
        this.setPaintOrder(Button.class, Counter.class, InputMenu.class, Enemy.class, Tower.class, Button.class);
        grid = new Grid(super.getGridXSize(), super.getGridYSize());
        GreenfootImage[][] images = new GreenfootImage[super.getGridYSize()][super.getGridXSize()];
        boolean[][] block = new boolean[super.getGridYSize()][super.getGridXSize()];
        for (int i = 0; i < images.length; i++)
        {
            for (int j = 0; j < images[i].length; j++)
            {
                images[i][j] = new GreenfootImage("images/honeycomb-cell.png");
                block[i][j] = false;
            }
        }
        Tile[][] tileGrid = grid.generateGrid(this, images, block,super.getTileSideLength());
        for (Tile[] row : tileGrid)
        {
            for(Tile tile : row)
            {
                addObject(tile,tile.getPixelPoint().getX(),tile.getPixelPoint().getY());
            }
        }
        
        menu = new InputMenu(this);
        addObject(menu, (int)(super.getWindowWidth() * 0.9), super.getWindowHeight() / 2);
        
        /*
        for (int i = 0; i < 8; i++)
        {
            path.add(new Point(i,i));
        }
        for (int j = 8; j < 12; j++)
        {
            path.add(new Point(j,7));
        }
        */ 
       
        advanceWave();
    }
    
    public void act()
    {
        // Create enemies
        if (enemyWait <= 0)
        {
            addObject(
                new Enemy(
                    this,
                    grid,
                    currentEnemyType.getImage(),
                    currentEnemyType.getBaseHealth() * wave,
                    currentEnemyType.getReward()),
                0, 0);
            enemyWait = 100;
        }
        else
        {
            enemyWait--;
        }
        
        if (enemiesRemainingInWave <= 0)
        {
            advanceWave();
        }
    }
    
    public Grid getGrid()
    {
        return grid;
    }
    
    public InputMenu getMenu()
    {
        return menu;
    }
    
    public void addMoney(int amount)
    {
        money += amount;
    }
    
    public void spendMoney(int amount)
    {
        money -= amount;
    }
    
    public int getMoney()
    {
        return money;
    }
    
    
    public void loseLife()
    {
        lives--;
    }
    
    public int getLivesRemaining()
    {
        return lives;
    }
    
    
    public void advanceWave()
    {
        enemiesRemainingInWave = (int)(Math.random() * 30) + 1;
        enemyWait = 500;
        
        addMoney(150);
        
        wave++;
    }
    
    public int getWave()
    {
        return wave;
    }
    
    public void killEnemy(int award)
    {
        addMoney(award);
        enemiesRemainingInWave--;
    }
    
    public void setSelectedGridPoint(GridPoint gridPoint)
    {
        if (selected != gridPoint)
        {
            if (selected != null)
            {
                selected.setTileImage(new GreenfootImage("images/honeycomb-cell.png"));
            }
            selected = gridPoint;
            selected.setTileImage(new GreenfootImage("images/honeycomb-cell-selected.png"));
            if (selected.getTower() == null)
            {
                menu.showAddTowerMenu();
            } 
            else
            {
                menu.showUpgradeTowerMenu();
            }
            // System.out.println(gridPoint.getTilePoint());    
        }
        else
        {
            selected.setTileImage(new GreenfootImage("images/honeycomb-cell.png"));
            selected = null;
            menu.hideTowerMenu();
            // System.out.println("Deselected: " + gridPoint.getTilePoint());
        }
    }
    
    public GridPoint getSelectedGridPoint()
    {
        return selected;
    }
    
    public void addTower(TowerTypes type)
    {
        // check that there is enough money; has filler values
        if (money < type.price())
        {
            return;
        }
        
        // check that the enemies still have a path to the end
        boolean[][] pfGrid = grid.getPathfinderGrid();
        pfGrid[selected.getTilePoint().getY()][selected.getTilePoint().getX()] = false;
        ArrayList<Point> newPath = AStarPathfinder.pathfinder(pfGrid, new Point(0,0), new Point(11,8));
        if(newPath.isEmpty() || selected.getTilePoint().equals(new Point(0,0)))
        {
            System.out.println("ERROR! Cannot block enemies from end!");
            return;
        }
        
        money -= type.price();
        
        Tower toAdd = new Tower(this, type, selected);
        addObject(toAdd, selected.getPixelPoint().getX(), selected.getPixelPoint().getY());
        setSelectedGridPoint(selected); // Deselect the gridpoint
        
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies = getObjects(Enemy.class);
        for (Enemy x : enemies)
        {
            x.recalculatePath();
        }
    }
}
