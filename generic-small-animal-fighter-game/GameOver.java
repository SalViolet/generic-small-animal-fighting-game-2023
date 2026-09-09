import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DamageText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    //IMAGES
    GreenfootImage win =  new GreenfootImage("win.png");
    GreenfootImage loose =  new GreenfootImage("loose.jpeg");
    GreenfootImage error =  new GreenfootImage("error.jpeg");
    /**
     * Act - do whatever the DamageText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // adds corect ending screen
        Hero actor = (Hero) getWorld().getObjects(Hero.class).get(0);
        Birb enemy = (Birb) getWorld().getObjects(Birb.class).get(0);
        if (actor.HeroHP <= 0)
            setImage(loose);
        else if (enemy.BirbHP <= 0)
            setImage(win);
        else
            setImage(error);
    }
    public GameOver()
    {
        
    }

}
