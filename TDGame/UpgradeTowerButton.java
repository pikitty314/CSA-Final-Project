import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradeTowerButter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeTowerButton extends Button
{
    GameplayWorld world;
    Tower tower;
    
    public UpgradeTowerButton(GameplayWorld world, int size, Tower tower)
    {
        super();
        
        this.world = world;
        
        this.tower = tower;
        
        GreenfootImage picture = new GreenfootImage("images/button-blue.png");
        picture.scale(size, size);
        this.setImage(picture);
    }
    
    @Override
    public void uponPress()
    {
        tower.upgradeTower();
        world.getMenu().hideTowerMenu();
    }
}
