package com.ejemplo.Reto4;

public abstract class CurrencyHandler {
    protected CurrencyHandler nextHandler;

    public void setNext(CurrencyHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public abstract double convert(String fromCurrency, String toCurrency, double amount);
}
