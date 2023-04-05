package org.example.serverPackage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.example.*;

public class RmiServerGame extends UnicastRemoteObject implements RmiServerGameInterface {
    RmiClientInterface rmiClient;
    public RmiServerGame() throws RemoteException {
        super();
    }

    @Override
    public void sendMove(Position[] positions){
        //TODO: questo metodo deve essere chiamato dal client
    }

    @Override
    public void setClient(RmiClientInterface rmiClient) throws RemoteException{
        this.rmiClient=rmiClient;
    }

    public RmiClientInterface getRmiClient() throws RemoteException{
        return rmiClient;
    }
}
