package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;

public class RastroBalaPlayer extends Entidade{
	private int maxDistance = 6;
	private int distance = 1;
	public RastroBalaPlayer(int x, int y, int largura, int altura, BufferedImage sprite) {
		super(x, y, largura, altura, sprite);
		// TODO Auto-generated constructor stub
	}

	public void removerRastro() {
		if(distance>=maxDistance) {
			Joguinho.rastroBalasPlayer.remove(this);
		}
	}
	
	public void render(Graphics g) {
		distance++;
		
		if(distance<maxDistance) {
			g.setColor(new Color(255, 254, 165));
			g.fillOval(this.getX(), this.getY()+10, 20, 10);
		}
	}
}
