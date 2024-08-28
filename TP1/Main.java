import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // Crear el adaptee y el adapter
        ControlRemoto control = new ControlRemoto();
        Televisor miTV = new Televisor();
        TelevisorAdapter TVAdapter = new TelevisorAdapter(miTV);

        // Crear un ScheduledExecutorService con dos hilos
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Crear y programar las tareas de presión de botones
        Callable<Boolean> subirVol = () -> {
            control.subirVolumen();
            return true;
        };
        scheduler.schedule(subirVol, 5 , TimeUnit.SECONDS);
        Callable<Boolean> bajarVol = () -> {
            control.bajarVolumen();
            return true;
        };
        scheduler.schedule(bajarVol, 10 , TimeUnit.SECONDS);
        Callable<String> volumeUpTask2 = new ButtonPressTask(remoteControl, "volumeUp");
        Callable<String> channelUpTask = new ButtonPressTask(remoteControl, "channelUp");

        try {
            // Ejecutar dos tareas para subir el volumen al mismo tiempo
            scheduler.schedule(volumeUpTask1, 1, TimeUnit.SECONDS);
            scheduler.schedule(volumeUpTask2, 1, TimeUnit.SECONDS);

            // Ejecutar una tarea para cambiar de canal después de 2 segundos
            scheduler.schedule(channelUpTask, 2, TimeUnit.SECONDS);

            // Dormir el hilo principal para esperar que se ejecuten las tareas
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Apagar el scheduler
            scheduler.shutdown();
        }
    }
}
