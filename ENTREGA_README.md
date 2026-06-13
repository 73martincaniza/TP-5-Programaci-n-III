# TP5 - Gestión de Inventario Inteligente (Entrega) 📦

¡Hola profes! Este es el repositorio con la resolución del TP5 Integrador de Programación III.

## 🧑‍💻 El Equipo (Grupo 3)
- Estudiante Universitario
- (Pueden poner sus nombres acá)

## 🚀 Cómo correr el proyecto

1. Abran una terminal en esta carpeta.
2. Corran el clásico `mvn clean package`. Asegúrense de tener Java 21 instalado y seteado en sus variables de entorno.
3. Levanten la app con `mvn spring-boot:run` o dándole play a la clase principal en IntelliJ/Eclipse.
4. Vayan a `http://localhost:8081/swagger-ui.html` para ver y probar todos los endpoints documentados con Swagger.

## 🛠 Qué hicimos

Le metimos con todo a los requerimientos, acá va un resumen:
- **Interfaces Genéricas**: Creamos `IGenericRepository` y la superclase abstracta `GenericInMemoryRepository` usando `ConcurrentHashMap`. Así no repetimos código en cada repositorio y nos aseguramos de que no crashee todo si le pegan dos peticiones concurrentes (gracias al thread-safety de ConcurrentHashMap y AtomicLong).
- **Atomics para el Stock**: El stock en la clase `Producto` está usando `AtomicInteger`. Esto asegura que si cae un POST de retiro y un POST de ingreso de mercadería exactamente al mismo tiempo en hilos distintos, no haya *race conditions* y la matemática no se rompa.
- **Strategy Pattern**: Armamos `AlertaStrategy` para decidir cuándo un producto está crítico o bajo, inyectando los valores desde el `application.yml`. Si el día de mañana queremos cambiar cómo se calculan las alertas (ej: basado en promedios de ventas mensuales), solo agregamos otra clase y listo, respetamos Open/Closed Principle.
- **SOLID a pleno**: Nada de `@Autowired` suelto en los atributos, todo inyectado por constructor de forma limpia. Los controllers devuelven `Records` DTO, nunca la entidad de base. 
- **JavaDoc**: Prometemos que `mvn javadoc:javadoc` corre perfecto y sin quejas. Comentamos todas las clases, métodos públicos, params y throws.

## 📊 Performance Report

Pueden pegarle a `GET /api/admin/performance-report` para ver cómo sufre la compu poblando e iterando sobre 1k, 10k y 100k registros falsos. Dejamos también el archivo `PERFORMANCE.md` en la raíz con la tabla de Big O y nuestra justificación de por qué los números dan como dan.

Cualquier duda nos dicen. ¡Saludos!
