package com.ejemplo.Reto4;

public class COPHandler extends CurrencyHandler{
    
    @Override
    public double convert(String fromCurrency, String toCurrency, double amount){
        if(fromCurrency.equals("COP")){
            if(toCurrency.equals("EUR")) return amount * 0.00029;
            if(toCurrency.equals("USD")) return amount * 0.00029;
            if(toCurrency.equals("JPY")) return amount * 0.29;
            throw new RuntimeException("No se puede convertir COP a " + toCurrency);
        }
        if(nextHandler != null){
            return nextHandler.convert(fromCurrency, toCurrency, amount);
        }
        throw new RuntimeException("No se puede convertir " + fromCurrency + " a " + toCurrency);
    }
}
