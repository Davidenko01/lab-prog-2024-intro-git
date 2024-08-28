public class Televisor {
    private int volumen;
    private int canal;

    public Televisor {
        volumen = 0;
        canal = 0;
    }

    public void apretarBotonSubirVolumen() {
        if (volumen <= 100) {
            volumen++;
            System.out.println("Volumen actual: "+volumen);
        } else {
            System.out.println("VOLUMEN AL MÁXIMO");
        }
    }

    public void apretarBotonBajarVolumen() {
        if (volumen > 0) {
            volumen--;
            System.out.println("Volumen actual: "+volumen);
        } else {
            System.out.println("TELEVISOR MUTEADO");
        }
    }

    public void subirCanal() {
        canal++;
        System.out.println("Canal actual: "+canal);
    }

    public void bajarCanal() {
        if (canal > 0) {
            canal--;
            System.out.println("Canal actual: "+canal);
        }
    }

    public void encender() {
        System.out.println("Se prendió el tele");
    }

    public void apagar() {
        System.out.println("Se apagó el tele");
    }
}