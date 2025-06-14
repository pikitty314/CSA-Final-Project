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
        }
    }
    
    public void followPath(int speed)
    {                
        // walk towards the end to do damage
        if (pathComplete)
        {
            this.setRotation(0);
            this.move(speed);
            return;
        }
        
        // check if point reached
        if (inRangeOfAnother(this.getX(), currentPoint.getPixelPoint().getX(), speed)
            && inRangeOfAnother(this.getY(), currentPoint.getPixelPoint().getY(), speed))
        {
            if (path.isEmpty())
            {
                pathComplete = true;
                return;
            }
            nextPathPoint();
        }
        
        // go to next point
        this.turnTowards(currentPoint.getPixelPoint().getX(), currentPoint.getPixelPoint().getY());
        this.move(speed);
    }
    
    /** Selects the next path point */
    public void nextPathPoint()
    {        
        currentPoint = grid.getPoint(path.get(0).getX(),path.get(0).getY());
        path.remove(0);
    }
    
    /** Update the enemies path */
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
        
        // check if dead
        if (health <= 0)
        {
            Greenfoot.playSound("sounds/enemy-death.wav");
            getWorld().removeObject(this);
            world.killEnemy(reward);
        }
    }
    
    public void resume()
    {
        play = true;
    }
    
    public void pause()
    {
        play = false;
    }
}
