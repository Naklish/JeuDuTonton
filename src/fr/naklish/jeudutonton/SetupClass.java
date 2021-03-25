package fr.naklish.jeudutonton;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;










public class SetupClass extends StateBasedGame implements Runnable {
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;
	public static Player player = new Player();
	public static Player opponnent = new Player();
	private Thread t;
	public static boolean start = false;
	public static boolean nextRound = true;
	
	
	public SetupClass(String title) {
		super(title);
		try {
			Socket s = new Socket("LocalHost", 61234);
			bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			printWriter = new PrintWriter(s.getOutputStream(), true);
			t = new Thread(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		
	}
	
	public static void main(String[] args) throws SlickException {
		SetupClass sc = new SetupClass("Jeu du tonton");
		sc.start();
		
		System.setProperty("org.lwjgl.librarypath", new File("slick/").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(sc);
		
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		app.setTargetFrameRate(200);
		app.start();
	}
	
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new GameStateMenu());
		this.addState(new GameStateJoueur1());
		this.addState(new GameStateChoose1());
		this.addState(new GameStateBet1());
		this.addState(new GameStateResult());
		this.addState(new GameStateOver());   
	}

	@Override
	public void run() {
		System.out.println("method run");
		while(true) {
			
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(SetupClass.player.isStartPressed() == true) {
				printWriter.println("[start]");
				while(true) {
					try {
						Thread.sleep(1000);
						
						//Récupération du message du server si les deux joueurs ont appuyé sur start
						String start = bufferedReader.readLine();
						if(start.equals("[start]")) {
							SetupClass.start = true;
							break;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				break;
				}
		}while(true) {
			try {
				Thread.sleep(1000);
				if(SetupClass.player.getNom() != null) {
					
					//Envoie du pseudo au server
					printWriter.println(SetupClass.player.getNom());
					
					//Récupération du pseudo du second joueur
					SetupClass.opponnent.setNom(bufferedReader.readLine());
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(nextRound) {
			System.out.println("New Round");
				while(true) {
					try {
						Thread.sleep(1000);
						if(SetupClass.player.isChoix() == true) {
							System.out.println("Envoie et réception des choix");
							//Envoie du choix du nombre de caillasse au server
							printWriter.println(SetupClass.player.getChoixCaillasse());
							
							//Récupération du choix du second joueur
							SetupClass.opponnent.setChoixCaillasse(Integer.valueOf(bufferedReader.readLine()));
							SetupClass.opponnent.setChoix(true);
							break;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				while(true) {
					try {
						Thread.sleep(1000);
						if(SetupClass.player.isBet()) {
							//Envoie du pronostique au server
							printWriter.println(SetupClass.player.getPronostique());
							
							//Récupération du pronostique du second joueur
							SetupClass.opponnent.setPronostique(Integer.valueOf(bufferedReader.readLine()));
							SetupClass.opponnent.setBet(true);
							break;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(true) {
					try {
						Thread.sleep(1000);
						if(SetupClass.player.isNext() == true) {
							System.out.println("next");
							printWriter.println("[next]");
							
							String next = bufferedReader.readLine();
							if(next.equals("[next]")) {
								SetupClass.opponnent.setNext(true);
								break;
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(true) {
					try {
						Thread.sleep(1000);
						System.out.println("Waiting...");
						if(SetupClass.opponnent.isNext()) {
							System.out.println("opponent is next...");
							break;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
			
		
	}
			
	
	
	public void start() {
		t.start();
	}
	
}