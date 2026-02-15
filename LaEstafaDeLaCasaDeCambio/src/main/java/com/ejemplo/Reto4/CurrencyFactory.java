package com.ejemplo.Reto4;

public interface CurrencyFactory {
    CurrencyHandler createUSDHandler();
    CurrencyHandler createEURHandler();
    CurrencyHandler createCOPHandler();
    CurrencyHandler createJPYHandler();
}
