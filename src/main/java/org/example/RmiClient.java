package org.example;

import org.example.serverPackage.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient extends UnicastRemoteObject implements RmiClientInterface {
    RmiServerGameInterface server;
    Client client;
    public RmiClient(RmiServerGameInterface server, Client client) throws RemoteException {
        super();
        this.server=server;
        this.client = client;
    }

    @Override
    public void notifyAMove(String nameOfPlayer, ObjectCard[][] objectCards)throws RemoteException{
        System.out.println("A move is been made, grid and "+nameOfPlayer+"'s have been changed");
        client.updateGrid(objectCards);
        client.updateLibrary(nameOfPlayer, objectCards);
    }

    @Override
    public void notifyTurn()throws RemoteException{
        client.notifyIsYourTurn();
    }

    @Override
    public RmiClientInterface getClient() throws RemoteException{
        return this;
    }

    @Override
    public void startGame(ObjectCard[][] grid, ObjectCard[][] initlibrary)throws RemoteException{
        client.updateGrid(grid);
        client.initLibrary(initlibrary);
        client.initThread();
        System.out.println("Sono partito");
    }
}
