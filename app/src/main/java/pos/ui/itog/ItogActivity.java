package pos.ui.itog;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_ver_01.R;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import pos.Connection.ConnectionSettingsObj;
import pos.Connection.ConnectionType;
import pos.Connection.SendClass;
import pos.Dto.Role;

public class ItogActivity extends AppCompatActivity {
    private Role userRole;
    Button closeBtn;
    Button itogDateBtn;
    EditText itogCurrDayEdit;
    EditText itogByDateEdit;
    TextView startDateText;
    TextView endDateText;
    TextView itogoOnDateText;
    TextView dateDivText;
    private String urlServer;
    private int portServer;
    private DatePickerDialog.OnDateSetListener mDateStartSetListener;
    private DatePickerDialog.OnDateSetListener mDateEndSetListener;
    private static final String TAG = "logsItogActivity";

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_itog);

        itogDateBtn = (Button) findViewById(R.id.itogDateBtn);
        closeBtn = (Button) findViewById(R.id.itogCloseBtn);
        itogCurrDayEdit = (EditText) findViewById(R.id.itogCurrDayEdit);
        startDateText = (TextView) findViewById(R.id.startDateText);
        endDateText = (TextView) findViewById(R.id.endDateText);
        itogByDateEdit = (EditText) findViewById(R.id.itogByDateEdit);
        itogoOnDateText = (TextView) findViewById(R.id.itogoOnDateText);
        dateDivText = (TextView) findViewById(R.id.dateDivText);

        urlServer=getIntent().getStringExtra("urlServer");
        portServer=getIntent().getIntExtra("portServer",0);
        userRole = Role.valueOf(getIntent().getStringExtra("role"));

        if (userRole.equals(Role.ADMIN) || userRole.equals((Role.MANAGER))) {
            itogoOnDateText.setVisibility(View.VISIBLE);
            startDateText.setVisibility(View.VISIBLE);
            dateDivText.setVisibility(View.VISIBLE);
            endDateText.setVisibility(View.VISIBLE);
            itogDateBtn.setVisibility(View.VISIBLE);
            itogByDateEdit.setVisibility(View.VISIBLE);

        } else {
            itogoOnDateText.setVisibility(View.INVISIBLE);
            startDateText.setVisibility(View.INVISIBLE);
            dateDivText.setVisibility(View.INVISIBLE);
            endDateText.setVisibility(View.INVISIBLE);
            itogDateBtn.setVisibility(View.INVISIBLE);
            itogByDateEdit.setVisibility(View.INVISIBLE);
        }

        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ItogActivity.this, android.R.style.Theme_Black_NoTitleBar, mDateStartSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateStartSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                startDateText.setText(date);
            }
        };

        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ItogActivity.this, android.R.style.Theme_Black_NoTitleBar, mDateEndSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateEndSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                endDateText.setText(date);
            }
        };

//        try {
//            itogDayStr = getIntent().getStringExtra("itogDay");
//
//            if (!itogDayStr.isEmpty()) {
//                itogCurrDayEdit.setText(itogDayStr);
//            }
//
//        } catch (NullPointerException e) {
//            Log.d(TAG, "Дневная выручка отсутствует, игнорируется метод вывода.");}

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.itogCloseBtn:
                        itogCloseBtnClick();
                        break;
                    case R.id.itogDateBtn:
                        setItogDateBtnClick();
                        break;
                }
            }
        };
        closeBtn.setOnClickListener(onClickListener);
        itogDateBtn.setOnClickListener(onClickListener);
        itogCurrDayEdit.setText(getDayItog());
    }

    private void setItogDateBtnClick(){
        itogByDateEdit.setText(getDayItog());
    }

    private void itogCloseBtnClick() {
        ItogActivity.super.finish();
    }

    private String getDayItog() {
        String result="";
        ConnectionSettingsObj connectionSettingsObj = prepareSendObjItog();
        SendClass sendClass = new SendClass();
        sendClass.execute(connectionSettingsObj);
        try {
            result=sendClass.get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ConnectionSettingsObj prepareSendObjItog() {
        ConnectionSettingsObj connectionSettingsObj;
        String requestStr = ConnectionType.READ_DAY_ITOG.toString() + "#" + startDateText.getText()
                + "#" + endDateText.getText();
        connectionSettingsObj = new ConnectionSettingsObj(requestStr,urlServer,portServer);
        return connectionSettingsObj;
    }


    @Override
    protected void onStop() {
        Log.d(TAG,"Закрытие окна дневной выручки");
        super.onStop();
    }
}
