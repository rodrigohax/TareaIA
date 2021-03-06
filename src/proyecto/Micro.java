package proyecto;

import java.awt.Point;
import java.util.TimerTask;

public class Micro extends TimerTask implements Constantes {

    public Laberinto laberinto;
    public Celda micro, celdaMovimiento;
    public Point p1, p2, p3, p4;
    public Peaton[] pasajeros;
    public int npeatones;

    public Micro(Laberinto laberinto, Point xp, Point yp) {
        p1 = new Point(xp.x, xp.y);
        p2 = new Point(yp.x, xp.y);
        p3 = new Point(yp.x, yp.y);
        p4 = new Point(xp.x, yp.y);
        this.laberinto = laberinto;
        celdaMovimiento = new Celda(p1.x, p1.y, laberinto.celdas[p1.x][p1.y].tipoCelda);
        micro = new Celda(celdaMovimiento.x, celdaMovimiento.y, MICRO);

        pasajeros = new Peaton[PEATONESMICRO];
        p1.x = micro.x - 1;
        for (int i = 0; i < PEATONESMICRO; i++) {
            pasajeros[i] = new Peaton(laberinto, p1, i);
            p1.x--;
        }
        laberinto.repaint();
    }

    private void moverVehiculo() {
        if (celdaMovimiento.x == p2.x && celdaMovimiento.y < p3.y && celdaMovimiento.y >= p2.y) {
            if (noHayPersona(celdaMovimiento.x, celdaMovimiento.y + 1)) {
                moverAbajo();
                for (int i = 0; i < PEATONESMICRO; i++) {
                    pasajeros[i].moverAbajo(celdaMovimiento);
                }
            }
        } else if (celdaMovimiento.x >= p1.x && celdaMovimiento.y == p1.y && celdaMovimiento.x < p2.x) {
            if (noHayPersona(celdaMovimiento.x + 1, celdaMovimiento.y)) {
                moverDerecha();
                for (int i = 0; i < PEATONESMICRO; i++) {
                    pasajeros[i].moverDerecha(celdaMovimiento);
                }
            }
        } else if (celdaMovimiento.x <= p3.x && celdaMovimiento.y == p3.y && celdaMovimiento.x > p4.x) {
            if (noHayPersona(celdaMovimiento.x - 1, celdaMovimiento.y)) {
                moverIzquierda();
                for (int i = 0; i < PEATONESMICRO; i++) {
                    pasajeros[i].moverIzquierda(celdaMovimiento);
                }
            }
        } else if (celdaMovimiento.x == p4.x && celdaMovimiento.y <= p4.y && celdaMovimiento.y >= p1.y) {
            if (noHayPersona(celdaMovimiento.x, celdaMovimiento.y - 1)) {
                moverArriba();
                for (int i = 0; i < PEATONESMICRO; i++) {
                    pasajeros[i].moverArriba(celdaMovimiento);
                }
            }
        }
    }

    private void moverAbajo() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y + 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        for (int i = 0; i < PEATONESMICRO; i++) {
            pasajeros[i].moverAbajo();
        }
    }

    private void moverArriba() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y - 1].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.y = celdaMovimiento.y - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        for (int i = 0; i < PEATONESMICRO; i++) {
            pasajeros[i].moverArriba();
        }
    }

    private void moverDerecha() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x + 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x + 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        for (int i = 0; i < PEATONESMICRO; i++) {
            pasajeros[i].moverDerecha();
        }
    }

    private void moverIzquierda() {
        char temp = celdaMovimiento.tipoCelda;
        celdaMovimiento.tipoCelda = laberinto.celdas[celdaMovimiento.x - 1][celdaMovimiento.y].tipoCelda;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = temp;
        celdaMovimiento.x = celdaMovimiento.x - 1;
        laberinto.celdas[celdaMovimiento.x][celdaMovimiento.y].tipoCelda = MICRO;
        for (int i = 0; i < PEATONESMICRO; i++) {
            pasajeros[i].moverIzquierda();
        }
    }

    @Override
    public void run() {
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
        moverVehiculo();
        laberinto.lienzoPadre.repaint();
        laberinto.lienzoPadre.validate();
    }

    private boolean noHayPersona(int x, int y) {
        return laberinto.celdas[x][y].tipoCelda != JUGADOR
                && laberinto.celdas[x][y].tipoCelda != PEATON
                && laberinto.celdas[x][y].tipoCelda != VEHICULO
                && laberinto.celdas[x][y].tipoCelda != MICRO;

    }
}
