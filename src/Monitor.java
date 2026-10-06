public class Monitor implements Runnable {


    private Descarga[] descargas;

    public Monitor(Descarga[] descargas){

        this.descargas = descargas;

    }

    public void run(){

        boolean hilosVivos = true;

        while ( hilosVivos == true ){

            int activos = 0;
            hilosVivos = false;

            for(int i = 0; i < descargas.length; i++ ){

                if (descargas[i].isAlive()){

                    activos++;
                    hilosVivos = true;
                }
            }

            if (activos > 0 ) {
                System.out.println("[Monitor] Descargas en curso: " + activos);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("[Monitor] No queda ninguna descarga en curso");


    }


}
