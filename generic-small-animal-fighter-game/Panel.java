import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class interfase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Panel extends Actor
{
    // initalise images
    GreenfootImage open3 = new GreenfootImage("interfase100%.png");
    GreenfootImage open1st2 = new GreenfootImage("interfase50%.png");
    GreenfootImage openEdge = new GreenfootImage("interfase80%.png");
    GreenfootImage open1 = new GreenfootImage("interfase0%.png");
    GreenfootImage wait = new GreenfootImage("wait.png");
    /**
     * Act - do whatever the interfase wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Panel()
    {
        // need to size down all the images
        open3.scale(open3.getWidth()/2, open3.getHeight()/2);
        open1st2.scale(open1st2.getWidth()/2, open1st2.getHeight()/2);
        openEdge.scale(openEdge.getWidth()/2, openEdge.getHeight()/2);
        open1.scale(open1.getWidth()/2, open1.getHeight()/2);
        wait.scale(wait.getWidth()/2, wait.getHeight()/2);
    }
    //runs through scenarios with diffrent refresh times to see wich ones right
    public void act()
    {
        Hero actor = (Hero) getWorld().getObjects(Hero.class).get(0);
        if (actor.turnOver ==true)
        {
            setImage(wait);
        }
        else if (actor.potionRefesh<=0&& actor.callRefesh>=0)
        {
            setImage(open1st2);
        }
        else if (actor.potionRefesh>=0&& actor.callRefesh<=0)
        {
            setImage(openEdge);
        }
        else if (actor.potionRefesh>=0&& actor.callRefesh>=0)
        {
            setImage(open1);
        }
        else if (actor.potionRefesh<=0&& actor.callRefesh<=0)
        {
            setImage(open3);
        }
        else
        {
            setImage(open1);
        }
    }
    // this makes sure I can get the worlds variables
    public MyWorld getWorld()
    {
        return (MyWorld)(super.getWorld());
    }
}
