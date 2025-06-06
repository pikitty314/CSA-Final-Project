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
    private int reward;
    
    private boolean pathComplete = false;
    
    public Enemy(GameplayWorld world, Grid grid, GreenfootImage image, int maxHealth, int reward)
    {
        this.world = world;
        this.grid = grid;
        
        this.path = AStarPathfinder.pathfinder(grid.getPathfinderGrid(), new Point(0,0), new Point(11,8));
        
        this.health = maxHealth;
        this.reward = reward;
        
        this.setImage(image);
        
        nextPathPoint();
    }
    
    public Enemy(GameplayWorld world, Grid grid, int maxHealth)
    {
        this.world = world;
        this.grid = grid;
        
        this.path = AStarPathfinder.pathfinder(grid.getPathfinderGrid(), new Point(0,0), new Point(11,8));
        
        health = maxHealth;
        reward = 25;
        
        nextPathPoint();
        recalculatePath();
        // System.out.println(currentPoint.getTilePoint());
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
            
            if(this.getX() > (int)(world.getWindowWidth() * 0.9))
            {
                world.loseLife();
                world.removeObject(this);
            }
        }
        
        // Sometimes the path re-randomizes just for fun
        if (Math.random() < 0.001)
        {
            recalculatePath();
            // System.out.println("randomized");
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
                // System.out.println("Reached end of path");
                return;
            }
            nextPathPoint();
            // System.out.println(currentPoint.getTilePoint());
        }
        
        /* This doesn't work yet
        if (currentPoint.getPixelPoint().getX() > this.getX())
        {
            this.setRotation(0);
        } else if (currentPoint.getPixelPoint().getY() > this.getY())
        {
            this.setRotation(270);
        } else if (currentPoint.getPixelPoint().getY() < this.getY())
        {
            this.setRotation(90);
        } else if (currentPoint.getPixelPoint().getX() < this.getX())
        {
            this.setRotation(180);
        } else
        {
            this.turnTowards(currentPoint.getPixelPoint().getX(), currentPoint.getPixelPoint().getY());
        } */
        this.turnTowards(currentPoint.getPixelPoint().getX(), currentPoint.getPixelPoint().getY());
        this.move(speed);
    }
    
    public void nextPathPoint()
    {        
        currentPoint = grid.getPoint(path.get(0).getX(),path.get(0).getY());
        path.remove(0);
    }
    
    public void recalculatePath()
    {
        path = AStarPathfinder.pathfinder(grid.getPathfinderGrid(), currentPoint.getTilePoint(), new Point(11,8));
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
            world.killEnemy(reward); // 25 is just a filler value
        }
    }
}
