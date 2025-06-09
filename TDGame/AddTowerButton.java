import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AddTowerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AddTowerButton extends Button
{
    GameplayWorld world;
    TowerTypes type;
    
    public AddTowerButton(GameplayWorld world, int size, TowerTypes type)
    {
        super();
        
        this.world = world;
        
        this.type = type;
        
        this.setImage(type.getImage());
        this.getImage().scale(size, size);
    }
    
    public AddTowerButton(GameplayWorld world, int size, GreenfootImage towerImage)
    {
        super();
        
        this.world = world;
        
        this.type = TowerTypes.TASER;
        
        this.setImage(towerImage);
        this.getImage().scale(size, size);
    }
    
    public AddTowerButton(int size)
    {
        super();
        this.getImage().scale(size,size);
    }
    
    @Override
    public void uponPress()
    {
        world.addTower(type);
    }
}
