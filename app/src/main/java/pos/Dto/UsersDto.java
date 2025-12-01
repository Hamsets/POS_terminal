package pos.Dto;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.Entities.Role;
import pos.Entities.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto{
    private int id;
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private Role role;
    private String password;
    private BigDecimal rating;
    private boolean deleted;
//    private User user;
    private static final String TAG = "logsUsersDto";

    public static String convertToJson (User user){
        String s = "";
        try {
            //писать результат сериализации будем во Writer(StringWriter)
            StringWriter writer = new StringWriter();

            //это объект Jackson, который выполняет сериализацию
            ObjectMapper mapper = new ObjectMapper();

            // сама сериализация: 1-куда, 2-что
            mapper.writeValue(writer, user);

            //преобразовываем все записанное во StringWriter в строку
            s = writer.toString();
        } catch (IOException e){
            Log.d(TAG,"Ошибка сериализации User в JSON.");
            e.printStackTrace();
        }
        return s;
    };

    public static User convertFromJson (String s){
        User user = new User ();
        try {
            StringReader reader = new StringReader(s);
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(reader, User.class);
        }catch (IOException e){
            Log.d(TAG,"Ошибка десериализации User из JSON.");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDto usersDto = (UsersDto) o;
        return id == usersDto.id
                && firstName.equals(usersDto.firstName)
                && lastName.equals(usersDto.lastName)
                && surName.equals(usersDto.surName)
                && email.equals(usersDto.email)
                && role.equals(usersDto.role)
                && password.equals(usersDto.password)
                && rating.equals(usersDto.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, surName, email, role, password, rating);
    }
}
