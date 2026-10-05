public class gestorDescargas {

    public static void main(String[] args){

        Descarga d1 = new Descarga("cuarzos.png");
        Descarga d2 = new Descarga("meditacion.mp4");
        Descarga d3 = new Descarga("horoscopo.pdf");
        Descarga d4 = new Descarga("mantras.mp3");

        d1.setName("Descarga-cuarzos.png");
        d2.setName("Descarga-meditacion.mp4");
        d3.setName("Descarga-horoscopo.pdf");
        d4.setName("Descarga-mantras.mp3");

        long inicio = System.currentTimeMillis();



        d1.start();
        d2.start();
        d3.start();
        d4.start();

        try{
            d1.join();
            d2.join();
            d3.join();
            d4.join();

        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        long tiempoReal = System.currentTimeMillis() - inicio;
        long sumaTiempos = d1.getTiempoTotal() + d2.getTiempoTotal() + d3.getTiempoTotal() + d4.getTiempoTotal();

        System.out.println("Todas las descargas han terminado.");
        System.out.println("Tiempo real: " + tiempoReal + " ms");
        System.out.println("Si se hubieran descargado una detrás de otra: " + sumaTiempos + " ms");



    }
}
