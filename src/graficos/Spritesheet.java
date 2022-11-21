package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
    private BufferedImage Sprites; 
	
    public Spritesheet(String path) {
    	try {
			Sprites = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public BufferedImage getSprite(int x, int y, int largura, int altura) {
    	return Sprites.getSubimage(x,y,largura,altura); 
    	
    }
}
