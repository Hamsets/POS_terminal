package pos.ui.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pos.Dto.Role;

/**
 * Кkасс предоставляющий авторизованного пользователя для UI
 *
 * Class exposing authenticated user details to the UI.
 */

@Getter
@AllArgsConstructor
class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI
    private int id;
    private Role role;
    private String urlServer;
    private int portServer;

//    LoggedInUserView(String displayName) {
//        this.displayName = displayName;
//    }

//    String getDisplayName() {
//        return displayName;
//    }

}