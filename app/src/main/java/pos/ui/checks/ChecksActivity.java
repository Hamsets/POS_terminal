package pos.ui.checks;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pos_ver_01.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import pos.Connection.ConnectionSettingsObj;
import pos.Connection.ConnectionType;
import pos.Connection.SendClass;
import pos.Dto.CheckDto;
import pos.Dto.Role;


public class ChecksActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{
    private Role userRole;
    private String urlServer;
    private int portServer;
    private static final String TAG = "logsChecksActivity";
    private Button closeBtn;
    private Button deleteBtn;
    private Button dateBtn;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ArrayList<CheckDto> checkDtoArrayList = new ArrayList<>();
    private ListView listViewChecks;
    private View header;
    private View footer;
    final ArrayList<String> checksListStr = new ArrayList<>();
    private Calendar cal = Calendar.getInstance();
    private int year = cal.get(Calendar.YEAR);
    private int month = cal.get(Calendar.MONTH);
    private int day = cal.get(Calendar.DAY_OF_MONTH);
    private SparseBooleanArray chosen;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checks);

        urlServer = getIntent().getStringExtra("urlServer");
        portServer = getIntent().getIntExtra("portServer", 0);
        userRole = Role.valueOf(getIntent().getStringExtra("role"));

        listViewChecks = (ListView) findViewById(R.id.list_view_checks);
        closeBtn = (Button) findViewById(R.id.activity_checks_close_button);
        deleteBtn = (Button) findViewById(R.id.activity_checks_delete_button);
        dateBtn = (Button) findViewById(R.id.activity_checks_date_button);

        header = createHeader ("Дата: " + day + "/" + getCorrectMonth(month) + "/" + year);
        footer = createFooter ();

        listViewChecks.addHeaderView(header);
        listViewChecks.addFooterView(footer);

        listViewChecks.getCheckedItemPositions();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,
                checksListStr);

        listViewChecks.setAdapter(adapter);

        getArrayCheckByDateFromServer(year, month, day);

        listViewChecks.setLongClickable(true);
        listViewChecks.setOnItemLongClickListener(this::onItemLongClick);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ChecksActivity.this,
                        android.R.style.Theme_Black_NoTitleBar, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.activity_checks_close_button:
                        ChecksActivity.super.finish();
                        break;
                    case R.id.activity_checks_delete_button:
                        deleteBtnClick();
                        break;
                }
            }
        };

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearDialog, int monthDialog, int dayDialog) {
                year = yearDialog;
                month = monthDialog;
                day = dayDialog;

                getArrayCheckByDateFromServer(year, month, day);

                listViewChecks.removeHeaderView(header);
                header = createHeader ("Дата: " + day + "/" + getCorrectMonth(month) + "/" + year);
                listViewChecks.addHeaderView(header);

                adapter.notifyDataSetChanged();
            }
        };

        closeBtn.setOnClickListener(onClickListener);
        deleteBtn.setOnClickListener(onClickListener);

        if (userRole.equals(Role.ADMIN) || userRole.equals((Role.MANAGER))) {
            closeBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
            dateBtn.setVisibility(View.VISIBLE);
        } else {
            closeBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
            dateBtn.setVisibility(View.INVISIBLE);
        }
    }

    private void getArrayCheckByDateFromServer(int year, int month, int day) {
        month = month + 1;
        String date = day + "/" + month + "/" + year;

        checkDtoArrayList.clear();
        checksListStr.clear();
        Calendar calendar = Calendar.getInstance();

        try {
            checkDtoArrayList = getChecksByDate(date);
            for (int i=0; i < checkDtoArrayList.size(); i++){
                calendar.setTimeInMillis(checkDtoArrayList.get(i).getDateStamp().getTime());
                checksListStr.add ("#" + checkDtoArrayList.get(i).getId()
                        + "     " + checkDtoArrayList.get(i).getSum().toString()
                        + "     " + calendar.get(Calendar.DAY_OF_MONTH)
                        + "/" + calendar.get(Calendar.MONTH)
                        + "/" + calendar.get(Calendar.YEAR)
                        + "     " + calendar.get(Calendar.HOUR_OF_DAY)
                        + ":" + calendar.get(Calendar.MINUTE)
                        + ":" + calendar.get(Calendar.SECOND));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View createHeader (String s){
        View view = getLayoutInflater().inflate(R.layout.check_list_header,null);
        ((TextView)view.findViewById(R.id.textViewHeaderText)).setText(s);
        return view;
    }

    View createFooter (){
        View view = getLayoutInflater().inflate(R.layout.check_list_footer, null);
        return view;
    }

    //получаем чек с сервера по дате из диалога даты
    @Nullable
    private ArrayList<CheckDto> getChecksByDate(String date) {
        ArrayList<CheckDto> сhecksByDateFromServer = new ArrayList<>();
        String result="";
        SendClass sendClass = new SendClass();
        sendClass.execute(prepareSendObjCheckByDate(date));
        try {
            result=sendClass.get();

            if (!result.equals("")) {
                Log.d(TAG, "Ответ от сервера по запросу чеков по дате: " + result);
                String[] checksArrStr = result.split("#");
                if (checksArrStr.length!=0){
                    for (String checksStr : checksArrStr){
                        CheckDto check = new CheckDto(checksStr);
                        сhecksByDateFromServer.add(check);
                        Log.d(TAG, "Создан чек по дате из ответа сервера: " + check.toString());
                    }
                Log.d(TAG, "Создан объект списка чеков по дате от сервера: "
                        + сhecksByDateFromServer.toString());
                    Log.d(TAG, "Длина списка чеков: " + сhecksByDateFromServer.size());
                }
            }

        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return сhecksByDateFromServer;
    }

    private ConnectionSettingsObj prepareSendObjCheckByDate(String date) {
        ConnectionSettingsObj connectionSettingsObj;
        String requestStr = ConnectionType.READ_CHECK_BY_DATE.toString() + "#" + date;
        connectionSettingsObj = new ConnectionSettingsObj(requestStr,urlServer,portServer);
        return connectionSettingsObj;
    }

    private void deleteBtnClick(){
        chosen = listViewChecks.getCheckedItemPositions();
        if ((chosen.size() > 0)){
            CheckDelConfirmDialogFragment checkDelConfirmDialogFragment = new CheckDelConfirmDialogFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            int checkedCountChekcs = 0;
            for (int i = 1; i < listViewChecks.getCount() - 1; i++) {
                if (chosen.get(i)) {
                    checkedCountChekcs++;
                }
            }
            checkDelConfirmDialogFragment.setCountCheckText(checkedCountChekcs + "шт.");
            checkDelConfirmDialogFragment.show (transaction, "checkDialog");
        }
    }

    public void checkDelConfirmOkClicked() {
        chosen = null;
        chosen = listViewChecks.getCheckedItemPositions();
        String resStr;
        resStr = "false";
        SendClass sendClass = new SendClass();
        String strChecks = "";

        for (int i = 0; i < listViewChecks.getCount() - 1 ; i++) {

            if (chosen.get(i+1)) {
                strChecks = strChecks +  checkDtoArrayList.get(i).getId().toString() + "#";
                listViewChecks.setItemChecked(i+1, false);
            }
        }

        sendClass.execute(prepareSendObjDeleteCheckById(strChecks));

        try {
            resStr = sendClass.get();
            Log.d (TAG, "Результат удаления чека (в основном окне чеков): " + resStr);
            getArrayCheckByDateFromServer(year, month, day);
        }
        catch (InterruptedException | ExecutionException e){
            Log.d (TAG, e.toString());
        }
    }

    //TODO нужно добавить подтверждение удаления через диалог
    public void goodsDelCheckClicked(CheckDto currCheck, int position){

        String resStr;
        resStr = "false";
        SendClass sendClass = new SendClass();
        String strChecks = "";

        if (currCheck != null) {
            strChecks = currCheck.getId().toString() + "#";
        }

        sendClass.execute(prepareSendObjDeleteCheckById(strChecks));

        try {
            resStr = sendClass.get();
            Log.d (TAG, "Результат удаления чека (в диалоге детализации чека): " + resStr);
        }
        catch (InterruptedException | ExecutionException e){
            Log.d (TAG, e.toString());
        }

        getArrayCheckByDateFromServer(year, month, day);

//        Log.d (TAG, "checkListStr после удаления из детализации чека: "
//                +  checksListStr.toString());
//        Log.d (TAG, "checkDtoArrayList после удаления из детализации чека: "
//                + checkDtoArrayList.toString());

        adapter.notifyDataSetChanged();


    }

    private ConnectionSettingsObj prepareSendObjDeleteCheckById (String idsChecks){
        ConnectionSettingsObj connectionSettingsObj;
        String requestStr = ConnectionType.DELETE_CHECK_BY_ID.toString() + "#" + idsChecks;
        connectionSettingsObj = new ConnectionSettingsObj(requestStr, urlServer, portServer);
        return connectionSettingsObj;
    }

    private int getCorrectMonth(int month){
        int correctMonth = month +1;
        return correctMonth;
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Закрытие окна чеков");
        super.onStop();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        if (position !=0){

            CheckDto checkDto = checkDtoArrayList.get(position-1);

            CheckGoodsDialogFragment checkGoodsDialogFragment = new CheckGoodsDialogFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            checkGoodsDialogFragment.setPosition(position -1);
            checkGoodsDialogFragment.setCurrCheck(checkDto);
            checkGoodsDialogFragment.show (transaction, "checkGoodsDialog");

            return true;
        } else {
            return false;
        }
    }
}
