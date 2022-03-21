package graficacion_efren;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Pintar {

    public static void pintaLinea(Graphics g, double x0, double y0, double x1, double y1, Color color) {
                
        g.setColor(color);
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
        pintarPunto(g, (int) x0, (int) y0,color);
        pintarPunto(g, (int) x1, (int) y1,color);
    }

    public static void pintaMarcas(Graphics g, double x0, double y0, double x1, double y1, Color color) {
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
        g.setColor(color);;
    }

    public static void pintarEjes(Graphics g, Coordenada xIni, Coordenada xFin, Coordenada yIni, Coordenada yFin) {
        pintaLinea(g, (int) xIni.getX(), (int) xIni.getY(), (int) xFin.getX(), (int) xFin.getY(), Color.BLACK);
        pintaLinea(g, (int) yIni.getX(), (int) yIni.getY(), (int) yFin.getX(), (int) yFin.getY(), Color.BLACK);
    }

    public static void pintarFuncion(Graphics g, List<Coordenada> funsion, int escala, int maxX, int maxY,Color color) {
//        System.out.println("-----------coordenadas adaptadas---------------");
        funsion.forEach(linea -> {
            linea.adaptaCoordenada(escala, maxX, maxY);
//            System.out.println(" x1= " + linea.getX1() + " y1= " + linea.getY1() + " x2= " + linea.getX2() + " y2= " + linea.getY2());
            pintaLinea(g, (int) linea.getX1(), (int) linea.getY1(), (int) linea.getX2(), (int) linea.getY2(), color);
            boolean r = true;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        });
        System.out.println("Fin parabola");
    }

    public static void pintarPunto(Graphics g, double x, double y,Color color) {
        g.drawRect((int) x - 1, (int) y - 1, 3, 3);
        g.setColor(color);
    }
}
