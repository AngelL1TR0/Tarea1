package org.example;

import java.util.List;
import java.util.concurrent.Semaphore;

public class RecieveTask implements Runnable {
    private Semaphore sendPackageSemaphore;

    private Semaphore recievePackageSemaphore;

    private List lista;

    public RecieveTask(Semaphore sendPackageSemaphore, Semaphore recievePackageSemaphore, List lista) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.recievePackageSemaphore = recievePackageSemaphore;
        this.lista = lista;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                if (lista.isEmpty()) {
                    recievePackageSemaphore.acquire();

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
