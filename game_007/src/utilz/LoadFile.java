package utilz;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class LoadFile {
    public static final String PLAYER = "Animation_player.png";
    public static final String BG5 = "BG5.jpg";
    public static final String FIREBALL = "FireBalls.png";
    public static final String POTION = "Potions.png";
    public static final String HP_BAR = "HP_bar.png";
    public static final String BG_start = "BG_start.png";
    public static final String BG_end = "BG_end.png";
    public static final String BG6 = "BG6.jpg";
    public static final String BG7 = "BG7.jpg";
    
    public static BufferedImage GetSprites(String filename){
        BufferedImage img = null;
        InputStream is = LoadFile.class.getClassLoader().getResourceAsStream("ress\\" + filename);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
    }