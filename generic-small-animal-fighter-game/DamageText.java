import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DamageText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DamageText extends Actor
{
    /**
     * Act - do whatever the DamageText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        GreenfootImage newImage = getImage();
        setImage(newImage);
    }
    public DamageText(int score,int x, int y)
    {
        //show scores above player head
        GreenfootImage newImage = new GreenfootImage(40,30);
        newImage.drawString(""+score,x,y);
        newImage.setColor(greenfoot.Color.RED);
        setImage(newImage);

    }

}
