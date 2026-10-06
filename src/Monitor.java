public class Monitor implements Runnable {

    // Atributos y constructor
    private Descarga[] descargas;

    public Monitor(Descarga[] descargas){

        this.descargas = descargas;

    }


    //metodo run
    public void run(){


        // variable para tener el bucle funcionando mientras haya al menos 1 hilo
        boolean hilosVivos = true;

        while ( hilosVivos == true ){

            int activos = 0;
            hilosVivos = false;

            // recorre las descargas y devuelve true si el hilo sigue vivo
            for(int i = 0; i < descargas.length; i++ ){

                if (descargas[i].isAlive()){

                    activos++;
                    hilosVivos = true;
                }
            }

            //cuenta cuantas descargas estan sucediendo a la vez
            if (activos > 0 ) {
                System.out.println("[Monitor] Descargas en curso: " + activos);
            }

            //detiene el hilo monitor para que salte la alerta cada 500ms
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        // alerta para cuando ya no quede ningun hilo en el bucle
        System.out.println("[Monitor] No queda ninguna descarga en curso");


    }


}
