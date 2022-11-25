/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.GetSprirteAmount;
import static utilz.Constants.PlayerConstants.IDLE;
import static utilz.Constants.PlayerConstants.*;
import utilz.LoadFile;

public class Player extends Entity{
    
    public int playerAction = IDLE;
    private boolean left,right,up,down;
    private boolean moving = false;
    private float playerSpeed = 5;
    
    public int temp ;
    private int counter = 0;
    private boolean buff = false;
    private Font font;    
    //Hp
    private BufferedImage hp_bar ;
    private int HP = 50;
    private int Max_HP = 50;
    private int HP_X = 78;
    private int HP_Y = 44;
    private int HP_Width = 192;
    private int HP_Height = 12;
    private int Health = HP_Width;
    
    

    public Player(int x,int y,int sX,int sY) {
        super(x, y, sX,sY);
        loadAnimations();
        
    }
    
    @Override
    public void update(){
        updateHPBar();
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    
    @Override
    public void render(Graphics g){        
        g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y,scaleX,scaleY,null);
        g.setColor(Color.RED);
        
        loadUI(g);
    }
    @Override
    public void updateAnimationTick() {
            aniTick++;
            if(aniTick>=aniSpeed){
                aniTick = 0;
                aniIndex++;
                if(aniIndex>=GetSprirteAmount(playerAction)){
                    aniIndex = 0;
                }
            }
        }
        
    
    @Override
    public void loadAnimations() {
        BufferedImage img = LoadFile.GetSprites(LoadFile.PLAYER);

        animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                        animations[j][i] = img.getSubimage((i*32)+50, (j*32)+8, 32, 32);
        
        hp_bar = LoadFile.GetSprites(LoadFile.HP_BAR);          
    }
    
    private void loadUI(Graphics g){
        font = new Font("TimesRoman",Font.BOLD,20);
        g.setFont(font);
        g.setColor(new Color(255, 178, 102));
        g.drawString("007", 90, 34);
        g.setColor(new Color(233, 29, 52));
        g.fillRect(HP_X, HP_Y, Health, HP_Height);
        g.drawImage(hp_bar, 0, -100, 300, 300, null);      
    }
    
    private void updateHPBar() {
        Health =(int)((HP / (float)Max_HP) * HP_Width);
    }
    
    private void setAnimation() {
            if(!moving){
                playerAction = IDLE;
            }
        }
        
        private void updatePos() {
            moving = false;
                if(left && !right){
                    x-=playerSpeed;
                    playerAction = RUNNING_LEFT;
                    moving = true;
                }else if(right &&!left){
                    x+=playerSpeed;
                    playerAction = RUNNING_RIGHT;
                    moving = true;
                }
                if(up && !down){
                    y-=playerSpeed;
                    moving = true;
                }else if(down &&!up){
                    y+=playerSpeed;
                    moving = true;
                }
                if(right == false){
                x-=(playerSpeed/playerSpeed );
//                    playerAction = RUNNING_LEFT;
                    moving = true;
            }

            
            counter++;
            if(counter > 1000 && buff == true){
                counter = 0;
                buff = false;
                playerSpeed = 5;
            }
            if(x>500){
                x=500;
            }else if(x<0){
                x=0;
            }
            if(y<0){
                y=0;
            }else if(y>900){
                y=900;
            }
        }

    public Rectangle getPlayerArea(){
        return new Rectangle((int)x,(int)y,scaleX,scaleY);
    }
    
    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }
    
    public void gotDMG(int dmg){
        if(buff==false){
            HP-=dmg;
        }
    }
    
    public void gotPotion(int potion_Health,int potion){
        if(potion==0){
            HP+=potion_Health;
            if(HP > 50){
                HP = 50;
            }
        }
        else if(potion==1){
            playerSpeed-=3;
            buff = true;
            counter = 0;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    
    
}
