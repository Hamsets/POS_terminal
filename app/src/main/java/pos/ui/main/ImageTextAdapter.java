package pos.ui.main;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pos_ver_01.R;

import java.util.ArrayList;

import pos.Dto.GoodsDto;

public class ImageTextAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<GoodsDto> goodsDtoArrayList = new ArrayList<>();

    public ImageTextAdapter(Context c) {
        mContext = c;
    }

    public void add (GoodsDto goodsDto){
        goodsDtoArrayList.add(goodsDto);
    }

    public int getCount() {
        return goodsDtoArrayList.size();
    }

    public Object getItem(int position) {
        return goodsDtoArrayList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.imagepart);
        TextView textView = (TextView) grid.findViewById(R.id.textpart);

        imageView.setImageResource(mContext.getResources().getIdentifier(goodsDtoArrayList.
                get(position).getPathImage(),"drawable",mContext.getPackageName()));
        textView.setText(goodsDtoArrayList.get(position).getPublicName());

        return grid;
    }
}