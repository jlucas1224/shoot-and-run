package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;
import mundo.Camera;

public class Entidade {
 
	
	
 protected int x;
 protected int y;
 protected int largura;
 protected int altura;
 
 private BufferedImage sprite;
 public static BufferedImage Inimigo = Joguinho.enemySprite.getSprite(12,18,10,10);
 public static BufferedImage balaPlayer = Joguinho.balasSprite.getSprite(483,211,62,70);
 private int maskx, masky, mlargura, maltura;
 
      public Entidade(int x,int y,int largura, int altura,BufferedImage sprite) {
    	  this.x=x;
    	  this.y=y;
    	  this.largura=largura;
    	  this.altura=altura;
    	  this.sprite=sprite;
    	  
    	  this.maskx = 0;
    	  this.masky = 0;
    	  this.maltura = altura;
    	  this.mlargura = largura;
      }
      
      public void setMask(int maskx, int masky, int mlargura, int maltura) {
    	  this.maskx = maskx;
    	  this.masky = masky;
    	  this.maltura = maltura;
    	  this.mlargura = mlargura;
      }
      
      public void setX(int newX) {
    	  this.x=newX;	 
      }
      public void setY(int newY) {
    	  this.y=newY;
      }
      
      public int getX() {
    	  return (int)this.x;
      }

      public int getY() {
    	  return (int)this.y;
      }

      public int getWidth() {
    	  return this.largura;
      }

      public int getHeight() {
    	  return this.altura;
      }
      public void tic() {
    	  
      }
      
      public static boolean isColliding(Entidade e1, Entidade e2) {
    	Rectangle e1mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mlargura, e1.maltura);
  		Rectangle e2mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mlargura, e2.maltura);
  		
  		return e1mask.intersects(e2mask);
      }
      
      
      public void render(Graphics g) {
    	  g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y,null);
    	  
      }
}
