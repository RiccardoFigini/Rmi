package org.example.serverPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerAccessInterface extends Remote {
    String registerToServer(String andrea) throws RemoteException;
}
