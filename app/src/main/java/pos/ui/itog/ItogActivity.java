package pos.ui.itog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_ver_01.R;

public class ItogActivity extends AppCompatActivity {
    Button closeBtn;
    EditText itogEdit;
    private String itogDay;
    private static final String TAG = "logsItogActivity";
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_itog);

        closeBtn = (Button) findViewById(R.id.itogCloseBtn);
        itogEdit = (EditText) findViewById(R.id.itogEdit);

        try {
            itogDay = getIntent().getStringExtra("itogDay");

            if (!itogDay.isEmpty()) {
                itogEdit.setText(itogDay);
            }

        } catch (NullPointerException e) {
            Log.d(TAG, "Дневная выручка отсутствует, игнорируется метод вывода.");}

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.itogCloseBtn:
                        itogCloseBtnClick();
                        break;
                }
            }
        };
        closeBtn.setOnClickListener(onClickListener);
    }

    private void itogCloseBtnClick() {
        ItogActivity.super.finish();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Закрытие окна дневной выручки");
        super.onStop();
    }
}
