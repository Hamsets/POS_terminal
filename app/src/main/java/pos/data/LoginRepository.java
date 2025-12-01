package pos.data;

import pos.Connection.ConnectionSettingsObj;
import pos.Entities.User;


/**
 * Класс, который запрашивает аутентификацию и информацию о пользователе из удаленного источника
 * данных и поддерживает в памяти кэш информации о статусе входа и учетных данных пользователя.
 *
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // Если пользовательские реквизиты кэшируются в локальном хранилище, рекомендуется их шифрование
    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
//    private LoggedInUser user = null;
    private User user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(User user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<User> login(ConnectionSettingsObj connectionSettingsObj) {
        // handle login
        Result<User> result = dataSource.login(connectionSettingsObj);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<User>) result).getData());
        }
        return result;
    }
}