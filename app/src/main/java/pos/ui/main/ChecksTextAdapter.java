package pos.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pos_ver_01.R;

import java.util.ArrayList;

import pos.Dto.CheckDto;

public class ChecksTextAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<CheckDto> checksDtoArrayList = new ArrayList<>();
    public ChecksTextAdapter (Context c) {mContext = c;}
    public void  add (CheckDto checkDto) {checksDtoArrayList.add(checkDto);}
    public int getCount() {return checksDtoArrayList.size();}
    public Object getItem (int position) {return checksDtoArrayList.get(position);}
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View checksListView;

        if (convertView == null) {
            checksListView = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
//            checksListView = inflater.inflate(R.layout.cell_checks, parent, false);
        } else {
            checksListView = (View) convertView;
        }

//        CheckBox checkBoxView = (CheckBox)  checksListView.findViewById(R.id.checkBox_cell_checks);
//        TextView idTextView = (TextView) checksListView.findViewById(R.id.text_id_cell_check);
//        TextView costTextView = (TextView) checksListView.findViewById(R.id.text_cost_cell_check);
//        TextView timeTextView = (TextView) checksListView.findViewById(R.id.text_time_cell_check);

//        checkBoxView.setChecked(false);
//        idTextView.setText(checksDtoArrayList.get(position).getId().toString());
//        costTextView.setText(checksDtoArrayList.get(position).getSum().toString());
//        timeTextView.setText(checksDtoArrayList.get(position).getDateStamp().toString());

        return checksListView;
    }


}
