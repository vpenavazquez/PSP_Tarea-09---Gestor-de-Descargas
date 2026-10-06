public class GestorDescargas {

    //metodo main
    public static void main(String[] args) {

        //comprueba si el usuario le ha pasado nombre de archivos al ejecutar por terminal si no crea el array con los 4 archivo especificados
        String[] archivos;

        if (args.length > 0) {
            archivos = args;
        } else {
            archivos = new String[] {
                    "cuarzos.png",
                    "meditacion.mp4",
                    "mantras.mp3",
                    "horoscopo.pdf"
            };
        }

        //array para guardar las referencias de los hilos de descarga
        Descarga[] descargas = new Descarga[archivos.length];

        //guarda la hora exacta del inicio de la descarga
        long inicio = System.currentTimeMillis();
        long sumaTiempos = 0;

        //recorre el array de archivos
        for (int i = 0; i < archivos.length; i++) {

            //instancia cada hilo
            descargas[i] = new Descarga(archivos[i]);
            //asignar nombre cada hilo
            descargas[i].setName("Descarga-" + archivos[i]);

            //solicita un nuevo hilo de ejecucion y llama .run() de la clase Descarga
            descargas[i].start();

        }

        //Lanzamiento del hilo de la clase Monitor
        Monitor monitor = new Monitor(descargas);

        Thread hiloMon = new Thread(monitor);

        hiloMon.start();


        //Lanzamiento del hilo de la Instalador
        Instalador instalador = new Instalador(descargas);

        Thread hiloIns = new Thread(instalador);

        hiloIns.start();

        //bucle para recorrer descargas
        for (int i = 0; i < descargas.length; i++){

            //buscamos meditacion.mp4
            if (descargas[i].getNombreArchivo().equals("meditacion.mp4")){

                //pausa del hilo durante 3000ms
                try{
                    descargas[i].join(3000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

                //pasados los 3000ms comprueba si el hilo esta vivo con .isAlive() y printea
                if(descargas[i].isAlive() == true){
                    System.out.println("[Main] meditacion.mp4 sigue en segundo plano");
                }

            }
        }

        //bucle para recorrer descargas y sumar el tiempo total
        for (int i = 0; i < descargas.length; i++) {

            //obliga a main a esperar a que todas las descargas hayan acabado
            try {
                descargas[i].join();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            //consulta la duracion individual con .getTiempototal() y la acumula en sumaTiempos
             sumaTiempos += descargas[i].getTiempoTotal();
        }

        //calculo de tiempo real
        long tiempoReal = System.currentTimeMillis() - inicio;

        System.out.println("Todas las descargas han terminado.");
        System.out.println("Tiempo real: " + tiempoReal + " ms");
        System.out.println("Suma de todos los tiempos: " + sumaTiempos + " ms");
    }
}