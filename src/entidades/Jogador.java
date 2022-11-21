package entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;
import mundo.Camera;
import mundo.Mundo;

public class Jogador extends Entidade{
    
	public double speed = 3.5;
	public int right = 0;
	public int left = 0;
	public int top = 0;
	public int bottom = 0;
	public int horizontal;
	public int vertical;
	public int facingLeft = -1;
	public int facingRight = 1;
	public int killCount=0;
	public int vida = 5;
	
	public boolean atirando = false;
	private BufferedImage player;
	public Jogador(int x, int y, int largura, int altura, BufferedImage sprite) {
		super(x, y, largura, altura, sprite);
		player = Joguinho.playerSprite.getSprite(336, 48, 249, 435);
	}
	
	
	public void tic() {
		int dx = 0;
		int px = 0;
		double doubleRandomTypeBullet = Math.random() * 3;
        int randomTypeBullet = (int)doubleRandomTypeBullet;
		if(facingLeft == 1) {
			dx=-2;
			px=50;
		}else if(facingLeft == -1) {
			dx=2;
			px=0;
		}
		if(atirando) {
			
			for(int i = 0; i < 30000; i++) {
				if(i==1) {
					balaPlayer balaPlayer = new balaPlayer(this.getX()-px,this.getY()+15, 50, 50, Entidade.balaPlayer,dx,randomTypeBullet);
					Joguinho.balasPlayer.add(balaPlayer);
				}
				if(i>=29999) {
					atirando = false;
				}
			}
		}
		
		if(!Joguinho.conversaComNpc) {
		
		horizontal = right-left;
		vertical = bottom - top;
		x+=(horizontal)*speed;
		y+=(vertical)*speed;
		}
	}


	public void render(Graphics g) {
		g.drawImage(player,this.getX() - Camera.x -(40*facingLeft),this.getY() - Camera.y,103*facingLeft,180,null);
	}
}
	

	



