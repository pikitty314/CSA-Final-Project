import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AddTowerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AddTowerButton extends Button
{
    public AddTowerButton(GameplayWorld world, int size, GreenfootImage towerImage)
    {
        super();
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
        
    }
}
