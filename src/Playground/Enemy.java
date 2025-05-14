import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int targetX;
    private int targetY;
    
    public Enemy()
    {
        super();
        targetX = 300;
        targetY = 300;
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turnTowardsTarget();
        moveTowardsTarget();
    }
    
    public void turnTowardsTarget()
    {
        this.turnTowards(MyWorld.targetLocation.getX(), MyWorld.targetLocation.getY());
    }
    
    public void moveTowardsTarget()
    {
        move(1);
    }
}
