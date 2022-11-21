package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;
import mundo.Camera;

public class balaPlayer extends Entidade{
private BufferedImage balaPlayer;
private int maxDistance = 40;
private int dx;
private double speed = 4;
private int topY = 3;
private int bottomY = -3;
private boolean subindo=true;
public int dano = 1;
private boolean descendo;
private boolean launch = true;
private double ySpeed =0;
public double yPosition=1;
private int distance = 1;
private int type;

private int angle=0;
private boolean indo = true;
private boolean voltando = false;
	public balaPlayer(int x, int y, int largura, int altura, BufferedImage sprite, int dx, int type) {
		super(x, y, largura, altura, sprite);
		this.dx = dx;
		this.type=type;
		this.y=y;
		balaPlayer = Joguinho.balasSprite.getSprite(483,211,62,70);
	}
	public void renderRastrosBalasPlayer(Graphics g) {
		for(int i=0;i<Joguinho.rastroBalasPlayer.size();i++) {
			Entidade e = Joguinho.rastroBalasPlayer.get(i);
			e.render(g);
		}
	}
	public void criarRastros() {
		RastroBalaPlayer rastroBalaPlayer = new RastroBalaPlayer(this.getX()- Camera.x ,this.getY() +50- Camera.y - (int)yPosition, 50, 50, balaPlayer);
		Joguinho.rastroBalasPlayer.add(rastroBalaPlayer);
	}
public void tic() {
	
	if(type==0) {
	}
	else if(type == 1) {
		if(subindo) {
			yPosition+=0.5*15;
			if(yPosition>=topY*15) {
				subindo = false;
				descendo = true;
			}
		}else {
			yPosition-=0.5*15;
			if(yPosition<=bottomY*15) {
				subindo = true;
				descendo = false;
			}
		}
	}else if(type == 2) {
		if(subindo) {
			yPosition-=0.5*15;
			if(yPosition<=bottomY*15) {
				subindo = false;
				descendo = true;
			}
		}else {
			yPosition+=0.5*15;
			if(yPosition>=topY*15) {
				subindo = true;
				descendo = false;
			}
		}
	}
	x+=dx*speed;
	distance++;
	if(distance>=maxDistance) {
		Joguinho.balasPlayer.remove(this);
	}
	
	if(indo) {
		angle+=5;
		if(angle>=360) {
			indo = false;
			voltando = true;
		}
	}
	if(voltando) {
		angle-=5;
		if(angle<=-360) {
			voltando = false;
			indo = true;
		}
	}
	
}
public void render(Graphics g) {
	
	renderRastrosBalasPlayer(g);
	//g.drawImage(balaPlayer,this.getX()- Camera.x ,this.getY() +50- Camera.y - (int)yPosition,40,40,null);

	AffineTransform at = new AffineTransform();
    at.translate(this.getX()- Camera.x ,(this.getY() +50- Camera.y - (int)yPosition)); // draw image in center of frame
    // at.scale(0.5, 0.5);
    at.rotate(Math.toRadians(angle),40/2,40/2);
    at.scale(0.64, 0.57);

    Graphics2D g2d = (Graphics2D) g;
    
    
    g2d.drawImage(balaPlayer, at, null);
}
}
