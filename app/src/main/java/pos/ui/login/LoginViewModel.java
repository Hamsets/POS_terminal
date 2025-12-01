package pos.ui.login;

import android.util.Patterns;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.pos_ver_01.R;
import pos.Connection.ConnectionSettingsObj;
import pos.Connection.ConnectionType;
import pos.Dto.UsersDto;
import pos.Entities.User;
import pos.data.LoginRepository;
import pos.data.Result;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private static final String TAG = "logsLoginViewmodel";

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password, String urlServer, int portServer) {
        //TODO can be launched in a separate asynchronous job
        //может быть запущен в отдельном асинхронном задании

        ConnectionSettingsObj connectionSettingsObj = prepareSendObj(username, password,
                urlServer,portServer);

        Result<User> result = loginRepository.login(connectionSettingsObj);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getFirstName(),
                    data.getLastName(), data.getSurName(), data.getEmail(), data.getRole(),
                    data.getUserId(), data.getRating(), urlServer, portServer)));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public ConnectionSettingsObj prepareSendObj(String username, String password, String urlServer,
                                                int portServer){
        ConnectionSettingsObj connectionSettingsObj;
        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        String str = ConnectionType.COMPARE_USER +  "#" + UsersDto.convertToJson(user);
        connectionSettingsObj = new ConnectionSettingsObj(str,urlServer,portServer);
        return connectionSettingsObj;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 3;
    }
}