package pos.data;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import pos.Connection.ConnectionSettingsObj;
import pos.Connection.SendClass;
import pos.Dto.UsersDto;
import pos.Entities.User;


/**
 * Класс, который обрабатывает аутентификацию с использованием учетных данных для входа и получает информацию о пользователе
 *
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private static final String TAG = "logsLoginDataSource";
    private String answer = "";
    public Result<User> login(ConnectionSettingsObj connectionSettingsObj) {


        try {
            // TODO: handle loggedInUser authentication
            SendClass sendClass = new SendClass();
            sendClass.execute(connectionSettingsObj);
            if (sendClass != null) {

                try {
                    Log.d(TAG, "Попытка отправки реквизитов пользователя:");
                    answer = sendClass.get();
                    if (!answer.isEmpty()) {
//                        boolean isHasDbUser = Integer.parseInt(answer) != -1;
                        Log.d(TAG, "- отправляемые данные: " + connectionSettingsObj.getStrMessage());
                        Log.d(TAG, "- результат отправки на сервер: " + (!answer.equals("-1")));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            if (!answer.isEmpty() && !answer.equals("-1")) {
                Log.d(TAG, "Логин принят сервером");
                User logedInUser = userMapper(answer);
                return new Result.Success<>(logedInUser);
            } else {
//                LoggedInUser fakeUser = new LoggedInUser(0, "Jane Doe");
                Log.d(TAG, "Логин отвергнут сервером!");
                return new Result.Error(new Exception("Ошибка входа"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Ошибка входа", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
    private User userMapper (String userStr){

        try {
            return UsersDto.convertFromJson(userStr);
//            userStr.replaceAll("[^A-Za-zА-Яа-я0-9 ]", "");
        } catch (Exception e) {
            Log.d(TAG, "Ошибка создания User из ответа сервера. " + e);
            return null;
        }

    }
}