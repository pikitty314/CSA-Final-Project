import greenfoot.*;
/**
 * Contains all constant and global variables.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Globals  
{
    public static class WindowConstants
    {
        public static final int windowWidth = 1280;
        public static final int windowHeight = 720;
        public static final int windowScale = 1;
    }
    
    public static class WorldConstants
    {
        public static final int gridXSize = 12;
        public static final int gridYSize = 9;
        
        public static int gridTileSizePixels = 0;        
        static {
            if((Globals.WindowConstants.windowWidth / (double)gridXSize) > (Globals.WindowConstants.windowHeight / (double)gridYSize))
            {
                gridTileSizePixels = Globals.WindowConstants.windowHeight / (gridYSize + 1);
            }
            else
            {
                gridTileSizePixels = Globals.WindowConstants.windowWidth / (gridXSize + 1);
            }
        }
        
    }
}
