import greenfoot.*;

/**
 * Write a description of class TowerTypes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum TowerTypes  
{
    LIGHTHOUSE(new GreenfootImage("images/lighthouse.png"), 3, 1, 100),
    STINGER (new GreenfootImage("images/towers/stinger-retracted.png"), new GreenfootImage("images/towers/stinger-animated.gif"), 1, 10, 1);
    
    private final GreenfootImage image;
    private final GreenfootImage animatedImage;
    private final int rangeInTiles;
    private final int damage;
    
    private final int price;
    
    TowerTypes(GreenfootImage image, int rangeInTiles, int damage, int price)
    {
        this.image = image;
        this.animatedImage = image;
        this.rangeInTiles = rangeInTiles;
        this.damage = damage;
        
        this.price = price;
    }
    
    TowerTypes(GreenfootImage image, GreenfootImage animatedImage, int rangeInTiles, int damage, int price)
    {
        this.image = image;
        this.animatedImage = animatedImage;
        this.rangeInTiles = rangeInTiles;
        this.damage = damage;
        
        this.price = price;
    }
    
    public GreenfootImage getImage()
    {
        return image;
    }
    
    public GreenfootImage getAnimatedImage()
    {
        return animatedImage;
    }
    
    public int getRangeInTiles()
    {
        return rangeInTiles;
    }
    
    public int damage()
    {
        return damage;
    }
    
    public int price()
    {
        return price;
    }
}
