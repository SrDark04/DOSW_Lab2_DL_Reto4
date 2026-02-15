package com.ejemplo.Reto4;

public class USDHandler extends CurrencyHandler{
    
    @Override
    public double convert(String fromCurrency, String toCurrency, double amount){
        if(fromCurrency.equals("USD")){
            if(toCurrency.equals("EUR")) return amount * 0.84;
            if(toCurrency.equals("COP")) return amount * 3800;
            if(toCurrency.equals("JPY")) return amount * 152.55;
            throw new RuntimeException("No se puede convertir USD a " + toCurrency);
        }
        if(nextHandler != null){
            return nextHandler.convert(fromCurrency, toCurrency, amount);
        }
        throw new RuntimeException("No se puede convertir " + fromCurrency + " a " + toCurrency);
    }
}
