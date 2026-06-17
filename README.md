## Cómo ejecutar el programa
Requisitos: Java 17 y Maven instalados.
```bash
git clone https://github.com/ccettour/feria-emprendedores-ms2.git
cd feria-emprendedores-ms2
mvn compile
mvn exec:java -Dexec.mainClass="com.feria.Main"
```
## Cómo correr los tests
```bash  
mvn test
```

Para el reporte de cobertura:
```bash 
mvn test jacoco:report
```
### Reporte en: target/site/jacoco/index.html

## Decisiones técnicas
### Semana 1
Se renombraron variables crípticas y se separaron métodos con múltiples responsabilidades, aplicando SRP y DIP. 
### Semana 2
Se implementó el patrón Factory Method mediante EmprendedorFactory para centralizar la construcción y validación de Emprendedor, respetando OCP. 
### Semana 3
Los tests unitarios se escribieron con JUnit 5 y Mockito; la funcionalidad obtenerEstadoStock() fue desarrollada con TDD siguiendo ciclos rojo-verde-refactor. 
### Semana 4
El pipeline de CI con GitHub Actions ejecuta mvn test automáticamente en cada push a main.
