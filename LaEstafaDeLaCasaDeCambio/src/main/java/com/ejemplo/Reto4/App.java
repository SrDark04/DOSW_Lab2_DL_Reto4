package com.ejemplo.Reto4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    private static Scanner scn = new Scanner(System.in);
    private static CurrencyHandler usd;
    private static CurrencyHandler eur;
    private static CurrencyHandler cop;
    private static CurrencyHandler jpy;

    public static void main(String[] args) {
        CurrencyFactory factory = new DefaultCurrencyFactory();
        usd = factory.createUSDHandler();
        eur = factory.createEURHandler();
        cop = factory.createCOPHandler();
        jpy = factory.createJPYHandler();

        usd.setNext(eur);
        eur.setNext(cop);
        cop.setNext(jpy);

        inicioTiendaCambio();
    }

    public static void inicioTiendaCambio() {
        System.out.print("Bienvenido a la tienda de cambio de divisas.\nIngrese la cantidad de transacciones a ejecutar:");
        int transacciones = scn.nextInt();
        iteradorDeTransacciones(transacciones);
    }

    public static void iteradorDeTransacciones(int transacciones){
        ArrayList<ArrayList<String>> resultados = new ArrayList<>();
        for(int i = 0; i < transacciones; i++){
            System.out.println("----------------- Transaccion " + (i + 1) + " -----------------");
            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad = scn.nextDouble();
            System.out.print("Ingrese moneda de origen (USD, EUR, COP, JPY): ");
            String origen = scn.next().toUpperCase();
            System.out.print("Ingrese monedas de destino (USD, EUR, COP, JPY separadas por coma, eje: USD,EUR): ");
            String destino = scn.next().toUpperCase();

            ArrayList<String> fila = new ArrayList<>();
            fila.add(origen);
            fila.add(String.valueOf(cantidad)); // Convertir a String
            fila.addAll(Arrays.asList(destino.split(",")));
            
            resultados.add(fila);
        }
        CalcularResultados(resultados);
    }

    private static void CalcularResultados(ArrayList<ArrayList<String>> resultados) {
        HashMap<String, Double> totales = new HashMap<>();
        
        int numTransaccion = 1;
        
        for (ArrayList<String> t : resultados) {
            String origen = t.get(0);
            double cantidad = Double.parseDouble(t.get(1));
            ArrayList<String> destinos = new ArrayList<>(t.subList(2, t.size()));
            System.out.println("\nTransacción " + numTransaccion + ": " + cantidad + " " + origen);
            for (String destino : destinos) {
                try {
                    double resultado = usd.convert(origen, destino, cantidad);
                    System.out.printf("  Convertido a %s: %.2f %s%n", destino, resultado, destino);
                    totales.put(destino, totales.getOrDefault(destino, 0.0) + resultado);
                } catch (RuntimeException e) {
                    System.out.println("  Error: " + e.getMessage());
                }
            }
            numTransaccion++;
        }
        MostrarResultadosTotales(totales);
    }

    private static void MostrarResultadosTotales(HashMap<String, Double> totales) {
        System.out.println("\n--- Totales por moneda ---");
        totales.forEach((moneda, total) -> 
            System.out.printf("%s: %.2f %s%n", moneda, total, moneda)
        );
    }


}
