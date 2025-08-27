# Datepicker Automation (Serenity BDD + Cucumber + Gradle)

Automatización de pruebas para la funcionalidad de selección de fecha con JQuery Datepicker usando Serenity BDD (Screenplay), Cucumber y Gradle.

## Runner de pruebas

- Runner principal: `src/test/java/com/datepicker/runners/datepickerRunner.java`
  - Usa `@RunWith(CustomRunner.class)` que a su vez delega en `CucumberWithSerenity`.
  - `features`: `src/test/resources/features/reserva.feature`
  - `glue`: `com.datepicker.stepdefinitions`, `com.datepicker.hook`

Puedes ejecutar las pruebas:
- Desde tu IDE, ejecutando la clase `datepickerRunner`.
- O con Gradle (recomendado para CI):

```bash
# Windows PowerShell / CMD
gradlew clean test
```

> Nota: Si tienes Gradle instalado globalmente, también puedes usar `gradle clean test`.

## Generación del reporte Serenity con Gradle (aggregate)

Serenity genera un informe agregando los resultados de las ejecuciones mediante la tarea `aggregate`.

```bash
# Ejecutar pruebas y generar reporte agregando resultados
gradlew clean test aggregate
```

### Ubicación del reporte

- El reporte HTML de Serenity quedará disponible (típicamente) en:
  - `build/reports/serenity/index.html`

Abre `index.html` en tu navegador para ver el resumen, escenarios, evidencias y métricas.

## Requisitos

- Java JDK 8+ (recomendado 11 o superior)
- Chrome + ChromeDriver compatibles (si usas Chrome)
- Acceso a Internet para descargar dependencias en la primera ejecución

## Notas útiles

- Si ves advertencias del CDP (Chrome DevTools Protocol), alinea la versión de Selenium/WebDriver con tu versión de Chrome instalada.
- Si aparece un aviso de provider de Log4j, puedes añadir un binding SLF4J/Log4j si deseas silenciar el warning (no afecta la ejecución de las pruebas).

## Estructura relevante

- Features: `src/test/resources/features/`
- Step Definitions: `src/test/java/com/datepicker/stepdefinitions/`
- Runner: `src/test/java/com/datepicker/runners/datepickerRunner.java`
- Tasks/Questions/UI: `src/main/java/org/datepicker/`

---

Si necesitas agregar nuevos escenarios, pasos o configurar tags, edita el `@CucumberOptions` en `datepickerRunner` o los `.feature` en `src/test/resources/features/`. 
