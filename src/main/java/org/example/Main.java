package org.example;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        try {
            List<Integer> packages = new Vector<>();

            Semaphore sendPackageSemaphore = new Semaphore(3);
            Semaphore recievePackageSemaphore = new Semaphore(3);

            sendPackageSemaphore.acquire(3);

            Thread sendPackage = new Thread(new SendTask (sendPackageSemaphore,recievePackageSemaphore, packages));
            Thread receivePackage = new Thread( new RecieveTask (sendPackageSemaphore,recievePackageSemaphore, packages));

            sendPackage.start();
            receivePackage.start();

            receivePackage.join();

        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("Programa terminado");
    }
}