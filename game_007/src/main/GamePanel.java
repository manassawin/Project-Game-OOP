package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import java.awt.image.BufferedImage;
import utilz.LoadFile;


public class GamePanel extends JPanel{
        
        private Game game;
        private BufferedImage bg,bg_start,bg_end,bg_2,bg_3;
        
	public GamePanel(Game game){
            bg_start = LoadFile.GetSprites(LoadFile.BG_start);
            bg = LoadFile.GetSprites(LoadFile.BG5);
            bg_end = LoadFile.GetSprites(LoadFile.BG_end);
            bg_2 = LoadFile.GetSprites(LoadFile.BG6);
            bg_3 = LoadFile.GetSprites(LoadFile.BG7);
            
            this.game = game;
            setPanelSize();
            addKeyListener(new KeyboardInputs(this));
	}

	private void setPanelSize() {
            Dimension size = new Dimension(2000, 1063);
            setPreferredSize(size);
	}
        
        @Override
	public void paintComponent(Graphics g) {
            if(game.playings == 1 && game.status == 0){
               super.paintComponent(g);
            g.drawImage(bg, 0, 0,2500,1133,null);
            game.render(g);
            }else if(game.playings == 2){
               super.paintComponent(g);
            g.drawImage(bg_end, 0, 0,2500,1133,null);
            game.render(g);
            }else{
                super.paintComponent(g);
            g.drawImage(bg_start, 0, 0,2500,1133,null);
            game.render(g);
            }
            if(game.status == 1){
                super.paintComponent(g);
            g.drawImage(bg_2, 0, 0,2500,1133,null);
            game.render(g);
            }else if(game.playings == 2){
               super.paintComponent(g);
            g.drawImage(bg_end, 0, 0,2500,1133,null);
            game.render(g);
            }
            if(game.status == 2){
                super.paintComponent(g);
            g.drawImage(bg_3, 0, 0,2500,1133,null);
            game.render(g);
            }else if(game.playings == 2){
               super.paintComponent(g);
            g.drawImage(bg_end, 0, 0,2500,1133,null);
            game.render(g);
            }
	}
        
        public Game getGame(){
            return game;
        }
}
 