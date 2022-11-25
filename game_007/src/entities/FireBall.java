/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.LoadFile;
import java.util.Random;

/**
 *
 * @author koyrot
 */
public class FireBall extends Entity{
   
    private Random random;
    private int direction =1;
    private boolean canDmg = true;
    private int fire_speed = 13;
    
    public FireBall(int sX,int sY) {
        super(0, 1063, sX, sY);
        loadAnimations();
    }
    
    

    @Override
    public void loadAnimations() {
        BufferedImage img = LoadFile.GetSprites(LoadFile.FIREBALL);

        animations = new BufferedImage[5][5];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*32)+50, (j*32)+8, 32, 32);
    }

    @Override
    public void update() {
        fireBallMove();
        updateAnimationTick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawImage(animations[direction][aniIndex],(int)x,(int)y,scaleX,scaleY,null);
        g.drawRect((int)x,(int)y,scaleX,scaleY);
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 5){
                aniIndex = 0;
            }
        }
    }
    
    private void fireBallMove(){
        if(direction == 1){
            x-=fire_speed;
            if(x<-100){
                random = new Random();
                x = 2080;
                y =  random.nextInt((1063 ) + 1) ;
                canDmg = true;
                
            }
        }
    }
    
    public Rectangle getCoinArea(){
        return new Rectangle(x,y,scaleX,scaleY);
    }
    
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCanDmg() {
        return canDmg;
    }

    public void setCanDmg(boolean canDmg) {
        this.canDmg = canDmg;
    }

    public int getFire_speed() {
        return fire_speed;
    }

    public void setFire_speed(int fire_speed) {
        this.fire_speed += fire_speed;
    }
    public void setFire_speed_default(int fire_speed) {
        this.fire_speed = fire_speed;
    }
    
}
