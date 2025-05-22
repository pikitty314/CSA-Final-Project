import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Actor
{
    private boolean play = true;
    
    private GridPoint position;
    
    private int towerRange;
    
    public Tower(GreenfootImage image, GridPoint position, int towerRange)
    {
        super();
        
        this.position = position;
        this.towerRange = towerRange;
        
        this.setImage(image);
        this.setLocation(position.getPixelPoint().getX(), position.getPixelPoint().getY());
        
        position.placeTower(this);
    }
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (play)
        {
            runAttackSystem();
        }
    }
    
    public void runAttackSystem()
    {
        List<Enemy> enemies = this.getObjectsInRange(towerRange, Enemy.class);
        Enemy target = null;
        
        // Find the enemy closest to the end
        for (Enemy enemy : enemies)
        {
            if (target == null || target.getRemainingPathLength() <= enemy.getRemainingPathLength())
            {
                target = enemy;
            }
        }
        
        if (target == null)
        {
            setRotation(0);
            return;
        }
        else
        {
            turnTowards(target.getX(), target.getY());
            target.doDamage(1);
        }        
    }
}
