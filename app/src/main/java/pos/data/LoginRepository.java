package pos.data;

import pos.Connection.ConnectionSettingsObj;
import pos.data.model.LoggedInUser;

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
    private LoggedInUser user = null;

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

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(ConnectionSettingsObj connectionSettingsObj) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(connectionSettingsObj);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}