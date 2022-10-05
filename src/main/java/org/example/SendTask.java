package org.example;

import java.util.List;
import java.util.concurrent.Semaphore;

public class SendTask implements Runnable {
    private Semaphore sendPackageSemaphore;

    private Semaphore recievePackageSemaphore;

    private List lista;

    public SendTask(Semaphore sendPackageSemaphore, Semaphore recievePackageSemaphore, List lista) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.recievePackageSemaphore = recievePackageSemaphore;
        this.lista = lista;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            try {
                if (sendPackageSemaphore.equals(3)){
                    sendPackageSemaphore.acquire();
                    System.out.println("Enviado paquete numero " + i);
                    recievePackageSemaphore.release();
                }
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}
