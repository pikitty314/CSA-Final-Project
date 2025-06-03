import greenfoot.*;

/**
 * Write a description of class TowerTypes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum TowerTypes  
{
    LIGHTHOUSE(new GreenfootImage("images/lighthouse.png"), 3, 1, 100);
    
    private final GreenfootImage image;
    private final int rangeInTiles;
    private final int damage;
    
    private final int price;
    
    TowerTypes(GreenfootImage image, int rangeInTiles, int damage, int price)
    {
        this.image = image;
        this.rangeInTiles = rangeInTiles;
        this.damage = damage;
        
        this.price = price;
    }
    
    public GreenfootImage getImage()
    {
        return image;
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
