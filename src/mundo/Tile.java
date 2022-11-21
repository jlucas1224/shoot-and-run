package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;

public class Tile {

	public static BufferedImage TILE_FLOOR = Joguinho.sprites.getSprite(0,0,16,16);
	
	
    
	
	private BufferedImage sprite;
	private int x,y;
	private int maskx, masky, mlargura, maltura;
	protected int largura;
	protected int altura;
	
	public Tile(int x, int y, BufferedImage sprite, int largura, int altura) {
		this.maskx = maskx;
  	    this.masky = masky;
  	    this.largura=largura;
	    this.altura=altura;
		this.x = x;
		this.y =y;
		this.sprite = sprite;
	}
	
    public void renderizar(Graphics g)	{
    	g.drawImage(sprite,x - Camera.x,y - Camera.y,largura,altura,null);
    }

}
