package org.example;

import java.util.Queue;
import java.util.Scanner;

public class ThreadInputClient implements Runnable{
    String messageFromCLI;
    final Queue<String> queueOfCommand;

    private  final Client client;
    boolean stopReading;

    final Object lock;
    public ThreadInputClient(Client client, Object lock){
        messageFromCLI=null;
        this.queueOfCommand= client.getCommandForOutput();
        stopReading=false;
        this.client=client;
        this.lock=lock;
    }
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(!stopReading) {
            System.out.println("Insert a command. You can not use commend #move if is not your turn." +
                    " Available command are:");
            System.out.println("#showTurn");
            System.out.println("#showMyLibrary");
            System.out.println("#showAllLibrary");
            System.out.println("#showGrid");
            System.out.println("#move");
            System.out.println();
            messageFromCLI = scanner.nextLine().trim();
            if(client.getStatus()==Status.ITSMYTURN)
                manageTurn();
            manageMessage();
        }
    }

    private void manageTurn(){
        //TODO controlllo che inserisce effettivamente il turno, altrimenti richiedi
    }
    private void manageMessage() {
        synchronized (queueOfCommand) {
            queueOfCommand.add(messageFromCLI);
        }
        queueOfCommand.notifyAll();
    }

    public void setStopReading(boolean stopReading) {
        this.stopReading = stopReading;
    }
}
