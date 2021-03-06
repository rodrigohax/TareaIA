package proyecto;

import inteligencia.*;
import java.awt.Graphics;
import static proyecto.Constantes.N_CELDAS_ALTO;
import static proyecto.Constantes.N_CELDAS_ANCHO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Jugador implements Constantes {

    public Laberinto laberinto;
    public Celda jugador, celdaMovimiento;
    public BusquedaAnchura inteligencia;
    public Carta carta;
    public int nCartas = NCARTAS;
    

    public Jugador(Laberinto laberinto) {
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(0, 0, laberinto.celdas[0][0].tipoCelda);
        jugador = new Celda(0, 0, JUGADOR);
        laberinto.celdas[jugador.x][jugador.y].tipoCelda = JUGADOR;
        inteligencia = new BusquedaAnchura(laberinto, this);
        carta = new Carta(laberinto);
    }

    void moverCelda(KeyEvent evento) {
        switch (evento.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Mover arriba");
                moverCeldaArriba();
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Mover abajo");
                moverCeldaAbajo();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Mover izquierda");
                moverCeldaIzquierda();
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Mover derecha");
                moverCeldaDerecha();
                break;
        }
    }

    public void moverCeldaArriba() {
        if (jugador.y > 0) {
            if (noHayPared(jugador.x, jugador.y - 1) && noVieneVehiculo(jugador.x, jugador.y, 'U')) {
                avanzar(jugador.x, jugador.y - 1, 'U');
            }
        }
    }

    public void moverCeldaAbajo() {
        if (jugador.y + 1 < N_CELDAS_ALTO) {
            if (noHayPared(jugador.x, jugador.y + 1) && noVieneVehiculo(jugador.x, jugador.y, 'D')) {
                avanzar(jugador.x, jugador.y + 1, 'D');
            }
        }
    }

    public void moverCeldaIzquierda() {
        if (jugador.x > 0) {
            if (noHayPared(jugador.x - 1, jugador.y) && noVieneVehiculo(jugador.x, jugador.y, 'L')) {
                avanzar(jugador.x - 1, jugador.y, 'L');
            }
        }
    }

    public void moverCeldaDerecha() {
        if (jugador.x + 1 < N_CELDAS_ANCHO) {
            if (noHayPared(jugador.x + 1, jugador.y) && noVieneVehiculo(jugador.x, jugador.y, 'R')) {
                avanzar(jugador.x + 1, jugador.y, 'R');
            }
        }
    }

    private boolean noHayPared(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != OBSTACULO
                && laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != PEATON;
    }

    private boolean noVieneVehiculo(int x, int y, char mov) {
        if (celdaMovimiento.tipoCelda == CAMINO) {
            System.out.println("Esta en camino");
            switch (mov) {
                case 'D':
                    if (x - 4 > 0 && x + 4 < N_CELDAS_ANCHO && y > 0 && y + 2 < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x][y + 1].tipoCelda == CALLE) {
                            return laberinto.celdas[x + 1][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 3][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 4][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 3][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 4][y + 2].tipoCelda != VEHICULO;
                        }
                    }
                case 'U':
                    if (x - 4 > 0 && x + 4 < N_CELDAS_ANCHO && y - 2 > 0 && y < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x][y - 1].tipoCelda == CALLE) {
                            return laberinto.celdas[x - 1][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 3][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 4][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 3][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 4][y - 2].tipoCelda != VEHICULO;
                        }
                    }
                case 'R':
                    if (x > 0 && x + 2 < N_CELDAS_ANCHO && y - 4 > 0 && y + 4 < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x + 1][y].tipoCelda == CALLE) {
                            return laberinto.celdas[x + 1][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 1][y - 4].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x + 2][y + 4].tipoCelda != VEHICULO;
                        }
                    }
                case 'L':
                    if (x - 2 > 0 && x < N_CELDAS_ANCHO && y - 4 > 0 && y + 4 < N_CELDAS_ALTO) {
                        if (laberinto.celdas[x - 1][y].tipoCelda == CALLE) {
                            return laberinto.celdas[x - 1][y + 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 1][y + 4].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 1].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 2].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 3].tipoCelda != VEHICULO
                                    && laberinto.celdas[x - 2][y - 4].tipoCelda != VEHICULO;
                        }
                    }
            }
        }
        return true;
    }

    private void avanzar(int x, int y, char mov) {
        char temp;
        System.out.println(x + " " + y);
        switch (mov) {
            case 'D':
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x][y - 1].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.y = jugador.y + 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 0;
                break;
            case 'U':
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x][y + 1].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.y = jugador.y - 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 2;
                break;
            case 'R':
                temp = celdaMovimiento.tipoCelda;

                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x - 1][y].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.x = jugador.x + 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 3;
                break;
            case 'L':
                temp = celdaMovimiento.tipoCelda;
                celdaMovimiento.tipoCelda = laberinto.celdas[x][y].tipoCelda;
                laberinto.celdas[x + 1][y].tipoCelda = temp;
                laberinto.celdas[x][y].tipoCelda = JUGADOR;
                jugador.x = jugador.x - 1;
                laberinto.celdas[jugador.x][jugador.y].indexSprite = 1;
                break;
        }
    }
}
