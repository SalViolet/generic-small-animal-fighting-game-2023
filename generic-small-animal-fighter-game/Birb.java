import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EvilMan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Birb extends Actor
{
    //HP
    public int BirbHP = 50;
    // how many turns till lightning dance is avalible
    private int spinRefresh = 2;
    // how many turns till scream is avalible
    private int screamRefesh = 6;
    // IMAGES FOR BIRB
    GreenfootImage Idle1 = new GreenfootImage("file.png");
    GreenfootImage Idle2 = new GreenfootImage("file2.png");
    GreenfootImage peck = new GreenfootImage("Peck.png");
    GreenfootImage hurt = new GreenfootImage("birbPain.png");
    // needs to get to 14 to go to next idle frame
    private int animate = 0;
    // counts down after inital attack so animation can finish
    private int newTurntimer = 100;
    // what idle number to do
    private boolean idle = true;
    // is it idle time or atack time
    private boolean attack = false;
    // attack type to do correct animation
    private String attackType="null";
    //SOUNDS FOR ATTACKS
    GreenfootSound scream = new GreenfootSound("SCREAMING.mp3");
    GreenfootSound bite = new GreenfootSound("BITE.mp3");
    GreenfootSound spin = new GreenfootSound("power.mp3");
    /**
     * Act - do whatever the EvilMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Birb()
    {
        // image too small so I must size up
        Idle1.scale(Idle1.getWidth()*2, Idle1.getHeight()*2);
        Idle2.scale(Idle2.getWidth()*2, Idle2.getHeight()*2);
        peck.scale(peck.getWidth()*2, peck.getHeight()*2);
        hurt.scale(hurt.getWidth()*2, hurt.getHeight()*2);
    }
    public void act()
    {   
        // shows health (why didnt we do this in flappy bird it is so easy)
        getWorld().showText(""+BirbHP, 50, getWorld().getHeight()/2-50);
        animate++;
        if (BirbHP<=0)
        {
            // ends game if health 0
            getWorld().endGame();
        }
        if (getWorld().getCanPlay()==true){
            // IDLE ANIMATION
            if (animate >= 14)
            {
                idle = !idle;
                animate=0;
                if (getWorld().getTurn()==true){
                    if (idle == true){
                        setImage(Idle1);
                    }
                    else{
                        setImage(Idle2);
                    }
                }
                else
                {
                    attack();
    
                }
            }
            if (getWorld().getTurn()==false)
            {
                newTurntimer--;
                if (newTurntimer<=0)
                {
                    // NEW TURN
                    getWorld().newTurn();
                    newTurntimer=100;
                    attack = false;
                    turn(360-getRotation());
                    attackType="null";
                    screamRefesh--;
                    spinRefresh--;
                }
            }
        }
    }
    // DAMAGE:1-8 REFRESH IN:2 turns IMAGE: peck SOUND: bite
    public int Peck()
    {
        setImage(peck);
        bite.play();
        animate=0;
        attack = true;
        return Greenfoot.getRandomNumber(4)+1;

    }
    // DAMAGE:7-10  NO REFRESH IMAGE: spining SOUND: spin
    public int lighningDance()
    {
        animate=0;
        turn(50); 
        spin.play();
        return Greenfoot.getRandomNumber(7)+3;
    }
    // DAMAGE:8-13  REFRESH IN: 6 IMAGE: none SOUND: SCREAMING
    public int IncoherentScreaming()
    {
        scream.play();
        return Greenfoot.getRandomNumber(8)+5;
    }
    // this makes sure I can get the worlds variables
    public MyWorld getWorld()
    {
        return (MyWorld)(super.getWorld());
    }
    // hurt animation
    public void showDamage()
    {
        setImage(hurt);
        animate=0;
    }
    // algaritom to tell what attack to do and holds animation till turn end
    public void attack()
    {
        int turnDamage = 0;
        Hero actor = (Hero) getWorld().getObjects(Hero.class).get(0);
        if (attackType.equals("null")){
            if (screamRefesh <= 0)
            {
                turnDamage = IncoherentScreaming();
                attackType="scream";
                screamRefesh=6;
                actor.showHeroDamage();
            }
            else if (spinRefresh <= 0)
            {
                int attack = Greenfoot.getRandomNumber(2);
                if (attack == 1)
                {
                    turnDamage = lighningDance();
                    attackType="spin";
                    spinRefresh=2;
                    actor.showHeroDamage();
                }
                else
                {
                    turnDamage = Peck();
                    attackType="peck";
                    actor.showHeroDamage();
                }
            }
            else
            {
                turnDamage = Peck();
                attackType="peck";
                actor.showHeroDamage();
            }
        }
        else if (attackType.equals("spin"))
                lighningDance();
        else if (attackType.equals("peck"))
            Peck();
        else if (attackType.equals("scream"))
            IncoherentScreaming();
        actor.HeroHP -= turnDamage;
    }
}
