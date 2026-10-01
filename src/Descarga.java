import java.util.Random;

public class Descarga  extends Thread{

    private String nombreArchivo;
    private int tiempoBloque;
    private long tiempoTotal;

    public Descarga(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
        Random random = new Random();
        this.tiempoBloque = 100 + random.nextInt(401);

    }



    @Override
    public  void run(){
        long tiempoInicio = System.currentTimeMillis();

        for (int i = 1; i <= 10; i++) {
         try{
             Thread.sleep(tiempoBloque);
         } catch (InterruptedException e) {
            System.out.println(nombreArchivo + "Descarga parada");
            return;
         }
            int porcentaje = i * 10;
            System.out.println(nombreArchivo + porcentaje + "%");
        }
        long tiempoFin = System.currentTimeMillis();
        this.tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println(nombreArchivo + "completado en " + this.tiempoTotal + "ms");
    }

    public long getTiempoTotal(){
        return tiempoTotal;
    }
}
