package proyecto;

import java.awt.Graphics;
import javax.swing.JComponent;

public class Laberinto extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Lienzo lienzoPadre;
    public Jugador jugador;

    public Laberinto(Lienzo lienzoPadre) {
        this.lienzoPadre = lienzoPadre;
        celdas = new Celda[N_CELDAS_ANCHO][N_CELDAS_ALTO];
        //inicializar el array de celdas
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j] = new Celda(i + (i * TAMANIO_CELDA), j + (j * TAMANIO_CELDA), CAMINO);
            }
        }
        //pongo unos edificios
        crearEdificio(1, 2, 2, 3, false); //obs
        crearEdificio(2, 8, 0, 3, true); //edificio0
        crearEdificio(12, 20, 0, 3, true); //edificio1
        crearEdificio(24, 32, 0, 3, true); //edificio2
        crearEdificio(4, 8, 7, 19, true); //construccion y edificio abajo
        crearEdificio(12, 20, 7, 10, true); //edificio3
        crearEdificio(24, 32, 7, 10, true); //edificio4
        crearEdificio(4, 5, 19, 20, false); //grifo
        crearEdificio(12, 20, 14, 16, true); //edificio5
        crearEdificio(24, 32, 14, 16, true); //edificio6
        crearEdificio(24, 32, 14, 16, false); //obs
        crearEdificio(12, 20, 20, 22, true); //edificio7
        crearEdificio(24, 32, 20, 22, true); //edificio8
        crearEdificio(36, 40, 14, 22, true); //edificio rejas
        crearEdificio(40, 41, 14, 18, true);  //puerta edificio rejas

        crearEdificio(36, 37, 9, 10, false); //obs
        crearEdificio(37, 38, 7, 9, false); //estatua
        crearEdificio(40, 41, 8, 9, false); //carro
        crearEdificio(36, 37, 0, 2, false); //banca
        crearEdificio(39, 41, 0, 2, false); //fuente

        crearCalle(0, 41, 4, 6);
        crearCalle(9, 41, 11, 13);
        crearCalle(9, 35, 17, 19);
        crearCalle(1, 11, 21, 23);
        crearCalle(35, 36, 4, 6);
        crearCalle(1, 3, 6, 21);
        crearCalle(9, 11, 0, 21);
        crearCalle(33, 35, 0, 23);
        crearCalle(21, 23, 0, 23);

        celdas[5][2].tipoCelda = PORTAL; //correo
        celdas[6][18].tipoCelda = PORTAL;
        celdas[38][9].tipoCelda = PEATON;
        celdas[13][2].tipoCelda = PORTAL;
        celdas[16][2].tipoCelda = PORTAL;
        celdas[25][2].tipoCelda = PORTAL;
        celdas[28][2].tipoCelda = PORTAL;
        celdas[15][9].tipoCelda = PORTAL;
        celdas[25][9].tipoCelda = PORTAL;
        celdas[28][9].tipoCelda = PORTAL;
        celdas[15][15].tipoCelda = PORTAL;
        celdas[18][15].tipoCelda = PORTAL;
        celdas[27][15].tipoCelda = PORTAL;
        celdas[30][15].tipoCelda = PORTAL;
        celdas[13][21].tipoCelda = PORTAL;
        celdas[16][21].tipoCelda = PORTAL;
        celdas[28][21].tipoCelda = PORTAL;
        celdas[40][17].tipoCelda = PORTAL;
        
    }

    @Override
    public void update(Graphics g) {
        for (int i = 0; i < N_CELDAS_ANCHO; i++) {
            for (int j = 0; j < N_CELDAS_ALTO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    private void crearEdificio(int x1, int x2, int y1, int y2, boolean cuadra) {
        int random = (int)Math.floor(Math.random()*(101));
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = OBSTACULO;
                if(cuadra)
                    celdas[i][j].npeatones = random;
            }
        }
    }

    private void crearCalle(int x1, int x2, int y1, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                celdas[i][j].tipoCelda = CALLE;
            }
        }
    }
}
