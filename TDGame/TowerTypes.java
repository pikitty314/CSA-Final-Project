import greenfoot.*;

/**
 * Write a description of class TowerTypes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum TowerTypes  
{
    STINGER (new GreenfootImage("images/towers/stinger-retracted.png"), new GreenfootImage("images/towers/stinger-animated.gif"), 1, 10, 100),
    POLLEN_TURRET(new GreenfootImage("images/towers/pollen-turret.png"), 3, 2, 150),
    STAR_SLINGER(new GreenfootImage("images/towers/star-slinger.png"), 5, 2, 200),
    TASER(new GreenfootImage("images/towers/taser.png"), 12, 3, 250);
    
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
