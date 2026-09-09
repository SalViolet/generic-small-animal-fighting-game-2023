import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class heroIGuess here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    //IMAGES
    GreenfootImage norm = new GreenfootImage("ppl2.png");
    GreenfootImage dmg = new GreenfootImage("dmg.png");
    //HP
    public int HeroHP = 50;
    // turns till attacks avalible
    public int potionRefesh = 2;
    public int callRefesh = 6;
    // needs to hit 15 to set off reaction
    private int heroAnimate = 0;
    // wait for new turn so you can take a breath
    private int newTurnTimer = 100;
    // has palyer chosen move yet?
    public boolean turnOver = false;
    //SOUNDS
    GreenfootSound slash = new GreenfootSound("SWORDSOUND.mp3");
    GreenfootSound heal = new GreenfootSound("HP.mp3");
    GreenfootSound call = new GreenfootSound("CALL.mp3");
    /**
     * Act - do whatever the heroIGuess wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Hero()
    {
        //resize images+ sounds
        norm.scale(norm.getWidth()*5, norm.getHeight()*5);
        dmg.scale(dmg.getWidth()*5, dmg.getHeight()*5);
        slash.setVolume​(100);
        setImage(norm);
    }
    public void act()
    {
        heroAnimate++;
        if (HeroHP<=0)
        {
            //ends game if hero HP 0
            getWorld().endGame();
        }
        if (heroAnimate >= 15)
        {
            // sets image back to norm (for dmg)
            setImage(norm);
            heroAnimate=0;
        }
        getWorld().showText(""+HeroHP,getWorld().getWidth()-120, getWorld().getHeight()/2-50);
        if (getWorld().getCanPlay()==true){
            if (getWorld().getTurn()==true)
            {
                // has palyer clicked yet?
                if (turnOver == false)
                {
                    // checks keys for attacks
                    if (Greenfoot.isKeyDown("up")||Greenfoot.isKeyDown("w"))
                        attack(sword());
                    else if (Greenfoot.isKeyDown("down")||Greenfoot.isKeyDown("s"))
                    {
                        if (potionRefesh<=0)
                            heal(potion());
                    }
                    else if (Greenfoot.isKeyDown("left")||(Greenfoot.isKeyDown("right")||Greenfoot.isKeyDown("a")||(Greenfoot.isKeyDown("d")))){
                        if (callRefesh<=0)
                        attack(callPeta());
                    }
                }
                else{
                    newTurnTimer--;
                }
                //new turn
                if (newTurnTimer == 0){
                    getWorld().newTurn();
                    newTurnTimer=100;
                    turnOver = false;
                }
            }
        }
        //else {
            //if 
    }
    // this makes sure I can get the worlds variables
    public MyWorld getWorld()
    {
        return (MyWorld)(super.getWorld());
    }
    // DAMAGE:15-25 REFRESH IN:9 SOUND: call
    public int callPeta()
    {
        callRefesh = 6;
        call.play();
        return Greenfoot.getRandomNumber(15)+10;
    }
    // HEAL:3-6 REFRESH IN:2 SOUND:heal
    public int potion()
    {
        heal.play();
        potionRefesh =2;
        return Greenfoot.getRandomNumber(3)+3;
    }
    // hurt animation for hero
    public void showHeroDamage()
    {
        setImage(dmg);
        heroAnimate=0;
    }
    // DAMAGE:5+1  NO REFRESH SOUND: slash
    public int sword()
    {
        slash.play();
        return Greenfoot.getRandomNumber(5)+1;
    }
    // this does damage to birb and ends turn
    public void attack(int damage)
    {
        Birb enemy = (Birb) getWorld().getObjects(Birb.class).get(0);
        enemy.BirbHP -= damage;
        enemy.showDamage();
        potionRefesh--;
        callRefesh--;
        turnOver = true;
    }
    // like attack but for healing
    public void heal(int damage)
    {
        HeroHP += damage;
        potionRefesh--;
        callRefesh--;
        turnOver = true;
    }
}
