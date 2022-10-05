package org.example;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        try {
            List<Integer> lista = new Vector<>();

            Semaphore sendPackageSemaphore = new Semaphore(3);
            Semaphore recievePackageSemaphore = new Semaphore(3);

            sendPackageSemaphore.acquire();

            Thread sendPackage = new Thread(new SendTask (sendPackageSemaphore,recievePackageSemaphore, lista));
            Thread receivePackage = new Thread( new RecieveTask (sendPackageSemaphore,recievePackageSemaphore, lista));

            sendPackage.start();
            receivePackage.start();

        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}