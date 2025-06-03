import greenfoot.*;

/**
 * Write a description of class EnemiesEnum here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum EnemyTypes  
{
    BEE (new GreenfootImage("images/bee.png"), 200, 25);
    
    public final GreenfootImage image;
    public final int reward;
    public final int baseHealth;
    
    EnemyTypes(GreenfootImage image, int baseHealth, int reward)
    {
        this.image = image;
        this.baseHealth = baseHealth;
        this.reward = reward;
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
