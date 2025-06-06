import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private GameplayWorld world;
    private Point pixelPose;
    private Point gridPoint;
    
    public Tile(GameplayWorld world, GreenfootImage image, Point pixelPosition, Point gridPoint)
    {
        super();
        this.world = world;
        super.setImage(image);
        this.pixelPose = pixelPosition;
        this.gridPoint = gridPoint;
    }
    
    public Tile(GameplayWorld world, GreenfootImage image, int pixelPoseX, int pixelPoseY, int gridX, int gridY)
    {
        super();
        this.world = world;
        super.setImage(image);
        this.pixelPose = new Point(pixelPoseX, pixelPoseY);
        this.gridPoint = new Point(gridX, gridY);
    }
    
    public Point getPixelPoint()
    {
        return pixelPose;
    }
    
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            uponPress();
        }
    }
    
    public void uponPress()
    {
        world.setSelectedGridPoint(world.getGrid().getPoint(gridPoint));
    }
    
    /* For some obscure reason,
     * it seems like super.setImage() would call the method below
     * if it were called setImage() */
    public void changeImage(GreenfootImage image)
    {
        image.scale(world.getTileSideLength(), world.getTileSideLength());
        System.out.println(this.world);
        super.setImage(image);
    }
}
