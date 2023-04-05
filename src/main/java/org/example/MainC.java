package org.example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainC {
    public static void main(String[] args) throws NotBoundException, RemoteException {
        Client client = new Client("Andrea");
        client.askToPlay();
    }
}
