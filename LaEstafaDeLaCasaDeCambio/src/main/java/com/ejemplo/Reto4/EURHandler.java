package com.ejemplo.Reto4;

public class EURHandler extends CurrencyHandler{
    
    @Override
    public double convert(String fromCurrency, String toCurrency, double amount){
        if(fromCurrency.equals("EUR")){
            if(toCurrency.equals("USD")) return amount * 1.19;
           if(toCurrency.equals("COP"))  return amount * 4500;
           if(toCurrency.equals("JPY"))  return amount * 178.55;
            throw new RuntimeException("No se puede convertir EUR a " + toCurrency);
        }
        if(nextHandler != null){
            return nextHandler.convert(fromCurrency, toCurrency, amount);
        }
        throw new RuntimeException("No se puede convertir " + fromCurrency + " a " + toCurrency);
    }
    
}
