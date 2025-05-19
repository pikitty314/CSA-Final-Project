import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private boolean play = true;
    
    private ArrayList<Point> path;
    private Grid grid;
    private GridPoint currentPoint;
    
    public Enemy(ArrayList<Point> path, Grid grid)
    {
        this.path = new ArrayList<Point>(path);
        this.grid = grid;

        nextPathPoint();
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (play)
        {
            followPath(1);
        }
    }
    
    public void followPath(int speed)
    {
        if (this.getX() == currentPoint.getPixelPoint().getX()
            && this.getY() == currentPoint.getPixelPoint().getY())
        {
            nextPathPoint();
        }
        
        this.turnTowards(currentPoint.getPixelPoint().getX(), currentPoint.getPixelPoint().getY());
        this.move(speed);
    }
    
    public void nextPathPoint()
    {
        currentPoint = grid.getPoint(path.get(0).getX(),path.get(0).getY());
        path.remove(0);
    }
}
