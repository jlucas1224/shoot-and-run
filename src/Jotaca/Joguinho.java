package Jotaca;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entidades.Entidade;
import entidades.Inimigo;
import entidades.Jogador;
import entidades.Npc;
import entidades.RastroBalaPlayer;
import entidades.Spawner;
import entidades.balaPlayer;
import graficos.Spritesheet;
import graficos.UserInterface;
import mundo.Mundo;
import mundo.Tile;

public class Joguinho extends Canvas implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean estaRodando = true;
	public static final int ALTURA = 720; 
	public static final int LARGURA = 1280; 
	public static final int ESCALA = 1;
	private BufferedImage image;
	public static String gameState = "NORMAL";
	public static boolean conversaComNpc = true;
	private boolean mensagemDeGameOver = false;
	private int framesGameOver = 0;
	public static boolean confirmar = false;
	public static boolean iniciouAgora = true;
	
	public static String novoMundo = "Mapa 1.png";
	
	public static boolean playerAtirando;
	public static List<Entidade> entidades;
	public static List<Tile> tiles;
	public static List<Inimigo> enemies;
	public static List<balaPlayer> balasPlayer;
	public static List<RastroBalaPlayer> rastroBalasPlayer;
	public static Spritesheet sprites;
	public static Spritesheet cenario1;
	public static Spritesheet playerSprite;
	public static Spritesheet npcSprite;
	public static Spritesheet enemySprite;
	public static Spritesheet imagemDoMenu;
	public static Spritesheet imagemDoPause;
	public static Spritesheet balasSprite;
	public static Mundo mundo;
	private BufferedImage cenario;
	public UserInterface ui;
	public static Inimigo inimigo;
	public static Jogador player;
	public static Spawner spawner;
	public static Npc npc;	
	public Menu menu;
	public Pause pause;
	public Joguinho() {
		
		setPreferredSize(new Dimension(LARGURA*ESCALA, ALTURA*ESCALA));
		initFrame();
		addKeyListener(this);
		
		image = new BufferedImage(LARGURA,ALTURA,BufferedImage.TYPE_INT_RGB);
		ui = new UserInterface();
		entidades = new ArrayList<Entidade>();	
		tiles = new ArrayList<Tile>();
		enemies = new ArrayList<Inimigo>();
		balasPlayer = new ArrayList<balaPlayer>();
		rastroBalasPlayer = new ArrayList<RastroBalaPlayer>();
		sprites = new Spritesheet("/Sprites.png");
		imagemDoMenu = new Spritesheet("/Menu Imagem.png");
		cenario1 = new Spritesheet("/Cenario1.png");
		playerSprite = new Spritesheet("/Player.png");
		enemySprite = new Spritesheet("/Enemy.png");
		npcSprite = new Spritesheet("/NPC.png");
		balasSprite = new Spritesheet("/Balas.png");
		cenario = cenario1.getSprite(0, 0, 1280, 874);
		player = new Jogador(60,300,16,16,sprites.getSprite(0,16,16,16));
		entidades.add(player);
		spawner = new Spawner(373,420,16,16,sprites.getSprite(0,16,16,16));
		mundo = new Mundo("/Mapa.png");
		menu = new Menu	(60,60,16,16,imagemDoMenu.getSprite(0,0,16,16));
		pause = new Pause (60,60,16,16,imagemDoMenu.getSprite(32,0,16,16));
		npc = new Npc(2000,200,100,50,npcSprite.getSprite(264, 113, 434, 289));
		entidades.add(npc);
		
	}
	public void initFrame() {
		frame = new JFrame("Jogo Foda"); 
		frame.add(this); 
		frame.setResizable(false); 
	    frame.pack();
	    frame.setLocationRelativeTo(null); 
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	    frame.setVisible(true); 
	}
	
	public synchronized void start() {
		thread = new Thread(this);
	    estaRodando = true;
	    thread.start();
	     
	}
	
	public synchronized void stop(){ 
	    estaRodando = false;
		try {
			thread.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
    }
	
	public static void main(String[] args) {
		Joguinho janela = new Joguinho();
		janela.start();	
	}
	public void tickEntidades() {
		for(int i=0;i<entidades.size();i++) {
			Entidade e = entidades.get(i);
			e.tic();
		}
	}
	public void tickEnemies() {
		for(int i=0;i<enemies.size();i++) {
			Entidade e = enemies.get(i);
			e.tic();
		}
	}
	public void tickBalasPlayer() {
		for(int i=0;i<balasPlayer.size();i++) {
			Entidade e = balasPlayer.get(i);
			e.tic();
		}
	}
	public void renderBalasPlayer(Graphics g) {
		for(int i=0;i<balasPlayer.size();i++) {
			Entidade e = balasPlayer.get(i);
			e.render(g);
		}
	}
	
	public void tickGameOver() {
		this.framesGameOver++;
		if(this.framesGameOver==30) {
			this.framesGameOver = 0;
			if(this.mensagemDeGameOver)
				this.mensagemDeGameOver = false;
			else 
				this.mensagemDeGameOver = true;
	}
	
	}
	public void tick() { 
		
		if(gameState == "NORMAL") {
			tickBalasPlayer();
			tickEntidades();
			tickEnemies();
			tickEntidades();
			spawner.tic();
			if (gameState == "GAME_OVER") {
				tickGameOver();
			} else if (gameState =="MENU") {
				menu.tic();
			}
			else if(gameState == "PAUSE") {
				pause.tic();
			}
		}
	}
	
	public void renderEntidades(Graphics g) {
		for(int i=0;i<entidades.size();i++) {
			Entidade e = entidades.get(i);
			e.render(g);
			}
	}
	public void renderEnemies(Graphics g) {
		for(int i=0;i<enemies.size();i++) {
			Entidade e = enemies.get(i);
			e.render(g);
			}
	}
	public void renderGameOver(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0,0,LARGURA*ESCALA,ALTURA*ESCALA);
		g.setFont(new Font("arial", Font.BOLD,60));
	    g.setColor(Color.white);
	    g.drawString("Morreu kk" , (LARGURA*ESCALA)/2 -150, (ALTURA*ESCALA)/2 -100);
	    g.setColor(Color.white);
	    if(mensagemDeGameOver)
	    g.drawString("Tu é um otário" , (LARGURA*ESCALA)/2 -200, (ALTURA*ESCALA)/2 + 50);
	}
	public void render() { 
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
	    }
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,255));
		g.fillRect (0,0,LARGURA,ALTURA);  
		
		if(gameState == "NORMAL") {
	    	mundo.render(g);
	    	g.drawImage(cenario, 0, 0, LARGURA*ESCALA,ALTURA*ESCALA,null);
			renderEntidades(g);
			renderEnemies(g);
			renderBalasPlayer(g);
			ui.render(g);
			
	    }

	   
		g.dispose();
		g = bs.getDrawGraphics();
		 
		
	    g.drawImage(image, 0, 0, LARGURA*ESCALA,ALTURA*ESCALA,null);
	    if(gameState =="GAME_OVER") {
			renderGameOver(g);
		}
		else if(gameState == "MENU") {
			menu.render(g);
		}
		else if(gameState == "PAUSE") {
			pause.render(g);
			
		}
	    
		
		
		bs.show(); 
		
	}
	
	
	public void run() {
		long ultimoTempo = System.nanoTime();
		double quantidadeDeTics = 60.0;
	    double ns = 1000000000/quantidadeDeTics; //A cada quanto tempo o programa vai fazer um novo tic
	    double delta = 0;
	    int fps = 0;
	    double timer = System.currentTimeMillis(); //tempo atual em milissegundos
		requestFocus();
	    while(estaRodando){
	    	long tempoAtual = System.nanoTime();	    
	    	delta += (tempoAtual - ultimoTempo)/ns; // intervalo para executar o tic
	    	ultimoTempo = tempoAtual;
	    	if(delta>=1) {
	    		tick();
	    		render();
	    		fps++;
	    		delta--;
	    	}
	    	
	    		if(System.currentTimeMillis() - timer >= 1000) {
	    			System.out.println("Fps: "+fps);
	    			fps = 0;
	    			timer += 1000;
	    		}
		}
		stop();
	    }
	
	
		
			 
		
			
		
		
	public void keyPressed(KeyEvent e) {
		if(npc.escolher) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				npc.opçao++;				
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				npc.opçao--;				
			}
		}
		
		if(npc.estagios<30 && npc.podePular) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				npc.estagios++;
				
			}
		}
		
		if(gameState == "NORMAL") {
			if(!conversaComNpc) {
			if(!player.atirando) {
				if(e.getKeyCode() == KeyEvent.VK_J) {
					player.atirando = true;
						
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_W) {
				player.top=1;
				
			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				player.bottom=1;	
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {		
				player.left=1;		
				player.facingLeft=1;
				player.facingRight=-1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				player.facingRight=1;
				player.facingLeft=-1;
				player.right=1;
		    }
			}
		}
		
        if(e.getKeyCode() == KeyEvent.VK_W ||
        		e.getKeyCode() == KeyEvent.VK_UP) {
        	
			if(gameState == "MENU" || gameState == "PAUSE") {
				menu.cima = true;
				pause.cima = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_S ||
				e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if(gameState == "MENU" || gameState == "PAUSE") {
				menu.baixo = true;
				pause.baixo = true;
			}
		}
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        	Menu.enter = true;
        	Pause.enter = true;
        }
	}
	
	public void keyReleased(KeyEvent e) {
		if(gameState == "NORMAL") {
			if(!conversaComNpc) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				player.top=0;
				
			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				player.bottom=0;	
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {		
				player.left=0;		
			}
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				player.right=0;
		    }
		}
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	
	}

