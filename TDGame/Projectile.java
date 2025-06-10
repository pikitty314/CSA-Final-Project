import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    Enemy target;
    int damage;
    
    public Projectile(Enemy target, GreenfootImage image, int scale, int damage)
    {
        this.target = target;
        this.damage = damage;
        
        image.scale(scale, scale);
        this.setImage(image);
    }
    
    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        List<Enemy> allEnemies = new ArrayList<Enemy>();
        allEnemies = getWorld().getObjects(Enemy.class);
        
        // Loop through every actor to check if target is still in world
        for (Enemy enemy : allEnemies)
        {
            if (enemy == target)
            {
                turnTowards(target.getX(), target.getY());
                move(5);
                
                if (getOneIntersectingObject(Enemy.class) != null)
                {
                    ((Enemy)getOneIntersectingObject(Enemy.class)).doDamage(damage);
                    getWorld().removeObject(this);
                }
                
                return;
            }
        }
        
        // Target is not in world, remove this projectile
        getWorld().removeObject(this);
    }
}
