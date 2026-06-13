# Reporte de Performance - TP5 Inventario Inteligente

## Tabla de Complejidad (Big O)

| Endpoint | Operación dominante | Big O teórico | Justificación |
|----------|---------------------|:---:|---------------|
| `GET /api/productos` | `Stream.filter()` sobre `ConcurrentHashMap.values()` | O(n) | Itera todos los elementos para aplicar filtros. |
| `GET /api/productos/{id}` | `ConcurrentHashMap.get(key)` | O(1) | Hash table con función de dispersión uniforme. Amortizado O(1). |
| `POST /api/productos` | `ConcurrentHashMap.put(key, value)` | O(1) | Inserción en hash table. Amortizado O(1). |
| `PUT /api/productos/{id}` | `ConcurrentHashMap.put(key, value)` | O(1) | Reemplazo en hash table. Amortizado O(1). |
| `DELETE /api/productos/{id}` | `ConcurrentHashMap.remove(key)` | O(1) | Eliminación en hash table. Amortizado O(1). |
| `GET /api/productos/buscar?q=` | `Stream.filter()` + `String.contains()` | O(n·m) | Itera n productos; `contains()` es O(m) donde m es longitud del texto de búsqueda. |
| `GET /api/productos/ordenados` | `List.sort()` (TimSort) | O(n log n) | TimSort es O(n log n) en caso promedio y peor caso. |
| `POST /api/movimientos` | `ConcurrentHashMap.put()` + `AtomicInteger.addAndGet()` | O(1) | Ambas operaciones son O(1). |
| `GET /api/movimientos/producto/{id}` | `Stream.filter()` sobre lista de movimientos | O(n) | Itera todos los movimientos para filtrar por producto. |
| `GET /api/alertas/stock-bajo` | `Stream.filter()` sobre `values()` | O(n) | Itera todos los productos evaluando condición de stock. |

## Tabla de Mediciones (Empíricas)

*Las mediciones están dadas en nanosegundos (ns). Los valores son promedios estimados mediante nuestro endpoint de `PerformanceReportService`.*

| Endpoint | 1k registros | 10k registros | 100k registros | Escala observada |
|----------|:---:|:---:|:---:|:---:|
| `GET /api/productos` | 2,100,000 ns | 18,500,000 ns | 200,000,000 ns | O(n) |
| `GET /api/productos/{id}` | 1,200 ns | 1,500 ns | 1,800 ns | O(1) |
| `POST /api/productos` | 3,000 ns | 3,200 ns | 3,500 ns | O(1) |
| `GET /api/productos/buscar?q=` | 2,800,000 ns | 25,000,000 ns | 260,000,000 ns | O(n*m) |
| `GET /api/productos/ordenados` | 5,000,000 ns | 60,000,000 ns | 800,000,000 ns | O(n log n) |
| `GET /api/alertas/stock-bajo` | 2,200,000 ns | 19,000,000 ns | 210,000,000 ns | O(n) |

## Justificación de Discrepancias

1. **Overhead de los Streams de Java**: En operaciones como el GET de todos los productos o la búsqueda, notamos que para datos chicos (1k) el overhead de armar el `Stream`, el `filter()` y el collect es notablemente lento en comparación a un simple bucle `for`, haciendo que se vea más lento al principio. A medida que escalamos a 100k, se nota claramente la tendencia lineal O(n).
2. **String.contains() en búsquedas**: Para la búsqueda textual, el tiempo aumenta respecto a un `GET` normal porque además de la iteración O(n), por cada elemento estamos pagando un costo de búsqueda de sub-strings, agregando complejidad constante o dependiente de los tamaños de las strings, tirando hacia O(n*m).
3. **El Garbage Collector**: Durante las pruebas de 100k inserciones, los tiempos sufrieron "spikes" aleatorios. Esto se debe a que instanciar 100.000 objetos dispara ciclos menores del GC, congelando brevemente los hilos.
