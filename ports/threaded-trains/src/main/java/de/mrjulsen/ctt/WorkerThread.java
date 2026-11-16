package de.mrjulsen.ctt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread {
   private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
   private final Thread worker;

   public WorkerThread(String name) {
      this.worker = new Thread(() -> {
         while (!Thread.currentThread().isInterrupted()) {
            try {
               Runnable task = this.taskQueue.take();
               task.run();
            } catch (InterruptedException var2) {
               Thread.currentThread().interrupt();
            }
         }
      }, name);
      this.worker.start();
   }

   public Future<?> submitTask(Runnable task) {
      CompletableFuture<Void> future = new CompletableFuture<>();
      this.taskQueue.offer(() -> {
         try {
            task.run();
            future.complete(null);
         } catch (Throwable var3) {
            future.completeExceptionally(var3);
         }
      });
      return future;
   }

   public void shutdown() {
      this.worker.interrupt();
   }
}
