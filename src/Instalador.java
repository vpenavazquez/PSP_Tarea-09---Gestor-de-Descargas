public class Instalador implements Runnable{


    // atributos y constructor
    private Descarga[] descargas;

    public Instalador(Descarga[] descargas){

        this.descargas = descargas;

    }

    //metodo run
    public void run(){
        //recorre descargas
        for(int i = 0; i < descargas.length; i++){

            //busca con equals alguna descarga que corresponda a esos nombres
            if (descargas[i].getNombreArchivo().equals("meditacion.mp4") || descargas[i].getNombreArchivo().equals("mantras.mp3") ){

                //detiene el instalador hasta que el hilo de descarga termine
                try {
                    descargas[i].join();
                } catch (InterruptedException e) {

                    System.out.println(e.getMessage());
                }


            }

        }

        // se ejecuta cuando for acaba
        System.out.println("[Instalador] Meditacion y mantras listos: instalando...");
        System.out.println("[Instalador] Instalación terminada");





    }
}
