public class Instalador implements Runnable{



    private Descarga[] descargas;

    public Instalador(Descarga[] descargas){

        this.descargas = descargas;

    }

    public void run(){

        for(int i = 0; i < descargas.length; i++){

            if (descargas[i].getNombreArchivo().equals("meditacion.mp4") || descargas[i].getNombreArchivo().equals("mantras.mp3") ){
                try {
                    descargas[i].join();
                } catch (InterruptedException e) {

                    System.out.println(e.getMessage());
                }


            }

        }

        System.out.println("[Instalador] Meditacion y mantras listos: instalando...");
        System.out.println("[Instalador] Instalación terminada");





    }
}
