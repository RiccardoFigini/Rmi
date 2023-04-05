package org.example;

import java.util.Queue;

public class ThreadOutputClient implements Runnable{
    final Queue<String> queueOfCommand;

    Client client;
    public ThreadOutputClient(Client client){
        this.queueOfCommand=client.getCommandForOutput();
        this.client=client;
    }

    @Override
    public void run() {
        String command;
        synchronized (queueOfCommand){
            while(queueOfCommand.isEmpty()) {
                try {
                    queueOfCommand.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            command = queueOfCommand.remove();
        }
        queueOfCommand.notifyAll();
        manage(command);
    }

    public void manage(String command){
        if(command.startsWith("#showGrid"))
            showGrid();
        else if(command.startsWith("#showMyLibrary"))
            showMyLibrary();
        else if(command.startsWith("#showAllLibrary"))
            showAllLibrary();
        else if(command.startsWith("#showTurn"))
            showTurn();
        else if(command.startsWith("#move"))
            move();
        else
            showErrorMessage();
    }

    public void showGrid(){
        System.out.println("Ecco la griglia");
    }

    public void showMyLibrary(){
        System.out.println("Ecco la tua libreria");
    }

    public void showTurn(){
        System.out.println("Ecco di chi è il turno");
    }

    public void showAllLibrary(){
        System.out.println("Ecco tutte le librerie");
    }

    public void showErrorMessage(){
        System.out.println("Questo comando non esiste");
    }

    public void move(){
        System.out.println("Dovrei gestire la mossa a questo punto");
    }
}
