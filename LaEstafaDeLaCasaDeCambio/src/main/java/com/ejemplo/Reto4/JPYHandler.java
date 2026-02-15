package com.ejemplo.Reto4;

public class JPYHandler extends CurrencyHandler{
    
    @Override
    public double convert(String fromCurrency, String toCurrency, double amount){
        if(fromCurrency.equals("JPY")){
            if(toCurrency.equals("EUR")) return amount * 0.0069;
            if(toCurrency.equals("USD")) return amount * 0.0066;
            if(toCurrency.equals("COP")) return amount * 3.45;
            throw new RuntimeException("No se puede convertir JPY a " + toCurrency);
        }
        if(nextHandler != null){
            return nextHandler.convert(fromCurrency, toCurrency, amount);
        }
        throw new RuntimeException("No se puede convertir " + fromCurrency + " a " + toCurrency);
    }
}
