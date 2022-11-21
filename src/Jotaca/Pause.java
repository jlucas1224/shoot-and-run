package Jotaca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entidades.Entidade;

public class Pause extends Entidade{
	private BufferedImage tela;
	public String [] options = {"Continuar", "Invent�rio", "Sair"};
    public int op��oAtual = 0;
    public int m�ximoDeOp��es = options.length - 1;
    public static boolean cima, baixo, enter;
    public static boolean pause = false;
    public static boolean invent�rio;
    
    public Pause(int x, int y, int largura, int altura, BufferedImage sprite) {
    	super(x, y, largura, altura, sprite);
    	tela = Joguinho.imagemDoMenu.getSprite(0, 480, 720, 480);
    	
    }
	public void tick() {
		if(cima) {
			cima = false;
			op��oAtual --;
			if(op��oAtual < 0) {
				op��oAtual = m�ximoDeOp��es;
			}
		}
		if(baixo) {
			baixo = false;
			op��oAtual ++;
			if(op��oAtual > m�ximoDeOp��es) {
				op��oAtual = 0;
			}
		}
		if(enter) {
			enter = false;
			if(options[op��oAtual] == "Continuar") {
				Joguinho.gameState = "NORMAL";
				pause = false;
				
			}
			if(options[op��oAtual] == "Sair") {
				System.exit(0);
			}
			if(options[op��oAtual] == "Invent�rio") {
				Joguinho.gameState = "INVENT�RIO";
			
				invent�rio = true;
			}
		}
	}
	public void render(Graphics g) {
		
		g.drawImage(tela ,0,0,Joguinho.LARGURA*Joguinho.ESCALA,Joguinho.ALTURA*Joguinho.ESCALA,null);
		g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD,30*Joguinho.ESCALA/3));
		g.drawString("Continuar",(Joguinho.LARGURA*Joguinho.ESCALA)*13/18, (Joguinho.ALTURA*Joguinho.ESCALA)*80/480);
		g.setFont(new Font("arial", Font.BOLD,30*Joguinho.ESCALA/3));
		g.drawString("Invent�rio",(Joguinho.LARGURA*Joguinho.ESCALA)*13/18, (Joguinho.ALTURA*Joguinho.ESCALA)*120/480);
		g.setFont(new Font("arial", Font.BOLD,30*Joguinho.ESCALA/3));
		g.drawString("Sair",(Joguinho.LARGURA*Joguinho.ESCALA)*13/18, (Joguinho.ALTURA*Joguinho.ESCALA)*160/480);
		if(options[op��oAtual] == "Invent�rio") {
			g.drawString(">", (Joguinho.LARGURA*Joguinho.ESCALA)*25/36, (Joguinho.ALTURA*Joguinho.ESCALA)*1/4);
		}
		if(options[op��oAtual] == "Continuar") {
			g.drawString(">", (Joguinho.LARGURA*Joguinho.ESCALA)*25/36, (Joguinho.ALTURA*Joguinho.ESCALA)*1/6);
		}
		if(options[op��oAtual] == "Sair") {
			g.drawString(">",(Joguinho.LARGURA*Joguinho.ESCALA)*25/36, (Joguinho.ALTURA*Joguinho.ESCALA)*1/3);
		
		}
		
	}

}
