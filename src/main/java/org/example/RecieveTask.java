package org.example;

import java.util.List;
import java.util.concurrent.Semaphore;

public class RecieveTask implements Runnable {
    private Semaphore sendPackageSemaphore;

    private Semaphore recievePackageSemaphore;

    private List<Integer> packages;

    public RecieveTask(Semaphore sendPackageSemaphore, Semaphore recievePackageSemaphore, List<Integer> packages) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.recievePackageSemaphore = recievePackageSemaphore;
        this.packages = packages;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 100; i++) {
            try {
               sendPackageSemaphore.acquire();
               int num = packages.remove(0);
               System.out.println("Recibido paquete " + num);
               Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            recievePackageSemaphore.release();
        }
    }
}
