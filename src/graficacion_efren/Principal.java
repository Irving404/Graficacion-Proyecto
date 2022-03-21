package graficacion_efren;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    private Coordenada yIni, yFin, xIni, xFin;
    private int maxX, maxY, escala;
    private List<JLabel> numerosXM, numerosXm, numerosYM, numerosYm;
    private HashMap<Integer, Color> colores;

    public Principal() {
        initComponents();
        obtenCoordenadas();
        estiloSpinner();
        panelPlano.setFocusable(true);
    }

    private void cleanTextField() {
        txtPuntoX.setText("");
        txtPuntoY.setText("");
        txtX1.setText("");
        txtX2.setText("");
        txtY1.setText("");
        txtY2.setText("");
    }

    private void estiloSpinner() {
        spEscala.getEditor().getComponent(0).setBackground(new java.awt.Color(255, 51, 51));
        spLimMax.getEditor().getComponent(0).setBackground(new java.awt.Color(255, 51, 51));
        spLimMin.getEditor().getComponent(0).setBackground(new java.awt.Color(255, 51, 51));

        spEscala.getEditor().getComponent(0).setForeground(Color.WHITE);
        spLimMax.getEditor().getComponent(0).setForeground(Color.WHITE);
        spLimMin.getEditor().getComponent(0).setForeground(Color.WHITE);

        colores = new HashMap<>();
        int i = 0;
        colores.put(i++, Color.BLACK);
        colores.put(i++, Color.RED);
        colores.put(i++, Color.BLUE);
        colores.put(i++, Color.WHITE);
        colores.put(i++, Color.GREEN);
        colores.put(i++, Color.YELLOW);
    }

    private void obtenCoordenadas() {
        this.maxX = panelPlano.getWidth();
        this.maxY = panelPlano.getHeight();
        int xMedia = (int) this.maxX / 2;
        int yMedia = (int) this.maxY / 2;

        yIni = new Coordenada(xMedia, 0);
        yFin = new Coordenada(xMedia, this.maxY);
        xIni = new Coordenada(0, yMedia);
        xFin = new Coordenada(this.maxX, yMedia);
    }

    private JLabel addLabel(String txt) { // crea etiqueta x para el plano
        JLabel texto = new JLabel();
        texto.setText(txt);
        texto.setVisible(false);
        return texto;
    }

    private void zoom() {
        ocultarEtiquetas();
        this.spEscala.setValue(this.escala);
        Pintar.pintarEjes(panelPlano.getGraphics(), xIni, xFin, yIni, yFin);
        this.escala = (int) spEscala.getValue();
        int lim = (maxX / 2) / escala;
        pintarEscala(lim);
    }

    private void ocultarEtiquetas() {
        numerosXM.forEach(x -> {
            x.setVisible(false);
        });
        numerosXm.forEach(x -> {
            x.setVisible(false);
        });
        numerosYM.forEach(x -> {
            x.setVisible(false);
        });
        numerosYm.forEach(x -> {
            x.setVisible(false);
        });
    }

    private void mostrarEtiquetas() {
        numerosXM.forEach(x -> {
            x.setVisible(true);
        });
        numerosXm.forEach(x -> {
            x.setVisible(true);
        });
        numerosYM.forEach(x -> {
            x.setVisible(true);
        });
        numerosYm.forEach(x -> {
            x.setVisible(true);
        });
    }

    private void pintarEscala(int lim) {
        numerosXM = new ArrayList<>();
        numerosXm = new ArrayList<>();
        numerosYM = new ArrayList<>();
        numerosYm = new ArrayList<>();
        JLabel texto = null;
        for (int i = 1; i <= lim; i++) {
            Pintar.pintaMarcas(panelPlano.getGraphics(), (maxX / 2) + (i * escala), (maxY / 2) - 5, (maxX / 2) + (i * escala), (maxY / 2) + 5, Color.BLACK);
            texto = addLabel(String.valueOf(i));
            numerosXM.add(texto);
            panelPlano.add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints((maxX / 2) + (i * escala), (maxY / 2) + 10, -1, -1));

            Pintar.pintaMarcas(panelPlano.getGraphics(), (maxX / 2) - (5), (maxY / 2) + (i * escala), (maxX / 2) + (5), (maxY / 2) + (i * escala), Color.BLACK);
            texto = addLabel(String.valueOf(-i));
            numerosYm.add(texto);
            panelPlano.add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints((maxX / 2) + (7), (maxY / 2) + (i * escala), -1, -1));
        }
        for (int i = -1; i >= -lim; i--) {
            Pintar.pintaMarcas(panelPlano.getGraphics(), (maxX / 2) + (i * escala), (maxY / 2) - 5, (maxX / 2) + (i * escala), (maxY / 2) + 5, Color.BLACK);
            texto = addLabel(String.valueOf(i));
            numerosXm.add(texto);
            panelPlano.add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints((maxX / 2) + (i * escala), (maxY / 2) + 10, -1, -1));

            Pintar.pintaMarcas(panelPlano.getGraphics(), (maxX / 2) - (5), (maxY / 2) + (i * escala), (maxX / 2) + (5), (maxY / 2) + (i * escala), Color.BLACK);
            texto = addLabel(String.valueOf(-i));
            numerosYM.add(texto);
            panelPlano.add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints((maxX / 2) + (10), (maxY / 2) + (i * escala), -1, -1));
        }
        mostrarEtiquetas();
        pack();
    }

    public void limpiaPanel() { // limpiar panel para cambiar la escala
        panelPlano = new javax.swing.JPanel();
        panelPlano.setBackground(new java.awt.Color(255, 204, 204));
        panelPlano.setPreferredSize(new java.awt.Dimension(700, 500));
        panelPlano.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(panelPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 790, 570));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelPlano = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtY2 = new javax.swing.JTextField();
        txtY1 = new javax.swing.JTextField();
        txtX2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        spLimMax = new javax.swing.JSpinner();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtX1 = new javax.swing.JTextField();
        spEscala = new javax.swing.JSpinner();
        spLimMin = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPuntoY = new javax.swing.JTextField();
        txtPuntoX = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtFuncion = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtIntervalo = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cbxColor = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taller de Graficacion");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPlano.setBackground(new java.awt.Color(255, 204, 204));
        panelPlano.setPreferredSize(new java.awt.Dimension(700, 500));
        panelPlano.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(panelPlano, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 790, 570));

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("MARCAR EJES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 380, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("--------------------------------------------------------------");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 400, -1));

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("PONER");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 130, 40));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("GRAFICAR FUNCION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 390, 40));

        txtY2.setBackground(new java.awt.Color(255, 51, 51));
        txtY2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtY2.setForeground(new java.awt.Color(255, 255, 255));
        txtY2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtY2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 40, 40));

        txtY1.setBackground(new java.awt.Color(255, 51, 51));
        txtY1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtY1.setForeground(new java.awt.Color(255, 255, 255));
        txtY1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtY1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 40, 40));

        txtX2.setBackground(new java.awt.Color(255, 51, 51));
        txtX2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtX2.setForeground(new java.awt.Color(255, 255, 255));
        txtX2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtX2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 40, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("X2");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("X1");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("GRAFICAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 100, 40));

        spLimMax.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spLimMax.setToolTipText("");
        spLimMax.setOpaque(false);
        jPanel3.add(spLimMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 50, 30));

        jButton5.setBackground(new java.awt.Color(153, 153, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("QUITAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 130, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("----- GRAFICAR FUNSION -----");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 240, 20));

        txtX1.setBackground(new java.awt.Color(255, 51, 51));
        txtX1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtX1.setForeground(new java.awt.Color(255, 255, 255));
        txtX1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 40, 40));

        spEscala.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spEscala.setToolTipText("");
        spEscala.setOpaque(false);
        jPanel3.add(spEscala, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 50, 30));

        spLimMin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spLimMin.setToolTipText("");
        spLimMin.setOpaque(false);
        jPanel3.add(spLimMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 50, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("color");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 60, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("LIM MIN");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("------- GRAFICAR LINEA -------");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 220, 20));

        jButton6.setBackground(new java.awt.Color(153, 153, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("GRAFICAR PUNTO");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 180, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Y1");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setText("(");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Y2");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setText(",");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, 50));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setText(")");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, 50));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setText("(");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, 50));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel14.setText(")");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, 50));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel15.setText(",");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, 50));

        txtPuntoY.setBackground(new java.awt.Color(255, 51, 51));
        txtPuntoY.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPuntoY.setForeground(new java.awt.Color(255, 255, 255));
        txtPuntoY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtPuntoY, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 40, 40));

        txtPuntoX.setBackground(new java.awt.Color(255, 51, 51));
        txtPuntoX.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPuntoX.setForeground(new java.awt.Color(255, 255, 255));
        txtPuntoX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(txtPuntoX, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 40, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("ZOOM");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 70, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setText(")");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, 50));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel18.setText("(");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, 50));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel19.setText(",");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, 50));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("X");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 20, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Y");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 20, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("GRAFICACION");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 170, 20));

        txtFuncion.setBackground(new java.awt.Color(255, 51, 51));
        txtFuncion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFuncion.setForeground(new java.awt.Color(255, 255, 255));
        txtFuncion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuncionActionPerformed(evt);
            }
        });
        jPanel3.add(txtFuncion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 170, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("LIM MAX");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        txtIntervalo.setBackground(new java.awt.Color(255, 51, 51));
        txtIntervalo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIntervalo.setForeground(new java.awt.Color(255, 255, 255));
        txtIntervalo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIntervaloActionPerformed(evt);
            }
        });
        jPanel3.add(txtIntervalo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 50, 40));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("INTERV");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 60, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("------- GRAFICAR PUNTO -------");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 270, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("F(X)");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 60, -1));

        cbxColor.setBackground(new java.awt.Color(255, 51, 51));
        cbxColor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxColor.setForeground(new java.awt.Color(255, 255, 255));
        cbxColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NEGRO", "ROJO", "AZUL", "BLANCO", "VERDE", "AMARILLO" }));
        jPanel3.add(cbxColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 90, 30));

        jButton7.setBackground(new java.awt.Color(153, 153, 153));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton7.setText("+");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 50, 40));

        jButton8.setBackground(new java.awt.Color(153, 153, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setText("-");
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 50, 40));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("ESCALA");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 70, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 430, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1271, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Agrega ejes
        Pintar.pintarEjes(panelPlano.getGraphics(), xIni, xFin, yIni, yFin);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // marcar escala
//        limpiaPanel();
        Pintar.pintarEjes(panelPlano.getGraphics(), xIni, xFin, yIni, yFin);
        this.escala = (int) spEscala.getValue();
        int lim = (maxX / 2) / escala;
        pintarEscala(lim);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // pinta funcion
        int color = cbxColor.getSelectedIndex();
        int limMin = (int) spLimMin.getValue();
        int limMax = (int) spLimMax.getValue();
        double intervalo = Double.parseDouble(txtIntervalo.getText());
        String funcion = txtFuncion.getText();
        List<Coordenada> funsion = new Operaciones().calculaFuncion(funcion, limMin, limMax, intervalo);
        if (funsion != null) {
            Pintar.pintarFuncion(panelPlano.getGraphics(), funsion, escala, maxX, maxY, colores.get(color));
            cleanTextField();
        }else{
            JOptionPane.showMessageDialog(panelPlano, "La funcion es incorrecta\nFavor de revisarla y corregirla");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // quitar numeros
        ocultarEtiquetas();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // pintar linea
        int color = cbxColor.getSelectedIndex();
        Coordenada cord = new Coordenada(Integer.parseInt(txtX1.getText()), Integer.parseInt(txtY1.getText()), Integer.parseInt(txtX2.getText()), Integer.parseInt(txtY2.getText()));
        cord.adaptaCoordenada(escala, maxX, maxY);
        Pintar.pintaLinea(panelPlano.getGraphics(), cord.getX1(), cord.getY1(), cord.getX2(), cord.getY2(), colores.get(color));
        cleanTextField();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Pintar un solo punto
        int color = cbxColor.getSelectedIndex();
        double pX = Double.parseDouble(txtPuntoX.getText());
        double pY = Double.parseDouble(txtPuntoY.getText());
        Coordenada cord = new Coordenada(pX, pY);
        cord.adapta2Puntos(escala, maxX, maxY);
        Pintar.pintarPunto(panelPlano.getGraphics(), cord.getX(), cord.getY(), colores.get(color));
        cleanTextField();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuncionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionActionPerformed

    private void txtIntervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIntervaloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIntervaloActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //ZOOM MENOS
        if (this.escala > 10) {
            this.escala -= 10;
            zoom();
        } else {
            JOptionPane.showMessageDialog(panelPlano, "La escala ya es muy pequeña");
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // ZOOM MAS
        this.escala += 10;
        zoom();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxColor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelPlano;
    private javax.swing.JSpinner spEscala;
    private javax.swing.JSpinner spLimMax;
    private javax.swing.JSpinner spLimMin;
    private javax.swing.JTextField txtFuncion;
    private javax.swing.JTextField txtIntervalo;
    private javax.swing.JTextField txtPuntoX;
    private javax.swing.JTextField txtPuntoY;
    private javax.swing.JTextField txtX1;
    private javax.swing.JTextField txtX2;
    private javax.swing.JTextField txtY1;
    private javax.swing.JTextField txtY2;
    // End of variables declaration//GEN-END:variables
}
