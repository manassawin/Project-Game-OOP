/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import utilz.LoadFile;

/**
 *
 * @author koyrot
 */
public class Potion extends Entity{
    int set_x =500;
    private boolean delay = true;
    public int counter = 0;
    private Random random;
    public int potion;

    public Potion(int sX,int sY) {
        super(0, 0, sX, sY);
        loadAnimations();
    }
    
    @Override
    public void loadAnimations() {
        BufferedImage img = LoadFile.GetSprites(LoadFile.POTION);

        animations = new BufferedImage[6][6];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*33)+50, (j*33)+8, 32, 32);
    }

    @Override
    public void update() {
        if(delay == true){
            Delay();
        }
        else if(delay == false){
            updateAnimationTick();
            PotionMiss();
        }
    }

    @Override
    public void render(Graphics g) {
        if(delay == false){
            g.drawImage(animations[potion][aniIndex],(int)x,(int)y,scaleX,scaleY, null);
//            g.drawRect((int)x,(int)y,scaleX,scaleY);
        }
    }

    @Override
    public void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= 6){
                aniIndex = 0;
            }
        }
    }
    
    public void Delay(){
        counter++;
        if(counter > 1500){
            delay = false;
            counter = 0;
            random = new Random();
            potion = random.nextInt(2);
            x =  random.nextInt(set_x);
            y =  random.nextInt((1063 - 300) + 1) + 300;
        }
    }
    public void PotionMiss(){
        counter++;
        if(counter > 4500){
            delay = true;
            counter = 0;
        }
    }
    
    public Rectangle getPotionArea(){
        return new Rectangle(x,y,scaleX,scaleY);
    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }
}

