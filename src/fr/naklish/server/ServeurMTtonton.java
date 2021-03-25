package fr.naklish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


//Création serveur Multi-Thread extends Thread
public class ServeurMTtonton extends Thread{
	private int nbClients;
	private List<Socket> listSocket = new ArrayList<Socket>();
	private List<Connection> listConnection = new ArrayList<Connection>();
	
	@Override
	public void run() {
		try {
			//Création Serveur Socket
			ServerSocket ss = new ServerSocket(61234);
			System.out.println("Démarrage du server");
			//Boucle d'attente de connexion
			while(nbClients < 2) {
				//Création Socket client / ServeurSocket attend connexion
				Socket s = ss.accept();
				listSocket.add(s);
				
				nbClients++;
				//Création (connexion) de la conversation prend en parametre un Socket(Client)
				Connection connection = new Connection(s, nbClients);
				connection.start();
				listConnection.add(connection);
			}
			System.out.println("Waiting for players...");
			while(true) {
				Thread.sleep(2000);
				if(this.isStartGame(listConnection)) {
					this.messageToClients("[start]");
					System.out.println("Starting game...");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				
		}
		}
	
	public static void main(String[] args) {
		new ServeurMTtonton().start();
	}
	
	public boolean isStartGame(List<Connection> listConnection) {
		boolean start = true;
		
		for(Connection connection : listConnection) {
			if(connection.isReady() == false) {
				start = false;
			}
		}
		
		return start;
	}
	
	public void messageToClients(String message) {
		for(Socket s : listSocket) {
			try {
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void messageClientToClient(String message, Socket source) {
		for(Socket s : listSocket) {
			try {
				if(s != source) {
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println(message);
				pw.flush();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void booleanClientToClient(boolean bool, Socket source) {
		for(Socket s : listSocket) {
			try {
				if(s != source) {
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println(bool);
				pw.flush();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//Création class Connection extends Thread
	class Connection extends Thread {
		private Socket socket;
		private int numeroClient;
		private boolean ready = false;
		
		public Connection(Socket socket, int num) {
			super();
			this.socket = socket;
			this.numeroClient = num;
		}
		
		// Code de la connection
		@Override
		public void run() {
			try {
				//Lecture
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				//Envoie
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				pw.flush();
				//adresse ip du client
				String ip = socket.getRemoteSocketAddress().toString();
				
				System.out.println("Connexion du client numéro " + numeroClient + "IP = " + ip);
				
				
				while(true) {
					String requete = br.readLine();
					System.out.println(ip + " a envoyé " + requete);
					if(requete.equals("[start]")) {
						setReady(true);
					}
					break;
				}
				while(true) {
					Thread.sleep(2000);
					System.out.println("Waiting for nicknames...");
					String pseudo = br.readLine();
					System.out.println("IP=" + ip + " ; Pseudo = " + pseudo);
					messageClientToClient(pseudo, socket);
					break;
				}
				while(true) {
					System.out.println("Starting round...");
					while(true) {
						Thread.sleep(1000);
						System.out.println("Waiting for choices...");
						String choix = null; 
						choix = br.readLine();
						System.out.println("IP=" + ip + " ; Choix = " + choix);
						messageClientToClient(choix, socket);
						break;
					}
					while(true) {
						Thread.sleep(1000);
						System.out.println("Waiting for bets...");
						String bet = null;
						bet = br.readLine();
						System.out.println("IP=" + ip + " ; Bet = " + bet);
						messageClientToClient(bet, socket);
						break;
					}
					while(true) {
						Thread.sleep(1000);
						System.out.println("Waiting to continue...");
						String next = br.readLine();
						if(next.equals("[next]")) {
							System.out.println("IP=" + ip + " ; is ready for next round");
							messageClientToClient(next, socket);
							break;
						}
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		public boolean isReady() {
			return ready;
		}
		
		public void setReady(boolean ready) {
			this.ready = ready;
		}
		
	}
}
	


