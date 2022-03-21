package graficacion_efren;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class myframe07 extends JFrame {
    // Utilice las teclas de flecha para manipular el círculo, xey se utilizan para determinar las coordenadas del círculo

    int x = 200;
    int y = 100;
    int radius = 15;
    myPane107 mp = new myPane107();

    public myframe07() {
        // TODO código auxiliar de constructor generado automáticamente
        this.add(mp);
        mp.setFocusable(true);
        this.setTitle("test1_7");
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        this.setVisible(true);
        event();
    }

    private void event() {
        // TODO código auxiliar de método generado automáticamente
        mp.addKeyListener(new KeyAdapter() {// Agregar un detector de eventos de teclado new KeyAdapter () {} es un adaptador de teclado o un detector

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO código auxiliar de método generado automáticamente
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    y -= 10; // getKeyCode () es el número de secuencia de la pulsación de la tecla, KeyEvent.VK_UP representa el número de secuencia de la tecla arriba (abajo)
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y += 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    x -= 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x += 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    
                }
                repaint();
            }

        });
        System.out.println("Fin");
    }

    class myPane107 extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // TODO código auxiliar de método generado automáticamente
            super.paintComponent(g);
            g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        }

    }

    public static void main(String[] args) {
        // TODO código auxiliar de método generado automáticamente
        new myframe07();
    }

}
