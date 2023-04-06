package org.example;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class ThreadInputClient implements Runnable{
    private String messageFromCLI;
    private Queue<String> queueOfCommand;

    private  final Client client;
    private boolean stopReading;

    private final Lock lock;

    public ThreadInputClient(Client client, Lock lock){
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
            System.out.println("Remember it is case sensitive. Insert you move: ");
            messageFromCLI = scanner.nextLine().trim();
            if(client.getStatus()==Status.ITSMYTURN)
                manageTurn();/*Da impleentare*/
            manageMessage();
        }
    }

    private void manageTurn(){
        //TODO controlllo che inserisce effettivamente il turno, altrimenti richiedi
    }
    private void manageMessage() {
        lock.lock();
        queueOfCommand.add(messageFromCLI);
        lock.unlock();
    }

    public void setStopReading(boolean stopReading) {
        this.stopReading = stopReading;
    }
}
