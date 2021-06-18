package support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.UserModel;

public class Utility {
    ObjectMapper objectMapper;
    public String convertToString(UserModel userModel) {
         objectMapper=new ObjectMapper();
         String request = "";
        try {
             request = objectMapper.writeValueAsString(userModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return request;
    }
}
