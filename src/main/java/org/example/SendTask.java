package org.example;

import java.util.List;
import java.util.concurrent.Semaphore;

public class SendTask implements Runnable {
    private Semaphore sendPackageSemaphore;

    private Semaphore recievePackageSemaphore;

    private List<Integer> packages;

    public SendTask(Semaphore sendPackageSemaphore, Semaphore recievePackageSemaphore, List<Integer> packages) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.recievePackageSemaphore = recievePackageSemaphore;
        this.packages = packages;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 100 ; i++) {
            try {
                    recievePackageSemaphore.acquire();
                    System.out.println("Enviado paquete numero " + i);
                    packages.add(i);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            sendPackageSemaphore.release();
        }
    }
}
