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
    
    ArrayList<Point> defaultPath;
    
    int money = 100;
    int lives = 20;
    int wave = 1;
    
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
                images[i][j] = new GreenfootImage("images/blue-box.png");
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
        
        defaultPath = new ArrayList<Point>();
        
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
        defaultPath = AStarPathfinder.pathfinder(grid.getPathfinderGrid(), new Point(0,0), new Point(11,8));
    
        addObject(new Enemy(this, defaultPath,grid, 100), 10, 10);
        addObject(new Enemy(this, defaultPath,grid, 100), 20, 20);
        addObject(new Enemy(this, defaultPath,grid, 100), 5, 5);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        addObject(new Enemy(this, defaultPath,grid, 100), 0, 0);
        
        
        addObject(new Tower(this, new GreenfootImage("images/lighthouse.png"), grid.getPoint(5,5), 3 * super.getTileSideLength()), grid.getPoint(5,5).getPixelPoint().getX(), grid.getPoint(5,5).getPixelPoint().getY());
    }
    
    public void act()
    {
        
    }
    
    public Grid getGrid()
    {
        return grid;
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
        wave++;
    }
    
    public int getWave()
    {
        return wave;
    }
    
    
    public void setSelectedGridPoint(GridPoint gridPoint)
    {
        if (selected != gridPoint)
        {
            selected = gridPoint;
            menu.showAddTowerMenu();
            System.out.println(gridPoint.getTilePoint());    
        }
        else
        {
            selected = null;
            menu.hideAddTowerMenu();
            System.out.println("Deselected: " + gridPoint.getTilePoint());
        }
    }
    
    public GridPoint getSelectedGridPoint()
    {
        return selected;
    }
    
    public void addTower()
    {
        // check that the enemies still have a path to the end
        boolean[][] pfGrid = grid.getPathfinderGrid();
        pfGrid[selected.getTilePoint().getY()][selected.getTilePoint().getX()] = false;
        ArrayList<Point> newPath = AStarPathfinder.pathfinder(grid.getPathfinderGrid(), new Point(0,0), new Point(11,8));
        if(newPath.isEmpty())
        {
            System.out.println("ERROR! Cannot block enemies from end!");
            return;
        }
        
        Tower toAdd = new Tower(this, new GreenfootImage("images/lighthouse.png"), selected, 3 * super.getTileSideLength());
        addObject(toAdd, selected.getPixelPoint().getX(), selected.getPixelPoint().getY());
        setSelectedGridPoint(selected); // Deselect the gridpoint
        
        // Recalculate the paths
        defaultPath = newPath;
        System.out.println(defaultPath);
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies = getObjects(Enemy.class);
        for (Enemy x : enemies)
        {
            x.recalculatePath();
        }
    }
}
