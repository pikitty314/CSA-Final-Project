import greenfoot.*;

/**
 * Write a description of class TowerTypes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum TowerTypes  
{
    STINGER (new GreenfootImage("images/towers/stinger-retracted.png"), new GreenfootImage("images/towers/stinger-extended.png"), new GreenfootImage("projectiles/stinger.png"), 1, 50, 10, 100),
    POLLEN_TURRET(new GreenfootImage("images/towers/pollen-turret.png"), new GreenfootImage("projectiles/pollen.png"), 3, 75, 25, 150),
    STAR_SLINGER(new GreenfootImage("images/towers/star-slinger.png"), new GreenfootImage("projectiles/throwing-star.png"), 5, 250, 200, 200),
    TASER(new GreenfootImage("images/towers/taser.png"), new GreenfootImage("projectiles/taser-bolt.png"), 12, 100, 100, 250);
    
    private final GreenfootImage image;
    private final GreenfootImage animatedImage;
    private final GreenfootImage projectile;
    private final int rangeInTiles;
    private final int damage;
    private final int cooldown;
    private final int price;
    
    TowerTypes(GreenfootImage image, GreenfootImage projectile, int rangeInTiles, int damage, int cooldown, int price)
    {
        this.image = image;
        this.animatedImage = image;
        this.projectile = projectile;
        this.rangeInTiles = rangeInTiles;
        this.damage = damage;
        this.cooldown = cooldown;
        this.price = price;
    }
    
    TowerTypes(GreenfootImage image, GreenfootImage animatedImage, GreenfootImage projectile, int rangeInTiles, int damage, int cooldown, int price)
    {
        this.image = image;
        this.animatedImage = animatedImage;
        this.projectile = projectile;
        this.rangeInTiles = rangeInTiles;
        this.damage = damage;
        this.cooldown = cooldown;
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
    
    public GreenfootImage getProjectileImage()
    {
        return projectile;
    }
    
    public int getRangeInTiles()
    {
        return rangeInTiles;
    }
    
    public int damage()
    {
        return damage;
    }
    
    public int getCooldown()
    {
        return cooldown;
    }
    
    public int price()
    {
        return price;
    }
}
