import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private GameplayWorld world;
    
    private boolean play = true;
    
    private ArrayList<Point> path;
    private Grid grid;
    private GridPoint currentPoint;
    
    private int health;
    
    private boolean pathComplete = false;
    
    public Enemy(GameplayWorld world, ArrayList<Point> path, Grid grid, int maxHealth)
    {
        this.world = world;
        this.path = new ArrayList<Point>(path);
        this.grid = grid;
        
        health = maxHealth;

        nextPathPoint();
        System.out.println(currentPoint.getTilePoint());
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (play)
        {
            followPath(1);
        }
    }
    
    public void followPath(int speed)
    {                
        if (pathComplete)
        {
            this.setRotation(0);
            this.move(speed);
            return;
        }
        
        if (inRangeOfAnother(this.getX(), currentPoint.getPixelPoint().getX(), speed)
            && inRangeOfAnother(this.getY(), currentPoint.getPixelPoint().getY(), speed))
        {
            if (path.isEmpty())
            {
                pathComplete = true;
                System.out.println("Reached end of path");
                return;
            }
            nextPathPoint();
            System.out.println(currentPoint.getTilePoint());
        }
        
        this.turnTowards(currentPoint.getPixelPoint().getX(), currentPoint.getPixelPoint().getY());
        this.move(speed);
    }
    
    public void nextPathPoint()
    {        
        currentPoint = grid.getPoint(path.get(0).getX(),path.get(0).getY());
        path.remove(0);
    }
    
    public int getRemainingPathLength()
    {
        return path.size();
    }
    
    /**
     * Returns true if a within range of b.
     */
    public boolean inRangeOfAnother(int a, int b, int range)
    {
        if (a - range <= b && b <= a + range)
        {
            return true;
        }
        return false;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public void doDamage(int amount)
    {
        health -= amount;
        if (health <= 0)
        {
            getWorld().removeObject(this);
            world.addMoney(50);
        }
    }
}
