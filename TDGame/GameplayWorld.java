import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class GameplayWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameplayWorld extends BaseWorld
{
    Grid grid;
    InputMenu menu;
    
    int money = 100;
    
    /**
     * Constructor for objects of class GameplayWorld.
     * 
     */
    public GameplayWorld()
    {
        super(12, 9);
        grid = new Grid(super.getGridXSize(), super.getGridYSize());
        GreenfootImage[][] images = new GreenfootImage[super.getGridYSize()][super.getGridXSize()];
        boolean[][] block = new boolean[super.getGridYSize()][super.getGridXSize()];
        for (int i = 0; i < images.length; i++)
        {
            for (int j = 0; j < images[i].length; j++)
            {
                images[i][j] = new GreenfootImage("images/blue-box.png");
                block[i][j] = false;
            }
        }
        Tile[][] tileGrid = grid.generateGrid(images, block,super.getTileSideLength());
        for (Tile[] row : tileGrid)
        {
            for(Tile tile : row)
            {
                addObject(tile,tile.getPixelPose().getX(),tile.getPixelPose().getY());
            }
        }
        
        ArrayList<Point> path = new ArrayList<Point>();
        
        menu = new InputMenu(this, () -> money);
        addObject(menu, (int)(super.getWindowWidth() * 0.9), super.getWindowHeight() / 2);
        
        /*
        for (int i = 0; i < 8; i++)
        {
            path.add(new Point(i,i));
        }
        for (int j = 8; j < 12; j++)
        {
            path.add(new Point(j,7));
        }
        */ 
        path = AStarPathfinder.pathfinder(grid.getPathfinderGrid(), new Point(0,0), new Point(11,8));
    
        addObject(new Enemy(path,grid, 100), 10, 10);
        addObject(new Enemy(path,grid, 100), 20, 20);
        addObject(new Enemy(path,grid, 100), 5, 5);
        addObject(new Enemy(path,grid, 100), 0, 0);
        
        addObject(new Tower(new GreenfootImage("images/lighthouse.png"), grid.getPoint(5,5), 3 * super.getTileSideLength()), grid.getPoint(5,5).getPixelPoint().getX(), grid.getPoint(5,5).getPixelPoint().getY());
    }
    
    public void act()
    {
        
    }
}
