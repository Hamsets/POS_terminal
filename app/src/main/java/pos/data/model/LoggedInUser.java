package pos.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pos.Entities.Role;

/**
 * Класс который собирает информацю о вошедших в систему пользователях, полученных от LoginRepository
 * УДАЛИТЬ!!!!!!!!!!!!!!
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Getter
@AllArgsConstructor
public class LoggedInUser {

    private int userId;
    private String displayName;
    private Role role;

//    public LoggedInUser(int userId, String displayName) {
//        this.userId = userId;
//        this.displayName = displayName;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public String getDisplayName() {
//        return displayName;
//    }
}