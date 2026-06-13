package com.inventory.smart.repository;

import com.inventory.smart.exception.ResourceNotFoundException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación abstracta en memoria de un repositorio genérico.
 * Utiliza ConcurrentHashMap para asegurar operaciones thread-safe.
 *
 * @param <T>  tipo de la entidad
 * @param <ID> tipo del identificador
 * @author Grupo 3 - Inventario Inteligente
 * @since 1.0
 */
public abstract class GenericInMemoryRepository<T, ID> implements IGenericRepository<T, ID> {

    /** Almacén de datos en memoria y thread-safe. */
    protected final ConcurrentHashMap<ID, T> dataStore = new ConcurrentHashMap<>();
    
    /** Generador atómico de IDs autoincrementales. */
    protected final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            ID id = (ID) getIdMethod.invoke(entity);

            if (id == null) {
                // Generar nuevo ID
                id = (ID) Long.valueOf(idGenerator.incrementAndGet());
                Method setIdMethod = entity.getClass().getMethod("setId", Long.class);
                setIdMethod.invoke(entity, id);
            }
            
            dataStore.put(id, entity);
            return entity;
            
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la entidad usando reflexión", e);
        }
    }

    @Override
    public void deleteById(ID id) {
        if (dataStore.remove(id) == null) {
            throw new ResourceNotFoundException("No se encontró la entidad con ID: " + id + " para eliminar.");
        }
    }

    @Override
    public boolean existsById(ID id) {
        return dataStore.containsKey(id);
    }

    @Override
    public long count() {
        return dataStore.size();
    }
}
