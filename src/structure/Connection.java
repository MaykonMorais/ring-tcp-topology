package structure;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Connection implements Runnable {
	private String host;
	private int port;
	private int originId;
	
	public Connection(String host, int port, int originId) {
		setHost(host);
		setPort(port);
		setOriginId(originId);
	}
	
	@Override
	public void run() {
		
		
		while(true) {
			
			try {
				Socket serverConnection = new Socket(getHost(), getPort());
				Process process = new Process();
				process.setServer(serverConnection);
				
				System.out.println("Connected to the server");
				
				Scanner input = new Scanner(System.in);
				PrintStream outputStream = new PrintStream(serverConnection.getOutputStream());
				
				while(input.hasNextLine()) {
					// Origin Id Process:Message
					outputStream.println(getOriginId() + ":" + input.nextLine());
				}
				
				serverConnection.close();
				input.close();
				outputStream.close();
			} catch (UnknownHostException err) {
				System.out.println("Server not found!");
			} catch (IOException err) {
				
				System.out.println("Trying reconnect...");
				
				try {
					Thread.sleep(8000);
				} catch(InterruptedException e) {
					
					System.out.println("Ops! Error occurred while accessing Thread resource");
				}
			}
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getOriginId() {
		return originId;
	}

	public void setOriginId(int originId) {
		this.originId = originId;
	}
	
}
