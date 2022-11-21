package Jotaca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entidades.Entidade;

public class Menu extends Entidade{
	private BufferedImage tela;
	public String [] options = {"Novo jogo", "Carregar jogo", "Sair"};
    public int op��oAtual = 0;
    public int m�ximoDeOp��es = options.length - 1;
    public static boolean cima, baixo, enter;
    
    public Menu(int x, int y, int largura, int altura, BufferedImage sprite) {
    	super(x, y, largura, altura, sprite);
    	tela = Joguinho.imagemDoMenu.getSprite(0, 0, 720, 480);
    	
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
			if(options[op��oAtual] == "Novo jogo") {
				Joguinho.gameState = "NORMAL";
				
			}
			if(options[op��oAtual] == "Sair") {
				System.exit(0);
			}
			
		}
	}
	public void render(Graphics g) {
		
		g.drawImage(tela ,0,0,Joguinho.LARGURA*Joguinho.ESCALA,Joguinho.ALTURA*Joguinho.ESCALA,null);
		
		g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD,30));
		g.drawString("Jog�o show massa demais", 
				(Joguinho.LARGURA*Joguinho.ESCALA)/2 - 240, (Joguinho.ALTURA*Joguinho.ESCALA)/2 - 200);
		g.setFont(new Font("arial", Font.BOLD,30));
		g.drawString("Novo jogo",0 , (Joguinho.ALTURA*Joguinho.ESCALA) - 200);
		g.setFont(new Font("arial", Font.BOLD,30));
		g.drawString("Carregar jogo",0, (Joguinho.ALTURA*Joguinho.ESCALA) - 160);
		g.setFont(new Font("arial", Font.BOLD,30));
		g.drawString("Sair",0 , (Joguinho.ALTURA*Joguinho.ESCALA) - 120);
		if(options[op��oAtual] == "Novo jogo") {
			g.drawString("<",160, (Joguinho.ALTURA*Joguinho.ESCALA) - 198);
		}
		if(options[op��oAtual] == "Carregar jogo") {
			g.drawString("<",210, (Joguinho.ALTURA*Joguinho.ESCALA) - 158);
		}
		if(options[op��oAtual] == "Sair") {
			g.drawString("<",70, (Joguinho.ALTURA*Joguinho.ESCALA) - 118);
		}
		
	}
}
