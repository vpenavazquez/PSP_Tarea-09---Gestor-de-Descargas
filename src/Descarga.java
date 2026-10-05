import java.util.concurrent.ThreadLocalRandom;

public class Descarga extends Thread {


    private String NombreArchivo;
    private int TiempoBloque;
    private long TiempoTotal;

    public Descarga(String NombreArchivo){

        this.NombreArchivo = NombreArchivo;
        this.TiempoBloque = ThreadLocalRandom.current().nextInt(100,501);

    }

    public long getTiempoTotal(){
        return TiempoTotal;
    }

    public void run() {
        long inicio = System.currentTimeMillis();
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(TiempoBloque);
            } catch (InterruptedException e) {
                System.out.println(NombreArchivo + "Descarga parada");
                return;
            }

            int porcentaje = i * 10;
            System.out.println(NombreArchivo + porcentaje + "%");
        }

        this.TiempoTotal = System.currentTimeMillis() - inicio;
        System.out.println(NombreArchivo + "completado en " + this.TiempoTotal + "ms");
    }


}
