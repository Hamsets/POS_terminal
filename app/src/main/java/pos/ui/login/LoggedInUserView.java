package pos.ui.login;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pos.Entities.Check;
import pos.Entities.Role;
import pos.Entities.User;

/**
 * Кkасс предоставляющий авторизованного пользователя для UI (ограниченная информация, без пароля)
 */
@JsonAutoDetect
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUserView {
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private Role role;
    private int id;
    private BigDecimal rating;

    private String urlServer;
    private int portServer;

    private static final String TAG = "logsLoggedInUserView";

    public String convertToJson (){
        String s = "";
        try {
            //писать результат сериализации будем во Writer(StringWriter)
            StringWriter writer = new StringWriter();

            //это объект Jackson, который выполняет сеi@i.com   риализацию
            ObjectMapper mapper = new ObjectMapper();

            // сама сериализация: 1-куда, 2-что
            mapper.writeValue(writer, this);

            //преобразовываем все записанное во StringWriter в строку
            s = writer.toString();
        } catch (IOException e){
            Log.d(TAG,"Ошибка сериализации LoggedInUserView в JSON.");
            e.printStackTrace();
        }
        return s;
    };

    public static LoggedInUserView convertFromJson (String s){
        LoggedInUserView loggedInUserView = null;
        try {
            StringReader reader = new StringReader(s);
            ObjectMapper mapper = new ObjectMapper();
            loggedInUserView = mapper.readValue(reader, LoggedInUserView.class);
        }catch (IOException e){
            Log.d(TAG,"Ошибка десериализации LoggedInUserView из JSON.");
            e.printStackTrace();
        }
        return loggedInUserView;
    }

    public User createUserForView () {
        return new User(this.id, this.firstName, this.lastName, this.surName, this.email,
                this.role, null, this.rating, false);
    }



//    LoggedInUserView(String displayName) {
//        this.displayName = displayName;
//    }

//    String getDisplayName() {
//        return displayName;
//    }

}