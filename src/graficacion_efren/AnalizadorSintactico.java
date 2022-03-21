package graficacion_efren;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

public class AnalizadorSintactico {

    private char token, variable = '.';
    private int i;
    private final double intervalo;
    private double lMin;
    private boolean error = false;
    private final double lMax;
    private final String cadena;
    private final HashMap<Character, Double> variables = new HashMap<>();
    private List<Double> resultados = new ArrayList<>();

    public List<Double> getResultados() {
        return this.resultados;
    }

    public AnalizadorSintactico(String cadena, double lMin, double lMax, double intervalo) {
        this.cadena = cadena.toLowerCase().replaceAll(" ", "");
        this.lMin = lMin;
        this.lMax = lMax;
        this.intervalo = intervalo;
    }

    private void error(char tokenEsperado) {
        if (!error) {
            System.out.println("caracter incorrecto → [" + tokenEsperado + "]");
            System.out.println("indice incorrecto → [" + i + "]");
            
        }
        this.error = true;
    }

    private void match(char tokenEsperado) {

        if (tokenEsperado == this.token) {
            getChar();
        } else {
            error(tokenEsperado);
        }
    }

    private void getChar() {
        System.out.println(this.i);
        this.i++;
        this.token = this.cadena.charAt(this.i);
    }

    private boolean isNumero(char caracter) {
        return caracter >= 48 && caracter <= 57;

    }

    private boolean isLetra(char caracter) {
        return caracter >= 97 && caracter <= 122;
    }

    private void cicloFuncion() {
        while (this.lMin <= this.lMax) {
            System.out.println("x = " + this.lMin);
            if (this.variables.isEmpty()) {
                this.variables.put(this.token, this.lMin);
            } else {
                this.variables.replace(this.variable, this.lMin);
            }
            first();
            this.lMin += intervalo;
        }
    }

    private double isVariable() {
        System.out.println("Entra es variable");
        String temp = "1";
        double tempV = 0;
        while (isLetra(this.token)) {
            if (this.variables.containsKey(this.token)) {
                tempV = this.variables.get((char) this.token);
            } else {
                if (this.variable == '.') {
                    int iAux = this.i;
                    char tAux = this.token;
                    this.variable = this.token;
                    cicloFuncion();
                    this.i = iAux;
                    this.token = tAux;
                } else {
                    error(this.variable);
                    System.exit(0);
                }
            }
            double temA = tempV * Double.parseDouble(temp);
            temp = String.valueOf(temA);
            this.i++;
            this.token = this.cadena.charAt(this.i);
        }
        this.i--;
        this.token = this.cadena.charAt(this.i);
        return Double.parseDouble(temp);
    }

    private double funsion() {
        double temp = 0.0;
        if (this.token != 'x') {
            String fun = "";
            while (isLetra(this.token)) {
                fun += "" + this.token;
                this.i++;
                this.token = this.cadena.charAt(this.i);
            }
            if (fun.equals("cos")) {
                System.out.println("es coseno");
                match('(');
                temp = exp();
                temp = Math.cos(temp);
                System.out.println("result " + temp);
//                match(')');
            } else if (fun.equals("sin")) {
                System.out.println("Es seno");
                match('(');
                temp = exp();
                temp = Math.sin(temp);
//                match(')');
            } else {
                error(this.token);
            }
        } else {
            temp = isVariable();
        }
        return temp;
    }

    private double SCANF() {
        String temp = "";
        while (isNumero(this.token)) {
            temp += this.token;
            this.i++;
            this.token = this.cadena.charAt(this.i);
            
        }
        while (isLetra(this.token)) {
            double tempV = 0;
            if (this.variables.containsKey(this.token)) {
                tempV = this.variables.get((char) this.token);
            } else {
                if (this.variable == '.') {
                    this.variable = this.token;
                    int iAux = this.i;
                    char tAux = this.token;
                    cicloFuncion();
                    this.i = iAux;
                    this.token = tAux;
                    return 0.0;
                } else {
                    System.out.println("error de scanf");
                    error(this.variable);
                    System.exit(0);
                }
            }
            double tempA = tempV * Double.parseDouble(temp);
            temp = String.valueOf(tempA);
            this.i++;
            this.token = this.cadena.charAt(this.i);
        }
        this.i--;
        this.token = this.cadena.charAt(this.i);
        return Double.parseDouble(temp);
    }

    private double factor() {
        double temp = 0;
        if (this.token == '(') {
            match('(');
            temp = exp();
            match(')');
        } else if (isNumero(this.token)) {
            temp = SCANF();
            getChar();
        } else if (isLetra(this.token)) {
            temp = funsion();
            getChar();
        } else {
            error(this.token);
        }
        return temp;
    }

    private double term() {
        double temp = factor();
        while (this.token == '*' || this.token == '/') {
            if (this.token == '*') {
                match('*');
                temp *= term();
            } else {
                match('/');
                double resp = factor();
                if (resp != 0) {
                    temp = temp / resp;
                } else {
                    temp = 0;
                }
            }
        }
        return temp;
    }

    private double exp() {
        double temp = term();
        while (this.token == '+' || this.token == '-') {
            if (this.token == '+') {
                match('+');
                temp += term();
            } else {
                match('-');
                temp -= term();
            }
        }
        return temp;
    }

    public void first() {
        this.i = 0;
        double result = 0;
        this.token = this.cadena.charAt(this.i);
        result = exp();
        if (this.token == '_') {
            if (!error) {
                this.resultados.add(result);
            }
        } else {
            error(this.token);
        }
        if (error) {
            this.resultados = null;
        }
    }
}
