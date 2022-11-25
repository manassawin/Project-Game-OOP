package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow {
	private JFrame jframe ;

	public GameWindow(GamePanel gamePanel) {

		jframe = new JFrame();
                jframe.setTitle("TotheMoon_007");
                jframe.setSize(2000,1063);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jframe.setResizable(false);
                jframe.setVisible(true);
                jframe.setLocationRelativeTo(null);
                jframe.add(gamePanel);
		jframe.pack();
		
                jframe.addWindowFocusListener(new WindowFocusListener() {
                    @Override
                    public void windowGainedFocus(WindowEvent e) {
                    }

                    @Override
                    public void windowLostFocus(WindowEvent e) {
                        gamePanel.getGame().windowFocusLost();
                    }
                });
	}

}
