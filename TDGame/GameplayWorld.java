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
        // path = Pathfinder.pathfinder(grid.getPathfinderGrid(), new Point(0,0), new Point(0,3));
        for (int i = 0; i < 8; i++)
        {
            path.add(new Point(i,i));
        }
        for (int j = 8; j < 12; j++)
        {
            path.add(new Point(j,7));
        }
        Enemy bee = new Enemy(path,grid);
        addObject(bee, 0, 0);
    }
    
    public void act()
    {
        
    }
}
