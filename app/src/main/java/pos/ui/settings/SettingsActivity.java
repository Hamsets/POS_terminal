package pos.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_ver_01.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "logsSettingsActivity";
    private SharedPreferences settings;
    private static final String PREFS_FILE = "Properties";
    private static final String PREF_URL_SERVER = "urlServer";
    private static final String PREF_PORT_SERVER = "portServer";
    private static final String PREF_POS_NAME = "posName";
    private String urlServer;
    private int portServer;
    private String posName;
    Button saveBtn;
    Button closeBtn;
    Button settingsMainCloseBtn;
    EditText ipEditText;
    EditText portEditText;
    EditText posEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveBtn = (Button) findViewById(R.id.settingsSaveBtn);
        closeBtn = (Button) findViewById(R.id.settingsCloseBtn);
        ipEditText = (EditText) findViewById(R.id.settingsServIPEdit);
        portEditText = (EditText) findViewById(R.id.settingsServPortEdit);
        posEditText = (EditText) findViewById(R.id.settingsServPosEdit);

        settings = getSharedPreferences(getString(R.string.properties), MODE_PRIVATE);

        urlServer = settings.getString(getString(R.string.urlServer),"");
        Log.d(TAG, "Получен IP адрес: " + urlServer);

        portServer = settings.getInt(getString(R.string.portServer), 0);
        Log.d(TAG, "Получен номер порта: " + portServer);

//        urlServer = settings.getString(PREF_URL_SERVER,"");
//        Log.d(TAG, "Получен IP адрес: " + urlServer);
//
//        portServer = settings.getInt(PREF_PORT_SERVER, 0);
//        Log.d(TAG, "Получен номер порта: " + portServer);

        posName = settings.getString(PREF_POS_NAME, "");
        Log.d(TAG, "Получено название кассы: " + posName);

        ipEditText.setText(urlServer);
        portEditText.setText(String.valueOf(portServer));
        posEditText.setText(posName);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.settingsSaveBtn:
                        saveSettingsBtnClick();
                        break;
                    case R.id.settingsCloseBtn:
                        canselSettingsBtnClick();
                        break;

                }
            }
        };
        saveBtn.setOnClickListener(onClickListener);
        closeBtn.setOnClickListener(onClickListener);
    }

    private void mainSettingsBtnClick(){
//        super.finish();
//        super.finish();
    }

    private void canselSettingsBtnClick() {

        super.finish();
    }

    private void saveSettingsBtnClick() {

        try {
            String ip = String.valueOf(ipEditText.getText());
            int port = Integer.parseInt(portEditText.getText().toString());
            String pos = String.valueOf(posEditText.getText());

            if (!ip.isEmpty() & isIP(ip)) {
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putString(PREF_URL_SERVER, ip);
                prefEditor.putInt(PREF_PORT_SERVER, port);
                prefEditor.putString(PREF_POS_NAME, pos);
                prefEditor.apply();
                Log.d(TAG,"Внесен новый адрес сервера: " + ip);
                Log.d(TAG,"Внесен новый порт сервера: " + port);
                Log.d(TAG,"Внесен новый идентификатор точки продаж: " + pos);
            }
        } catch (Exception e){
            Log.d(TAG,"Ошибка: " + e);
        }
    }

    private Boolean isIP(String ip) {
        String[] subNet = ip.split("\\.");
        if (subNet.length !=0 &
                Integer.parseInt(subNet[0])>0 & Integer.parseInt(subNet[0])<255 &
                Integer.parseInt(subNet[1])>0 & Integer.parseInt(subNet[1])<255 &
                Integer.parseInt(subNet[2])>0 & Integer.parseInt(subNet[2])<255 &
                Integer.parseInt(subNet[3])>0 & Integer.parseInt(subNet[3])<255) {
            return true;
        }
        else return false;
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Закрытие окна настроек");
        super.onStop();
    }
}