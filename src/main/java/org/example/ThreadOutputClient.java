package org.example;

import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class ThreadOutputClient implements Runnable{
    private Queue<String> queueOfCommand;

    private boolean stopWriting;
    private final Lock lock;
    Client client;
    public ThreadOutputClient(Client client, Lock lock){
        this.queueOfCommand=client.getCommandForOutput();
        this.client=client;
        this.lock=lock;
        this.stopWriting=false;
    }

    @Override
    public void run() {
        String command=null;
        while(!stopWriting) {
            lock.lock();
            if (!queueOfCommand.isEmpty()) {
                command=queueOfCommand.remove();
            }
            lock.unlock();
            if(command!=null)
                manage(command);
            command=null;
        }
    }

    public void manage(String command){
        System.out.println("Your input command is " + command);
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
        else if(command.startsWith("#text")){//metodo esempio che mi serviva per vedere se potevo stampare in mezzo a scritte
            command = command.replace("#text", "");
            System.out.println(command);
        }
        else
            showErrorMessage();
    }

    public void setStopWriting(boolean stopWriting) {
        this.stopWriting = stopWriting;
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
