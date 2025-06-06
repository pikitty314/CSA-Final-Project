import greenfoot.*;

/**
 * Write a description of class GridPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GridPoint  
{
    // Things that appear on screen
    private Tile tile;
    private Tower tower;
    
    // Location on screen
    private Point tilePoint; // Location on the 10x10 grid of tiles
    private Point pixelPoint; // Location on the grid of pixels
    
    // What can be done at this point
    private boolean canPlaceTower;
    private boolean canBeInEnemyPath;
        
    public GridPoint(Tile tile, Point tilePoint, Point pixelPoint, boolean canPlaceTower, boolean canBeInEnemyPath)
    {
        this.tile = tile;
        this.tower = null; // Start with no tower
        
        this.tilePoint = tilePoint;
        this.pixelPoint = pixelPoint;
        
        this.canPlaceTower = canPlaceTower;
        this.canBeInEnemyPath = canBeInEnemyPath;
    }
    
    public GridPoint(Tile tile, int tilePointX, int tilePointY, int pixelPointX, int pixelPointY, boolean canPlaceTower, boolean canBeInEnemyPath)
    {
        this.tile = tile;
        this.tower = null; // Start with no tower
        
        this.tilePoint = new Point(tilePointX, tilePointY);
        this.pixelPoint = new Point(pixelPointX, pixelPointY);
        
        this.canPlaceTower = canPlaceTower;
        this.canBeInEnemyPath = canBeInEnemyPath;
    }
    
    
    // Getters
    public boolean canPlaceTower() 
    {
        return canPlaceTower;
    }
    
    public boolean canBeInEnemyPath()
    {
        return canBeInEnemyPath;
    }
    
    public Tower getTower()
    {
        return tower;
    }
    
    public Point getPixelPoint()
    {
        return pixelPoint;
    }
    
    public Point getTilePoint()
    {
        return tilePoint;
    }
    
    // Setters
    public void setCanPlaceTower(boolean canPlaceTower)
    {
        this.canPlaceTower = canPlaceTower;
    }
    
    public void setCanBeInEnemyPath(boolean canBeInEnemyPath)
    {
        this.canBeInEnemyPath = canBeInEnemyPath;
    }
    
    public void placeTower(Tower tower)
    {
        this.tower = tower;
        setCanBeInEnemyPath(false);
    }
    
    public void removeTower()
    {
        this.tower = null;
        setCanBeInEnemyPath(true);
    }
    
    public void setTileImage(GreenfootImage image)
    {
        this.tile.changeImage(image);
    }
}
