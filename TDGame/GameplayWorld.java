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
    
    // game variables displayed to user
    int money = 250;
    int lives = 20;
    int wave = 0;
    
    // to win
    int waveGoal = 25;
    
    // wave related variables
    int enemiesRemainingInWave = 0;
    int enemiesLeftToWinWave = 0;
    int enemyWait = 0;
    GreenfootSound advanceWaveSound;
    
    // Lists containing enemy possibilities
    ArrayList<EnemyTypes> regularEnemyList = new ArrayList<EnemyTypes>();
    ArrayList<EnemyTypes> bossEnemyList = new ArrayList<EnemyTypes>();
    EnemyTypes currentEnemyType = EnemyTypes.DRAGONFLY;
    
    boolean isPlaying;
    
    public GameplayWorld(boolean endless)
    {
        this();
        if(endless)
        {
            waveGoal = Integer.MAX_VALUE;
        }
    }
    
    /**
     * Constructor for objects of class GameplayWorld.
     * 
     */
    public GameplayWorld()
    {
        super(12, 9);
        
        this.setPaintOrder(Button.class, Counter.class, Label.class, InputMenu.class, Enemy.class, Tower.class, Button.class);
        
        // create the grid and populate with tiles
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
        
        isPlaying = true;
        
        // create the menu
        menu = new InputMenu(this);
        addObject(menu, (int)(super.getWindowWidth() * 0.9), super.getWindowHeight() / 2);
        
        // populate enemy lists
        regularEnemyList.add(EnemyTypes.MITE);
        regularEnemyList.add(EnemyTypes.ANT);
        regularEnemyList.add(EnemyTypes.FLY);
        regularEnemyList.add(EnemyTypes.BEETLE);
        regularEnemyList.add(EnemyTypes.LADYBUG);
        regularEnemyList.add(EnemyTypes.HEMIPTERA);
        
        bossEnemyList.add(EnemyTypes.DRAGONFLY);
        
        advanceWaveSound = new GreenfootSound("sounds/wave-defeated.wav");
        advanceWaveSound.setVolume(50);
        
        advanceWave();
    }
    
    public void act()
    {
        // Check if allowed to play
        if (isPlaying)
        {
            play(false);
        }
        else
        {
            pause(false);
            return;
        }
        
        // Create enemies
        if (enemyWait <= 0 && enemiesRemainingInWave > 0)
        {
            int difficultyMultiplier = (int)(wave * (Math.log(wave)/Math.log(5)));
            addObject(
                new Enemy(
                    this,
                    grid,
                    currentEnemyType.getImage(),
                    currentEnemyType.getBaseHealth() * difficultyMultiplier,
                    currentEnemyType.getReward()),
                0, 0);
            enemyWait = 100;
            enemiesRemainingInWave--;
        }
        else
        {
            enemyWait--;
        }
        
        // check if win
        if (enemiesLeftToWinWave <= 0)
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
        enemiesLeftToWinWave--;
        
        if (lives <= 0)
        {
            Greenfoot.setWorld(new GameEndWorld(false));
        }
    }
    
    public int getLivesRemaining()
    {
        return lives;
    }
    
    
    /** Adds money, increases the wave number, and checks if you win */
    public void advanceWave()
    {
        enemyWait = 500;
        addMoney(150);
        
        wave++;
        
        if (wave > waveGoal)
        {
            Greenfoot.setWorld(new GameEndWorld(true));
        }
        else if (wave % 25 != 0)
        {
            currentEnemyType = regularEnemyList.get((int)(Math.random() * regularEnemyList.size()));
            enemiesRemainingInWave = (int)(Math.random() * 15) + wave;
            enemiesLeftToWinWave = enemiesRemainingInWave;
        }
        else
        {
            currentEnemyType = bossEnemyList.get((int)(Math.random() * bossEnemyList.size()));
            enemiesRemainingInWave = 1;
            enemiesLeftToWinWave = enemiesRemainingInWave;
        }
        
        if (wave != 1)
        {
            advanceWaveSound.play();
        }
    }
    
    public int getWave()
    {
        return wave;
    }
    
    public void killEnemy(int award)
    {
        addMoney(award);
        enemiesLeftToWinWave--;
    }
    
    
    /** Sets the selected grid point, or deselected a grid point */
    public void setSelectedGridPoint(GridPoint gridPoint)
    {
        if (selected != gridPoint)
        {
            if (selected != null)
            {
                // deselect
                selected.setTileImage(new GreenfootImage("images/honeycomb-cell.png"));
            }
            selected = gridPoint;
            selected.setTileImage(new GreenfootImage("images/honeycomb-cell-selected.png"));
            
            // which menu based on what the cell has
            if (selected.getTower() == null)
            {
                menu.showAddTowerMenu();
            } 
            else
            {
                menu.showUpgradeTowerMenu();
            } 
        }
        else
        {
            // already selected; deselect            
            selected.setTileImage(new GreenfootImage("images/honeycomb-cell.png"));
            selected = null;
            menu.hideTowerMenu();
        }
    }
    
    public GridPoint getSelectedGridPoint()
    {
        return selected;
    }
    
    /** Add a tower, if possible */
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
        
        // recalculate enemy paths
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies = getObjects(Enemy.class);
        for (Enemy x : enemies)
        {
            x.recalculatePath();
        }
    }
    
    public boolean getIsPlaying()
    {
        return isPlaying;
    }
    
    /** Resume the game */
    public void play(boolean setState)
    {
        // Change the state?
        if (setState)
        {
            isPlaying = true;
        }
        
        // Resume all enemies and towers
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies = getObjects(Enemy.class);
        for (Enemy x : enemies)
        {
            x.resume();
        }
        
        List<Tower> towers = new ArrayList<Tower>();
        towers = getObjects(Tower.class);
        for (Tower x : towers)
        {
            x.resume();
        }
    }
    
    
    /** Pause the game */
    public void pause(boolean setState)
    {
        // Check if to override the state
        if (setState)
        {
            isPlaying = false;
        }
        
        // Pause all enemies and towers
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies = getObjects(Enemy.class);
        for (Enemy x : enemies)
        {
            x.pause();
        }
        
        List<Tower> towers = new ArrayList<Tower>();
        towers = getObjects(Tower.class);
        for (Tower x : towers)
        {
            x.pause();
        }
    }
}
