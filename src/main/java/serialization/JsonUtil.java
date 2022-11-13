package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Student;
import model.University;

import java.util.Collection;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil() {
    }


    public static String serialize(Object object) {
        return gson.toJson(object);
    }


    public static Student deserializeStudent(String json) {
        return deserialize(json, Student.class);
    }

    public static University deserializeUniversity(String json) {
        return deserialize(json, University.class);
    }


    public static List<Student> deserializeStudents(String json) {
        return deserialize(json, new TypeToken<>(){});
    }

    public static List<University> deserializeUniversities(String json) {
        return deserialize(json, new TypeToken<>(){});
    }



    private static <T> T deserialize(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    private static <T extends Collection<?>> T deserialize(String json, TypeToken<T> type) {
        return gson.fromJson(json, type);
    }
}
