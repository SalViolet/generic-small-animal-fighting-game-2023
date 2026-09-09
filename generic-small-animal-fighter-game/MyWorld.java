import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    // who's turn is it
    boolean turn = false;
    // is the game still going?
    boolean canPlay = true;
    //the background music
    GreenfootSound song = new GreenfootSound("GAME_MUSIC.mp3");
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        // add Hero
        Hero player = new Hero();
        addObject​(player, getWidth()-100,getHeight()-100);
        // add birb
        Birb villan = new Birb();
        addObject​(villan, 50,getHeight()/2);
        // play background music
        song.playLoop();
        prepare();
    }
    // ends fight and adds end screen
    public void endGame(){
        canPlay=false;
        GameOver end = new GameOver ();
        addObject​(end, getWidth()/2,getHeight()/2);
    }
    // tells players if games still going
    public boolean getCanPlay()
    {
        return canPlay;
    }
    
    public void act()
    {
        // adds pannel if players turn else removes it
        if (turn==true){
            Panel playerChoice = new Panel();
            addObject​(playerChoice, getWidth()/2,getHeight()-80);
        }
        else
        {
            removeObjects(getObjects(Panel.class));
        }
    }
    // whos turn is it
    public boolean getTurn()
    {
        return turn;
    }
    // changes the turn
    public void newTurn()
    {
        turn=!turn;
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
