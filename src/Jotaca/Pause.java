package Jotaca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entidades.Entidade;

public class Pause extends Entidade{
	private BufferedImage tela;
	public String [] options = {"Continuar", "Inventário", "Sair"};
    public int opçãoAtual = 0;
    public int máximoDeOpções = options.length - 1;
    public static boolean cima, baixo, enter;
    public static boolean pause = false;
    public static boolean inventário;
    
    public Pause(int x, int y, int largura, int altura, BufferedImage sprite) {
    	super(x, y, largura, altura, sprite);
    	tela = Joguinho.imagemDoMenu.getSprite(0, 480, 720, 480);
    	
    }
	public void tick() {
		if(cima) {
			cima = false;
			opçãoAtual --;
			if(opçãoAtual < 0) {
				opçãoAtual = máximoDeOpções;
			}
		}
		if(baixo) {
			baixo = false;
			opçãoAtual ++;
			if(opçãoAtual > máximoDeOpções) {
				opçãoAtual = 0;
			}
		}
		if(enter) {
			enter = false;
			if(options[opçãoAtual] == "Continuar") {
				Joguinho.gameState = "NORMAL";
				pause = false;
				
			}
			if(options[opçãoAtual] == "Sair") {
				System.exit(0);
			}
			if(options[opçãoAtual] == "Inventário") {
				Joguinho.gameState = "INVENTÁRIO";
			
				inventário = true;
			}
		}
	}
	public void render(Graphics g) {
		
		g.drawImage(tela ,0,0,Joguinho.LARGURA*Joguinho.ESCALA,Joguinho.ALTURA*Joguinho.ESCALA,null);
		g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD,30*Joguinho.ESCALA/3));
		g.drawString("Continuar",(Joguinho.LARGURA*Joguinho.ESCALA)*13/18, (Joguinho.ALTURA*Joguinho.ESCALA)*80/480);
		g.setFont(new Font("arial", Font.BOLD,30*Joguinho.ESCALA/3));
		g.drawString("Inventário",(Joguinho.LARGURA*Joguinho.ESCALA)*13/18, (Joguinho.ALTURA*Joguinho.ESCALA)*120/480);
		g.setFont(new Font("arial", Font.BOLD,30*Joguinho.ESCALA/3));
		g.drawString("Sair",(Joguinho.LARGURA*Joguinho.ESCALA)*13/18, (Joguinho.ALTURA*Joguinho.ESCALA)*160/480);
		if(options[opçãoAtual] == "Inventário") {
			g.drawString(">", (Joguinho.LARGURA*Joguinho.ESCALA)*25/36, (Joguinho.ALTURA*Joguinho.ESCALA)*1/4);
		}
		if(options[opçãoAtual] == "Continuar") {
			g.drawString(">", (Joguinho.LARGURA*Joguinho.ESCALA)*25/36, (Joguinho.ALTURA*Joguinho.ESCALA)*1/6);
		}
		if(options[opçãoAtual] == "Sair") {
			g.drawString(">",(Joguinho.LARGURA*Joguinho.ESCALA)*25/36, (Joguinho.ALTURA*Joguinho.ESCALA)*1/3);
		
		}
		
	}

}
