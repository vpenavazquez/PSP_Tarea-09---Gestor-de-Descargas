import java.util.concurrent.ThreadLocalRandom;

public class Descarga extends Thread {

    //Atributos y constructor

    private String NombreArchivo;
    private int TiempoBloque;
    private long TiempoTotal;

    public Descarga(String NombreArchivo){

        this.NombreArchivo = NombreArchivo;
        //Alazar de cuanto tarda cada bloque
        this.TiempoBloque = ThreadLocalRandom.current().nextInt(100,501);

    }

    // Getters
    public long getTiempoTotal(){
        return TiempoTotal;
    }

    public String getNombreArchivo(){return NombreArchivo;}

    // metodo run
    public void run() {
        //gurdada la hora en ms cuando arranca
        long inicio = System.currentTimeMillis();

        //for para simular bloques de descarga haciendo que i llegue hasta 10
        for (int i = 1; i <= 10; i++) {

            //para el hilo un tiempo segun el numero aleatorio que salga en TiempoBloque
            try {
                Thread.sleep(TiempoBloque);
            } catch (InterruptedException e) {
                System.out.println(NombreArchivo + "Descarga parada");
                return;
            }
            //print del porcentaje de cada bloque
            int porcentaje = i * 10;
            System.out.println(NombreArchivo + " / " + porcentaje + " % ");
        }
        // calculo Tiempo total restando el tiempo de inicio al tiempo total de la ejecucion
        this.TiempoTotal = System.currentTimeMillis() - inicio;
        System.out.println(NombreArchivo + " " + "completado en " + this.TiempoTotal + " ms ");
    }


}
