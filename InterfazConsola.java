package sieteymedia;
import java.util.Scanner;
import recursos.Carta;
import sieteymedia.SieteYMedia;

public class InterfazConsola {
    private Scanner sc;
    private SieteYMedia juego;

    public InterfazConsola() {
        sc = new Scanner(System.in);
        juego = new SieteYMedia();
        presentarJuego();
        jugar();
    }

    public static void main(String[] args) {
        new InterfazConsola();
    }

    private void presentarJuego() {
        System.out.println("Bienvenido al juego de las 7 y media");
        System.out.println("------------------------------------");
    }

    private void jugar() {
        turnoJugador();
        turnoBanca();
        System.out.println("Adios");
    }

    private void turnoJugador() {
        char opc = 'C';
        System.out.println("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");

        while (juego.valorCartas(juego.getCartasJugador()) < 7.5 && opc == 'C') {
            juego.turnoJugador();

            System.out.println("Éstas son tus cartas jugador:");
            mostrarCartas(juego.getCartasJugador());
            double valor = juego.valorCartas(juego.getCartasJugador());
            System.out.println("\n\tValor de cartas: " + valor);

            if (valor < 7.5) {
                System.out.println("\n¿Pides [C]arta o te [P]lantas?");
                opc = sc.next().trim().toUpperCase().charAt(0);
            }
        }
    }

    private void turnoBanca() {
        if (juego.jugadorSeHaPasado()) {
            System.out.println("Jugador, te has pasado en tu jugada anterior, gana la banca");
            return;
        }

        System.out.println("\n\nTurno de banca ...");
        juego.turnoBanca();

        System.out.println("Éstas son mis cartas:");
        mostrarCartas(juego.getCartasBanca());
        System.out.println("\nValor de mis cartas(banca): " + juego.valorCartas(juego.getCartasBanca()));

        if (juego.bancaSeHaPasado()) {
            System.out.println("me pasé, ganas tú, jugador");
        } else if (juego.bancaGana()) {
            System.out.println("Gana la banca");
        } else {
            System.out.println("Gana el jugador");
        }
    }

    private void mostrarCartas(Carta[] cartas) {
        int i = 0;
        while (cartas[i] != null) {
            System.out.print("\t" + cartas[i]);
            i++;
        }
    }
}