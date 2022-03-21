package graficacion_efren;

public class Coordenada {

    private double x, y, x1, y1, x2, y2;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getX1() {
        return this.x1;
    }

    public double getY1() {
        return this.y1;
    }

    public double getX2() {
        return this.x2;
    }

    public double getY2() {
        return this.y2;
    }

    public void adaptaCoordenada(int escala, int maxX, int maxY) {
        this.x1 = (maxX / 2) + (this.x1 * escala);
        this.y1 = (maxY / 2) + (this.y1 * -escala);
        this.x2 = (maxX / 2) + (this.x2 * escala);
        this.y2 = (maxY / 2) + (this.y2 * -escala);
    }

    public void adapta2Puntos(int escala, int maxX, int maxY) {
        this.x = (maxX / 2) + (this.x * escala);
        this.y = (maxY / 2) + (this.y * -escala);
    }
}
