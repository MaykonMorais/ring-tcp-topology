package structure;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interfaces.IProcess;

public class Process extends Node implements IProcess {
	
	public Process() {}
	
	public Process(String host, int id, int serverPort, int clientPort) {
		super(id, host, serverPort, clientPort);
	}

	@Override
	public void execute() {
		try {
			ServerSocket server = new ServerSocket(getServerPort());
			
			System.out.println("\nServer running on the port: " + getServerPort());
			System.out.println();
			
			serviceExecutor(); /* Conexão Paralela entre os processos */
			
			Socket connection = server.accept();
			
			
			try {
				Scanner inputStream = new Scanner(connection.getInputStream());
				
				while(inputStream.hasNextLine()) {
					String message = inputStream.nextLine();
					System.out.println("Receive Message: " + message);
					
					if(getServer() != null) {
						PrintStream outputStream = new PrintStream(getServer().getOutputStream());
						
						String[] arrayByString = handleMessage(message);
						
						if(arrayByString != null) {
							outputStream.println(arrayByString[0] + ":" + arrayByString[1]);
						}
						
					}					
				}
				
				inputStream.close();
			} catch(IOException e) {
				System.out.println("\nErro receive Message");
			}
			server.close();
			connection.close();
			
		} catch(IOException err) {
			System.out.println("\nServer not connected");
		}		
	}

	@Override
	public void serviceExecutor() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Connection(getHost(), getClientPort(), getId()));
		executorService.shutdown();
	}

	@Override
	public String[] handleMessage(String message) {
		String [] arrayString =  message.split(":");
		String idOrigin = arrayString[0];
		
		if(Integer.parseInt(idOrigin) != getId()) {		
			/* Number verification */
			if(arrayString[1].matches("[0-9]*")) {
				arrayString[1] =  String.valueOf(Integer.parseInt(arrayString[1]) + getId());
			}
			/* String verification */
			else {
				arrayString[1] = arrayString[1] + getId();
			}
			return arrayString;
			
		} else {
			
			System.out.println("Message received back!: CYCLE STOPPED");
			System.out.println("Result: " + arrayString[1]);
			return null;
		}
		
	}
}
