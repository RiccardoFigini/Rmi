package org.example;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiClientInterface extends Remote {
    void notifyAMove(String nameOfPlayer, ObjectCard[][] objectCards) throws RemoteException;

    void notifyTurn()throws RemoteException;

    RmiClientInterface getClient()throws RemoteException;

    void startGame(ObjectCard[][] grid, ObjectCard[][] initlibrary)throws RemoteException;

    void printSomethig(String string)throws RemoteException;
}
