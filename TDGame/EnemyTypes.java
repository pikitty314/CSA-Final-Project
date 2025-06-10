import greenfoot.*;

/**
 * Write a description of class EnemiesEnum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum EnemyTypes  
{
    MITE (new GreenfootImage("images/enemies/mite.png"), 31, 40, 50, 10),
    ANT (new GreenfootImage("images/enemies/ant.png"), 50, 26, 200, 25),
    FLY (new GreenfootImage("images/enemies/fly.png"), 50, 34, 200, 25),
    BEETLE (new GreenfootImage("images/enemies/beetle.png"), 52, 34, 300, 35),
    LADYBUG (new GreenfootImage("images/enemies/ladybug.png"), 42, 50, 300, 35),
    HEMIPTERA (new GreenfootImage("images/enemies/hemiptera-green.png"), 50, 47, 150, 20),
    DRAGONFLY (new GreenfootImage("images/enemies/dragonfly.png"), 120, 90, 2000, 300, true);
    
    public final GreenfootImage image;
    public final int reward;
    public final int baseHealth;
    public final boolean isBoss;
    
    /** Constructor if you want to declare whether or not an enemy is a boss */
    EnemyTypes(GreenfootImage image, int xScale, int yScale, int baseHealth, int reward, boolean isBoss)
    {
        this.image = image;
        this.image.scale(xScale, yScale);
        this.baseHealth = baseHealth;
        this.reward = reward;
        this.isBoss = isBoss;
    }
    
    /** Constructor for non-boss enemies only */
    EnemyTypes(GreenfootImage image, int xScale, int yScale, int baseHealth, int reward)
    {
        this.image = image;
        this.image.scale(xScale, yScale);
        this.baseHealth = baseHealth;
        this.reward = reward;
        this.isBoss = false;
    }
    
    public GreenfootImage getImage()
    {
        return image;
    }
    
    public int getBaseHealth()
    {
        return baseHealth;
    }
    
    public int getReward()
    {
        return reward;
    }
}
