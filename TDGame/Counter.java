import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.IntSupplier;

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    String text;
    IntSupplier val;
    int size;
    
    public Counter(String text, IntSupplier val, int size)
    {
        super();
        this.text = text;
        this.val = val;
        this.size = size;
    }
    
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.setImage(new GreenfootImage(text + "" + val.getAsInt(), size, Color.BLACK, Color.WHITE));
    }
}
