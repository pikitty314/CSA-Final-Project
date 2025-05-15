/**
 * Write a description of class GridPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GridPoint  
{
    private Tile tile;
    private Point tilePoint; // Location on the 10x10 grid of tiles
    private Point pixelPoint; // Location on the grid of pixels
    private int paddingPixels; // Number of pixels on the left, top, and bottom of the tile
    private Tower tower;
    
    public GridPoint()
    {
        tile = new Tile();
        tilePoint = new Point(0,0);
        pixelPoint = new Point(0,0);
        paddingPixels = 0;
        tower = new Tower();
    }
}
