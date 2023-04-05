package org.example.serverPackage;

import org.example.Position;
import org.example.RmiClient;
import org.example.RmiClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerGameInterface extends Remote{
    void sendMove(Position[] positions) throws RemoteException;

    void setClient(RmiClientInterface rmiClient)throws RemoteException;

}
