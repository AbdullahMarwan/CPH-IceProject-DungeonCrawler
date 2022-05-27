package studyGroupF.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONHelper {
    private static JSONHelper jsonHelperInstance = null;
    private ObjectMapper mapper = new ObjectMapper();

    public static JSONHelper getInstance()
    {
        if (jsonHelperInstance == null) {
            jsonHelperInstance = new JSONHelper();
            jsonHelperInstance.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        return jsonHelperInstance;
    }

    public Object readJSON(File file, Class clazz) {
        try {
            return this.mapper.readValue(file, clazz);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    public void writeJSON(File file, Object object) {
        try {
            mapper.writeValue(file, object);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
