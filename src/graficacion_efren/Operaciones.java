package graficacion_efren;

import java.util.ArrayList;
import java.util.List;

public class Operaciones {

    public List<Coordenada> calculaFuncion(String cadena, int limMin, int limMax, double intervalo) {
        AnalizadorSintactico f = new AnalizadorSintactico(cadena + "_", limMin, limMax, intervalo);
        f.first();
        List<Double> resultados = f.getResultados();

        List<Coordenada> resultadosFinal = new ArrayList<>();
        double auxX = 0, auxY = 0;
        int cont = 0;
        if (resultados != null) {
            for (double i = limMin; i < limMax;) {
                double operacion = resultados.get(cont);
                if (resultadosFinal.isEmpty()) {
                    auxX = i;
                    auxY = operacion;
                    resultadosFinal.add(new Coordenada(auxX, auxY, i, operacion));
                    i += intervalo;
                    cont++;
                    operacion = resultados.get(cont);
                    resultadosFinal.add(new Coordenada(auxX, auxY, i, operacion));
                    auxX = i;
                    auxY = operacion;
                } else {
                    resultadosFinal.add(new Coordenada(auxX, auxY, i, operacion));
//                    System.out.println(" x1= " + auxX + " y1= " + auxY + " x2= " + i + " y2= " + operacion);
                    auxX = i;
                    auxY = operacion;
                }

                i += intervalo;
                cont++;
            }
        }else{
            resultadosFinal = null;
        }
        return resultadosFinal;
    }
}
