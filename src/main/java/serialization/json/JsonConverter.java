package serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serialization.SerializationStructure;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Collection;

public class JsonConverter {

    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonConverter() {
    }

    public static void serializeToJson(SerializationStructure structure, Path filePath) {
        String file = "" + filePath + java.time.LocalDate.now() + ".json";
        logger.info("Serialization to " + file + " started");
        try (Writer writer = new FileWriter(file)) {
            writer.write(convertToJson(structure));
        } catch (Exception e) {
            logger.error("Serialization to " + file + " failed", e);
            throw new RuntimeException(e);
        }
        logger.info("Serialization to " + file + " ended successfully");
    }

    public static String convertToJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T convertFromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public static <T extends Collection<?>> T convertFromJson(String json, TypeToken<T> type) {
        return gson.fromJson(json, type);
    }
}
