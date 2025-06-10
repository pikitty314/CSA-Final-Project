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
    private GameplayWorld world;
    
    private TowerTypes type;
    
    private boolean play = true;
    
    private GreenfootImage defaultImage;
    private GreenfootImage animatedImage;
    private boolean animatedPlaying;
    
    private GridPoint position;
    
    private int range;
    private int damage;
    private int cooldownTimer;
    
    private int upgradePrice;
    private int saleValue;
    
    /** Constructs a tower in world of type at position */
    public Tower(GameplayWorld world, TowerTypes type, GridPoint position)
    {
        super();
        
        this.world = world;
        
        this.type = type;
        this.saleValue = type.price() / 2;
        this.upgradePrice = type.price() * 2;
        
        this.position = position;
        
        this.range = type.getRangeInTiles() * world.getTileSideLength();
        this.damage = type.damage();
        
        this.defaultImage = new GreenfootImage(type.getImage());
        this.animatedImage = new GreenfootImage(type.getAnimatedImage());
        int imageScale = world.getTileSideLength() / 2;
        defaultImage.scale(imageScale, imageScale);
        animatedImage.scale(imageScale, imageScale);
        
        this.setImage(defaultImage);
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
        if(Greenfoot.mouseClicked(this))
        {
            uponPress();
        }
    }
    
    public void runAttackSystem()
    {
        // see if attack can be made
        if (!(cooldownTimer <= 0))
        {
            cooldownTimer--;
            return;
        }
        else
        {
            cooldownTimer = type.getCooldown();
        }
        
        List<Enemy> enemies = this.getObjectsInRange(range, Enemy.class);
        Enemy target = null;
        
        // Find the enemy closest to the end
        for (Enemy enemy : enemies)
        {
            if (target == null || target.getRemainingPathLength() >= enemy.getRemainingPathLength())
            {
                target = enemy;
            }
        }
        
        // if no target selected, face to the right
        if (target == null)
        {
            setRotation(0);
            animatedPlaying = false;
            setImage(defaultImage);
            return;
        }
        // otherwise turn towards the target and shoot a projectile
        else
        {
            turnTowards(target.getX(), target.getY());
            
            if (!animatedPlaying)
            {
                setImage(animatedImage);
                animatedPlaying = true;
            }
            
            world.addObject(new Projectile(target, type.getProjectileImage(), this.getImage().getHeight() / 2, damage), this.getX(), this.getY());
        }        
    }
    
    /** Allow selection via the menu */
    public void uponPress()
    {
        world.setSelectedGridPoint(position);
    }
    
    public void sellTower()
    {
        world.addMoney(saleValue);
        position.removeTower();
        world.removeObject(this);
    }
    
    /** If possible, upgrade the tower */
    public void upgradeTower()
    {
        // Check if enough money
        if (world.getMoney() >= upgradePrice)
        {
            // Increase stats
            world.spendMoney(upgradePrice);
            damage *= 2;
            range++;
            saleValue += upgradePrice / 2;
            upgradePrice *= 2;
            
            // Get larger
            if (getImage().getHeight() < world.getTileSideLength())
            {
                int newSideLength = getImage().getHeight() + (((world.getTileSideLength()) - (getImage().getHeight())) / 3);
                defaultImage.scale(newSideLength, newSideLength);
                animatedImage.scale(newSideLength, newSideLength);
            }
        }       
    }
    
    public int getSaleValue()
    {
        return saleValue;
    }
    
    public int getUpgradePrice()
    {
        return upgradePrice;
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
