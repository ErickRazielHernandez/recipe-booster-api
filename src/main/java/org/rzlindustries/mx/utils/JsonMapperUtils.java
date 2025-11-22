package org.rzlindustries.mx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonMapperUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private JsonMapperUtils() {super();}

    /**
     * Convierte un objeto a JSON
     *
     * @param object objeto a convertir a JSON
     * @param <T>    Objeto genérico a convertir a JSON
     * @return objeto en formato JSON
     */
    public static <T> String toJson(T object) {
        String result = objectMapper.createObjectNode().toString();
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error("Error al generar JSON: ", ex);
        }
        return result;
    }

    /**
     * Convierte un JSON a un objeto
     *
     * @param json  json a convertir en objeto
     * @param clazz clase del objeto a convertir
     * @return instancia de la clase recibida
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
            result = objectMapper.readValue(json, clazz);
            return result;
        } catch (JsonProcessingException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            log.error("Error al generar Objeto: ", e);
        }
        return result;
    }

    /**
     * Convierte un JSON a un objeto
     *
     * @param jsonNode  {@link JsonNode} a convertir en objeto
     * @param clazz     clase del objeto a convertir
     * @return instancia de la clase recibida
     */
    public static <T> T toObject(JsonNode jsonNode, Class<T> clazz) {
        T result = null;
        try {
            result = clazz.getConstructor().newInstance();
            result = objectMapper.treeToValue(jsonNode, clazz);
            return result;
        } catch (JsonProcessingException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            log.error("Error al generar Objeto: ", e);
        }
        return result;
    }

    /**
     * Convierte un JSON en una lista de objetos
     *
     * @param json json a convertir en una lista de objetos
     * @return lista de objetos
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            log.error("Error al generar lista de objetos: ", e);
        }
        return result;
    }

    /**
     * Convierte un objeto a un {@link ObjectNode}
     *
     * @param object objeto a convertir
     * @param <T>    objeto genérico a convertir
     * @return objeto de tipo {@link ObjectNode}
     */
    public static <T> ObjectNode toObjectNode(T object) {
        ObjectNode result = objectMapper.createObjectNode();
        try {
            result = (ObjectNode) objectMapper.readTree(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException ex) {
            log.error("Error al generar JSON");
        }
        return result;
    }

    /**
     * Agrega un {@link JsonNode} con su respectiva llave a un {@link ObjectNode}
     *
     * @param objectNode objeto de tipo {@link ObjectNode}
     * @param key        llave del nodo a agregar
     * @param jsonNode   objeto de tipo {@link JsonNode}
     * @return objeto de tipo {@link ObjectNode}
     */
    public static ObjectNode addJsonNode(ObjectNode objectNode, String key, JsonNode jsonNode) {
        objectNode.set(key, jsonNode);
        return objectNode;
    }

    /**
     * Agrega un {@link JsonNode} con su respectiva llave a un {@link ObjectNode}
     *
     * @param objectNode objeto de tipo {@link ObjectNode}
     * @param key        llave del nodo a agregar
     * @param value      valor a agregar en la llave
     * @return objeto de tipo {@link ObjectNode}
     */
    public static ObjectNode addJsonNode(ObjectNode objectNode, String key, String value) {
        objectNode.put(key, value);
        return objectNode;
    }

    public static List<JsonNode> toArrayJsonNodes(String jsonArray) {
        List<JsonNode> result = new ArrayList<>();
        try {
            result = objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class));
        } catch (JsonProcessingException e) {
            log.error("Error al generar lista de json nodes: ", e);
        }
        return result;
    }

    public static boolean hasKey(String json, String key) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode searchNode = rootNode.get(key);
            if (searchNode != null) {
                return searchNode.isEmpty();
            }
        } catch (JsonProcessingException e) {
            log.error("Error al leer el json: ", e);
        }
        return false;
    }

    public static boolean hasKeyNotEmpty(String json, String key) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode searchNode = rootNode.get(key);
            if (searchNode != null) {
                return !searchNode.isEmpty();
            }
        } catch (JsonProcessingException e) {
            log.error("Error al leer el json: ", e);
        }
        return false;
    }
}
