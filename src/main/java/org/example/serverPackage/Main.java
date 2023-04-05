package org.example.serverPackage;

import org.example.RmiClient;
import org.example.RmiClientInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        Registry registry = LocateRegistry.createRegistry(1099);
        RmiServerAccessInterface rmiServerAccess = new RmiServerAccess();
        registry.rebind("accessServer", rmiServerAccess);
        System.out.println("Ho azionato il server per la registrazione");

        Registry registry2 = LocateRegistry.createRegistry(1098);
        RmiServerGameInterface rmiServerGame = new RmiServerGame();
        registry2.rebind("gameServer", rmiServerGame);
        System.out.println("Ho azionato il server per la partita");

        /*In teoria il primo server dovrebbe essere sempre accesso il secondo acceso
        * quando c'è una partita da riempire*/
        int i=0;
        RmiServerGame rmiServerGame1 = (RmiServerGame) rmiServerGame;
        RmiClientInterface rmiClient = rmiServerGame1.getRmiClient();
        while (rmiClient==null){
            i++;
            Thread.sleep(1000);
            System.out.println(i);
            rmiClient = rmiServerGame1.getRmiClient();
        }

        rmiClient.startGame(null, null);
        System.out.println("Ho lanciato il client");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine().trim();
        rmiClient.notifyTurn();
    }
}
