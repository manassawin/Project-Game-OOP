package main;

import entities.FireBall;
import entities.Player;
import entities.Potion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


public class Game {

	public GameWindow gameWindow;
	private GamePanel gamePanel;
	public Thread gameThread;
	public final int FPS_SET = 120;
    public final int UPS_SET = 200;
        private Player player;
        private ArrayList<FireBall> fire = new ArrayList<FireBall>();
        private Potion potion;
        private int potion_Health = 10;
        public int score = 0;
        private int time =0;
        private int High_score;
        public int check = 20;
        public int dmg = 3;
        private boolean playing = false;
        public int playings = 0;
        public Font font;
        public int status=0;
        
        
        
	public Game() {
            createObject();
            gamePanel = new GamePanel(this);
            gameWindow = new GameWindow(gamePanel);
            gamePanel.requestFocus();
            
            
               Thread  player_thread = new Thread(new Runnable() {
                public void run() {

		while (true) {
              
                      try {
                    
                           player.update();
  
                            potion.update();    
                            checkPlayerGetDmg();
                            checkPlayerGetpotion();
                            checkDead();
                            check_score();
                             gamePanel.repaint();
                            Thread.sleep(3);
                            
                        } catch (Exception e) {
                        }
		}

	}
        });
                    
               Thread fire_ball_Thread1 = new Thread(new Runnable() {
                public void run() {

		while (true) {
              
                      try {
        
     
                            potion.update();    
          
                              fire.get(0).update();
                              fire.get(1).update();
                             fire.get(2).update();
                             gamePanel.repaint();
                            Thread.sleep(3);
                    
                            
                        } catch (Exception e) {
                        }
		}

	}
        });
                fire_ball_Thread1.start();
                player_thread.start();
         
	}
        
     
        private void createObject() {
              player = new Player(100,500,150,150);
              fire.add(new FireBall(80,80));
              fire.add(new FireBall(80,80));
              fire.add(new FireBall(80,80));
              potion = new Potion(50,50);
        }

	
        
   
     
        public void render(Graphics g){            
            if(playings == 1){
                player.render(g);
                fire.get(0).render(g);
                fire.get(1).render(g);
                fire.get(2).render(g);
                potion.render(g);
         
                g.setColor(new Color(255, 178, 102));
                g.drawString("Score : " + score, 800, 34);
            }
            else if(playings == 2){
                g.setFont(new Font("Blackadder ITC",Font.PLAIN,120));
                g.setColor(new Color(255, 178, 102));
                g.drawString("Score : " + High_score,1000,650);
                player.setHP(50);
                score = 0;
                check = 20;
                dmg = 3;
                fire.get(0).setFire_speed_default(13);
                fire.get(1).setFire_speed_default(13);
                fire.get(2).setFire_speed_default(13);
            }
        }


        public boolean isPlaying() {
            return playing;
        }

        public void setPlaying(boolean playing) {
            this.playing = playing;
        }

        public int getPlayings() {
            return playings;
        }

        public void setPlayings(int playings) {
            this.playings = playings;
        }
        
        public void checkDead(){
            if(player.getHP()<=0){
                playings = 2;
                High_score = score;
                status =0;
            }
        }
        
        public Player getplayer(){
            return player;
        }
        
        public void windowFocusLost(){
            player.resetDirBooleans();
        }
      
        public void checkPlayerGetDmg(){
            if(player.getPlayerArea().intersects(fire.get(0).getCoinArea()) && fire.get(0).isCanDmg() == true){
                player.gotDMG(dmg);
                fire.get(0).setCanDmg(false);
            }else if(player.getPlayerArea().intersects(fire.get(1).getCoinArea()) && fire.get(1).isCanDmg() == true){
                player.gotDMG(dmg);
                fire.get(1).setCanDmg(false);
            }else if(player.getPlayerArea().intersects(fire.get(1).getCoinArea()) && fire.get(1).isCanDmg() == true){
                player.gotDMG(dmg);
                fire.get(2).setCanDmg(false);
            }
        }
        public void checkPlayerGetpotion(){
            if(player.getPlayerArea().intersects(potion.getPotionArea()) && potion.isDelay() == false){
                potion.setDelay(true);
                player.gotPotion(potion_Health,potion.potion);
            }
        }
        public void check_score(){
             time++;
            if(time>5*40)
            {
                score++;
                time =0;
                if(score >= 40){
                    status =1;
                }
                if(score >= 100){
                    status =2;
                }
            }
      
        }

}