import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeWorld extends BaseWorld
{
    GreenfootImage title;
    GreenfootImage howToPage;
    GreenfootImage aboutTowersPage;
    
    StartButton startButton;
    StartEndlessButton startEndlessButton;
    HowToPlayButton howToPlayButton;
    AboutTowersButton aboutTowersButton;
    
    GoToTitleScreenButton goToTitleScreenButton;
    /**
     * Constructor for objects of class Title.
     */
    public WelcomeWorld()
    {    
        super();
        title = new GreenfootImage("images/title-page.png");
        howToPage = new GreenfootImage("images/how-to-play-page.png");
        aboutTowersPage = new GreenfootImage("images/about-towers-page.png");
        
        title.scale(super.getWindowWidth(), super.getWindowHeight());
        howToPage.scale(super.getWindowWidth(), super.getWindowHeight());
        aboutTowersPage.scale(super.getWindowWidth(), super.getWindowHeight());
        
        
        startButton = new StartButton(super.getTileSideLength() * 2);
        startEndlessButton = new StartEndlessButton(super.getTileSideLength() * 2);
        howToPlayButton = new HowToPlayButton(this, (int)(super.getTileSideLength() * 0.75));
        aboutTowersButton = new AboutTowersButton(this, (int)(super.getTileSideLength() * 0.75));
        
        goToTitleScreenButton = new GoToTitleScreenButton(this, (int)(super.getTileSideLength() * 0.75));
        
        drawWelcomePage();
    }
    
    public void drawWelcomePage()
    {
        setBackground(title);
        addObject(startButton, super.getWindowWidth() / 2, (int)(super.getWindowHeight() / 1.7));
        addObject(startEndlessButton, super.getWindowWidth() / 2, (int)(super.getWindowHeight() / 1.2));
        addObject(howToPlayButton, super.getWindowWidth() / 4, (int)(super.getWindowHeight() / 1.4));
        addObject(aboutTowersButton, 3*(super.getWindowWidth() / 4), (int)(super.getWindowHeight() / 1.4));
    }
    
    public void deleteWelcomePage()
    {
        removeObject(startButton);
        removeObject(startEndlessButton);
        removeObject(howToPlayButton);
        removeObject(aboutTowersButton);
    }
    
    public void drawHowToPlayPage()
    {
        setBackground(howToPage);
        addObject(goToTitleScreenButton, (int)(super.getWindowWidth() * 0.9), (int)(super.getWindowHeight() * 0.9));
        deleteWelcomePage();
    }
    
    public void drawAboutTowersPage()
    {
        setBackground(aboutTowersPage);
        addObject(goToTitleScreenButton, (int)(super.getWindowWidth() * 0.9), (int)(super.getWindowHeight() * 0.9));
        deleteWelcomePage();
    }
}
