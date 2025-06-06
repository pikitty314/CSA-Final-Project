import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeleteTowerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SellTowerButton extends Button
{
    GameplayWorld world;
    Tower tower;
    
    public SellTowerButton(GameplayWorld world, int size, Tower tower)
    {
        super();
        
        this.world = world;
        
        this.tower = tower;
        
        GreenfootImage picture = new GreenfootImage("images/sell-button.png");
        picture.scale(size, size);
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        tower.sellTower();
        world.getMenu().hideTowerMenu();
    }
}
