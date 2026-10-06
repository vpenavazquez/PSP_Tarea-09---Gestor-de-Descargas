public class GestorDescargas {

    public static void main(String[] args) {

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


        Descarga[] descargas = new Descarga[archivos.length];


        long inicio = System.currentTimeMillis();
        long sumaTiempos = 0;


        for (int i = 0; i < archivos.length; i++) {
            descargas[i] = new Descarga(archivos[i]);
            descargas[i].setName("Descarga-" + archivos[i]);

            descargas[i].start();

        }

        Monitor monitor = new Monitor(descargas);

        Thread hiloMon = new Thread(monitor);

        hiloMon.start();

        Instalador instalador = new Instalador(descargas);

        Thread hiloIns = new Thread(instalador);

        hiloIns.start();

        for (int i = 0; i < descargas.length; i++){

            if (descargas[i].getNombreArchivo().equals("meditacion.mp4")){

                try{
                    descargas[i].join(3000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

                if(descargas[i].isAlive() == true){
                    System.out.println("[Main] meditacion.mp4 sigue en segundo plano");
                }

            }
        }


        for (int i = 0; i < descargas.length; i++) {
            try {
                descargas[i].join();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }


             sumaTiempos += descargas[i].getTiempoTotal();
        }


        long tiempoReal = System.currentTimeMillis() - inicio;

        System.out.println("Todas las descargas han terminado.");
        System.out.println("Tiempo real: " + tiempoReal + " ms");
        System.out.println("Si se hubieran descargado una detrás de otra: " + sumaTiempos + " ms");
    }
}