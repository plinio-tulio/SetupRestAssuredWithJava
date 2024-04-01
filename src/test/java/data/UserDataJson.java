package data;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

public class UserDataJson {

    public static JSONObject GetUserDataJson() {
        Faker faker = new Faker();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", faker.name().firstName());
        jsonObject.put("job", faker.job().title());
        return jsonObject;
    }
}
