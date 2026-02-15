# DOSW Lab 2 - Reto 4: La Estafa de la Casa de Cambio

## Descripción del Proyecto

Sistema de conversión de divisas desarrollado como parte del Laboratorio 2 de Diseño Orientado a Servicios Web. El proyecto implementa una innovadora solución que combina dos patrones de diseño: **Chain of Responsibility** y **Abstract Factory**, permitiendo realizar conversiones múltiples de divisas de manera flexible y escalable.

## Objetivo del Reto

Desarrollar un sistema de casa de cambio que permita:
- Realizar múltiples conversiones de divisas en una sola transacción
- Procesar varias transacciones consecutivas
- Calcular y mostrar totales acumulados por cada moneda de destino
- Manejar errores de conversión de manera elegante

## Patrones de Diseño Utilizados

### 1. Chain of Responsibility (Cadena de Responsabilidad)

Este patrón permite que cada moneda maneje sus propias conversiones y, si no puede procesarlas, las pase al siguiente manejador en la cadena.

**Ventajas:**
- **Single Responsibility**: Cada handler maneja únicamente las conversiones desde su moneda específica
- **Open/Closed Principle**: Se pueden agregar nuevas monedas sin modificar el código existente
- **Bajo acoplamiento**: Los handlers no necesitan conocer la estructura completa de la cadena

**Implementación:**
```
USDHandler → EURHandler → COPHandler → JPYHandler
```

Cada handler intenta procesar la conversión. Si la moneda de origen coincide con la que maneja, realiza la conversión; de lo contrario, pasa la solicitud al siguiente handler.

### 2. Abstract Factory (Fábrica Abstracta)

Este patrón proporciona una interfaz para crear familias de objetos relacionados (los handlers de cada moneda) sin especificar sus clases concretas.

**Ventajas:**
- Centraliza la creación de todos los handlers
- Facilita el cambio de implementaciones
- Garantiza que los objetos creados sean compatibles entre sí

## Estructura del Proyecto

```
LaEstafaDeLaCasaDeCambio/
│
├── src/
│   ├── main/java/com/ejemplo/Reto4/
│   │   ├── App.java                      # Clase principal con lógica de negocio
│   │   ├── CurrencyHandler.java          # Clase abstracta base (Chain of Responsibility)
│   │   ├── USDHandler.java               # Handler para conversiones desde USD
│   │   ├── EURHandler.java               # Handler para conversiones desde EUR
│   │   ├── COPHandler.java               # Handler para conversiones desde COP
│   │   ├── JPYHandler.java               # Handler para conversiones desde JPY
│   │   ├── CurrencyFactory.java          # Interfaz Abstract Factory
│   │   └── DefaultCurrencyFactory.java   # Implementación concreta de la fábrica
│   │
│   └── test/java/com/ejemplo/Reto4/
│       └── AppTest.java                  # Tests unitarios
│
├── pom.xml                               # Configuración Maven
└── README.md                             # Este archivo
```

## Diagrama de Arquitectura

```
┌─────────────────────────────────────────────────────────────┐
│                           App.java                           │
│                    (Lógica de Negocio)                       │
└────────────────────┬───────────────────────┬─────────────────┘
                     │                       │
                     ▼                       ▼
         ┌──────────────────────┐  ┌──────────────────────┐
         │  CurrencyFactory     │  │  CurrencyHandler     │
         │    (Interface)       │  │   (Abstract Class)   │
         └──────────┬───────────┘  └──────────┬───────────┘
                    │                         │
                    ▼                         │
    ┌──────────────────────────┐              │
    │ DefaultCurrencyFactory   │              │
    │  (Concrete Factory)      │              │
    └──────────┬───────────────┘              │
               │ Creates                      │ Extends
               │                              │
               └──────────┬───────────────────┼──────────────┐
                          │                   │              │
                          ▼                   ▼              ▼
                  ┌──────────────┐    ┌──────────────┐  ┌──────────────┐
                  │  USDHandler  │───▶│  EURHandler  │─▶│  COPHandler  │─▶...
                  └──────────────┘    └──────────────┘  └──────────────┘
                   (Chain of Responsibility)
```

## Tasas de Conversión Implementadas

### Desde USD (Dólar Estadounidense)
- USD → EUR: 0.84
- USD → COP: 3,800
- USD → JPY: 152.55

### Desde EUR (Euro)
- EUR → USD: 1.19
- EUR → COP: 4,500
- EUR → JPY: 178.55

### Desde COP (Peso Colombiano)
- COP → USD: 0.00029
- COP → EUR: 0.00029
- COP → JPY: 0.29

### Desde JPY (Yen Japonés)
- JPY → USD: 0.0066
- JPY → EUR: 0.0069
- JPY → COP: 3.45

## Requisitos del Sistema

- **Java**: JDK 11 o superior
- **Maven**: 3.6 o superior
- **Sistema Operativo**: Windows, Linux o macOS

## Instalación y Ejecución

### 1. Clonar o descargar el proyecto

```bash
cd LaEstafaDeLaCasaDeCambio
```

### 2. Compilar el proyecto

```bash
mvn clean compile
```

### 3. Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.Reto4.App"
```

**O ejecutar todo en un solo comando:**

```bash
mvn clean compile exec:java -Dexec.mainClass="com.ejemplo.Reto4.App"
```

## Ejemplo de Uso

### Entrada del Usuario

```
Bienvenido a la tienda de cambio de divisas.
Ingrese la cantidad de transacciones a ejecutar: 2

----------------- Transaccion 1 -----------------
Ingrese la cantidad a convertir: 100
Ingrese moneda de origen (USD, EUR, COP, JPY): USD
Ingrese monedas de destino (USD, EUR, COP, JPY separadas por coma): EUR,COP

----------------- Transaccion 2 -----------------
Ingrese la cantidad a convertir: 50
Ingrese moneda de origen (USD, EUR, COP, JPY): EUR
Ingrese monedas de destino (USD, EUR, COP, JPY separadas por coma): USD,JPY
```

### Salida Esperada

```
Transacción 1: 100.0 USD
  Convertido a EUR: 84.00 EUR
  Convertido a COP: 380000.00 COP

Transacción 2: 50.0 EUR
  Convertido a USD: 59.50 USD
  Convertido a JPY: 8927.50 JPY

--- Totales por moneda ---
EUR: 84.00 EUR
COP: 380000.00 COP
USD: 59.50 USD
JPY: 8927.50 JPY
```

## Ejecutar Tests

```bash
mvn test
```

## Tecnologías Utilizadas

- **Lenguaje**: Java 11
- **Gestor de Dependencias**: Maven
- **Testing**: JUnit 4.11
- **Codificación**: UTF-8

## Detalles de Implementación

### CurrencyHandler (Clase Abstracta)

Define la estructura base para todos los handlers de la cadena:
- `setNext(CurrencyHandler nextHandler)`: Establece el siguiente handler en la cadena
- `convert(String fromCurrency, String toCurrency, double amount)`: Método abstracto para convertir

### Handlers Concretos

Cada handler (USDHandler, EURHandler, COPHandler, JPYHandler):
1. Verifica si puede manejar la moneda de origen
2. Si puede, realiza la conversión a la moneda de destino
3. Si no puede, delega al siguiente handler en la cadena
4. Si ningún handler puede procesar, lanza una excepción

### CurrencyFactory

Interfaz que define los métodos para crear cada tipo de handler:
- `createUSDHandler()`
- `createEURHandler()`
- `createCOPHandler()`
- `createJPYHandler()`

### DefaultCurrencyFactory

Implementación concreta que crea las instancias de cada handler.

### App.java

Clase principal que:
1. Crea la fábrica de handlers
2. Configura la cadena de responsabilidad
3. Solicita datos al usuario
4. Procesa las transacciones
5. Calcula y muestra los totales

## Ventajas de esta Arquitectura

1. **Escalabilidad**: Agregar nuevas monedas solo requiere crear un nuevo handler
2. **Mantenibilidad**: Cada clase tiene una responsabilidad única y clara
3. **Flexibilidad**: Las tasas de conversión están encapsuladas en cada handler
4. **Reutilización**: Los patrones facilitan la reutilización de código
5. **Testabilidad**: Cada componente puede ser probado independientemente

## Autores

- **Roger Mauricio Duran Guacaneme**
- **Camilo Alfonso Leon Acosta**

Desarrollado como parte del Laboratorio 2 - DOSW 401

## Licencia

Proyecto académico - Escuela Colombiana de Ingeniería Julio Garavito

---
