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
    
    private int upgradePrice;
    
    
    public Tower(GameplayWorld world, TowerTypes type, GridPoint position)
    {
        super();
        
        this.world = world;
        
        this.type = type;
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
        List<Enemy> enemies = this.getObjectsInRange(range, Enemy.class);
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
            animatedPlaying = false;
            setImage(defaultImage);
            return;
        }
        else
        {
            turnTowards(target.getX(), target.getY());
            
            if (!animatedPlaying)
            {
                setImage(animatedImage);
                animatedPlaying = true;
            }
            
            target.doDamage(damage);
        }        
    }
    
    public void uponPress()
    {
        world.setSelectedGridPoint(position);
    }
    
    public void sellTower()
    {
        world.addMoney(type.price() / 2);
        position.removeTower();
        world.removeObject(this);
    }
    
    public void upgradeTower()
    {
        if (world.getMoney() >= upgradePrice)
        {
            world.spendMoney(upgradePrice);
            damage *= 2;
            range++;
            upgradePrice *= 2;
            
            // System.out.println("UPGRADE COMPLETE");
            
            if (getImage().getHeight() < world.getTileSideLength())
            {
                int newSideLength = getImage().getHeight() + (((world.getTileSideLength()) - (getImage().getHeight())) / 3);
                defaultImage.scale(newSideLength, newSideLength);
                animatedImage.scale(newSideLength, newSideLength);
            }
        }       
    }
}
