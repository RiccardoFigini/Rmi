package org.example.serverPackage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiServerAccess extends UnicastRemoteObject implements RmiServerAccessInterface {

    protected RmiServerAccess() throws RemoteException {
    }
    @Override
    public String registerToServer(String andrea) throws RemoteException{
        //TODO: azioni per dargli il server corretto della partita a cui accedere
        return "gameServer";
    }
}
