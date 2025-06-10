import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    /** Makes a label with the text of size */
    public Label(String text, int size)
    {
        this.setImage(new GreenfootImage(text, size, Color.BLACK, new Color(255, 230, 193, 255)));
    }
}
