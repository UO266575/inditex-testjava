# Test Java - Prueba técnica de Inditex
Este proyecto fue desarrollado como una prueba técnica para Inditex, implementando una API REST para determinar el precio correcto de un producto en función de la marca, la fecha y las reglas de precios.

Para garantizar una solución estructurada y mantenible:

- Se utilizó OpenAPI/Swagger para definir el contrato de la API y generar documentación automáticamente.
- Se eligió una Arquitectura Hexagonal para mejorar la modularidad, aumentar la flexibilidad y simplificar las pruebas.
- Se implementaron tests unitarios e integración para verificar el correcto funcionamiento del servicio, permitiendo validar la API en Postman.
- Se utilizó Lombok para reducir el código repetitivo y mejorar la legibilidad.

## Testing
- Se utilizó Mockito para simular las dependencias y verificar el comportamiento de la lógica de negocio de manera aislada, asegurando que los componentes funcionen correctamente sin necesidad de interactuar con la base de datos.
- Para las pruebas de integración, se creó un script de js para que poder simular todos los escenarios planteados. Para poder ejecutar las pruebas siga los siguientes pasos:
  1. Iniciar la aplicación.
  2. Añadir http://localhost:8080/api/v1/prices como URL de la petición.
  3. Importar y ejecutar el script de pruebas ubicado en la carpeta endpointtests.

## Carga de datos por defecto
Al iniciar la aplicación y gracias a la anotación @PostConstruct, se carga automáticamente datos en la base de datos H2. Se optó por este enfoque en lugar de un data.sql porque es independiente del motor de base de datos, evitando problemas de compatibilidad en caso de cambiar la tecnología de persistencia. Este enfoque está en línea con la Arquitectura Hexagonal, ya que permite desacoplar la lógica de inicialización de datos de una implementación específica de la base de datos