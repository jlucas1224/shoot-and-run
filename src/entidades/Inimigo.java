package entidades;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;
import mundo.Camera;



public class Inimigo extends Entidade{
	private boolean moved = true;
	
	public int personagemFrame;
	
	private int vida;
	
	private int maskx = 0, masky = 0, maskw = 16, maskh = 16;
	private int frames = 0, maxFrames = 5, index, maxIndex= 3;
	private BufferedImage inimigo1;
	
	private int angle=0;
	private boolean indo = true;
	private boolean voltando = false;
	private int velocidade;
	
	public Inimigo(int x, int y, int width, int height, BufferedImage sprite, int velocidade, int vida) {
		
		super(x, y, width, height, sprite);
		
		this.vida = vida;
		this.velocidade = velocidade;
		inimigo1 = Joguinho.enemySprite.getSprite(12,18,125,180);
		
		
	}
	
	public boolean isCollidingWithPlayer() {	
		Rectangle inimigo = new Rectangle(this.getX() - Camera.x, (this.getY() - Camera.y)/2 , 125, 180);
		Rectangle player = new Rectangle(Joguinho.player.getX(),Joguinho.player.getY(),16,16);
		return inimigo.intersects(player);
	}
	
	public void morrer() {
		Joguinho.enemies.remove(this);
		
	}
	public boolean levandoTiro() {
		
		for(int i = 0; i<Joguinho.balasPlayer.size();i++) {
			balaPlayer e = Joguinho.balasPlayer.get(i);
			
			Rectangle bala = new Rectangle(e.getX(),e.getY() +50- (int)e.yPosition,40,40);
			Rectangle inimigo = new Rectangle(this.getX() - Camera.x, (this.getY() - Camera.y)/2 , 125, 180);
			
			if(inimigo.intersects(bala)) {
				Joguinho.balasPlayer.remove(i);
				vida-=e.dano;
				
				return true;
			}
		}
		return false;
	}
	
	public void tic() {
		
		if(indo) {
			angle++;
			if(angle>=10) {
				indo = false;
				voltando = true;
			}
		}
		if(voltando) {
			angle--;
			if(angle<=-10) {
				voltando = false;
				indo = true;
			}
		}
		 
		x-=velocidade;
		if(isCollidingWithPlayer()) {
			Joguinho.player.vida--;
			morrer();
		}
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index=0;
				}
			}
		}
		else {
			index = 0;
		}
		if(levandoTiro()) {
			
		}
		if(vida <=0) {
			Joguinho.player.killCount++;
			morrer();
		}
	}
	

    
	public void render(Graphics g) {	

		
         AffineTransform at = new AffineTransform();
         at.translate((this.getX() - Camera.x), (this.getY() - Camera.y)/ 2); // draw image in center of frame
         // at.scale(0.5, 0.5);
         at.rotate(Math.toRadians(angle),125/2,180/2);
         

         Graphics2D g2d = (Graphics2D) g;
         
         
         g2d.drawImage(inimigo1, at, null);
         

	}
}
