package org.example;

import org.example.serverPackage.RmiServerAccessInterface;
import org.example.serverPackage.RmiServerGameInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayDeque;
import java.util.Deque;

public class Client {
    private final String name;
    private final ThreadInputClient threadInputClient;
    private final ThreadOutputClient threadOutputClient;
    private Deque<String> commandForOutput;
    private Status status;

    private RmiClientInterface rmiClient;

    Object lock;

    public Client(String name){
        lock = new Object();
        commandForOutput = new ArrayDeque<>();
        this.name=name;
        threadInputClient = new ThreadInputClient(this, lock);
        threadOutputClient = new ThreadOutputClient( this);
        status = Status.BEFOREGAME;
    }

    public void askToPlay() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        RmiServerAccessInterface server = (RmiServerAccessInterface) registry.lookup("accessServer");

        String servergame = server.registerToServer(name);
        System.out.println("il nome del server è: " + servergame);
        registry = LocateRegistry.getRegistry(1098);
        RmiServerGameInterface serverGameInterface = (RmiServerGameInterface) registry.lookup(servergame);

        rmiClient = new RmiClient(serverGameInterface, this);
        serverGameInterface.setClient(rmiClient);
        System.out.println("Ho settato il client che non dovrebbe essere più null");
    }


    public void initThread(){
        Thread thread1 = new Thread(threadOutputClient);
        Thread thread2 = new Thread(threadInputClient);
        thread1.start();
        thread2.start();
    }


    public void notifyIsYourTurn(){
        synchronized (getStatus()) {
            status = Status.ITSMYTURN;
        }
        System.out.println("It's your turn. You have to insert your next move with right command." +
                " The game will not continue if you don't insert it");
    }

    public Status getStatus() {
        return status;
    }

    public Deque<String> getCommandForOutput() {
        return commandForOutput;
    }

    public void updateGrid(ObjectCard[][] objectCards){
        //TODO fai il metodo
    }

    public void updateLibrary(String nameOfPlayer, ObjectCard[][] objectCards){
        //TODO fai il metodo
    }

    public void initLibrary(ObjectCard[][] objectCards){
        //TODO inizializza tutte le librerie dei giocatori
    }

}
