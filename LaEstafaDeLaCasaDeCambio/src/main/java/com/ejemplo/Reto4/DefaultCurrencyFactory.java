package com.ejemplo.Reto4;

public class DefaultCurrencyFactory implements CurrencyFactory {
    
    public CurrencyHandler createUSDHandler() {
        return new USDHandler();
    }

    public CurrencyHandler createEURHandler() {
        return new EURHandler();
    }

    public CurrencyHandler createCOPHandler() {
        return new COPHandler();
    }

    public CurrencyHandler createJPYHandler() {
        return new JPYHandler();
    }
}
