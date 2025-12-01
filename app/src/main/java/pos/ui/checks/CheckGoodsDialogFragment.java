package pos.ui.checks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.pos_ver_01.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;
import lombok.Setter;
import pos.Dto.CheckDto;
import pos.Dto.GoodsDto;
import pos.Entities.Goods;

@Setter
public class CheckGoodsDialogFragment extends DialogFragment {
    private static final String TAG = "logsCheckGoodsDialogFragment";
    private CheckDto currCheck = new CheckDto ();
    private int position;
    public TextView title;
    public TextView goods;
    public Button btnCancel;
    public Button btnDel;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Детализация чека");
        View view = inflater.inflate(R.layout.check_detales_and_delete,null);
        title = (TextView) view.findViewById(R.id.сheckDetalesTitle);
        goods = (TextView) view.findViewById(R.id.goodsListInCheck);
        btnCancel = (Button) view.findViewById(R.id.btnCancelCheckDetales);
        btnDel = (Button) view.findViewById(R.id.btnDelCheckDetales);

        title.setText("Перечень товаров чека №" + currCheck.getId() + ":");

        appendGoodsFromCheck(currCheck);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                CheckGoodsDialogFragment.super.dismiss();
                ((ChecksActivity) getActivity()).goodsDelCheckClicked(currCheck, position);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckGoodsDialogFragment.super.dismiss();
            }
        });

        return view;
    }

    private void appendGoodsFromCheck(CheckDto checkDto){
        List<Goods> goodsArr = checkDto.getGoodsList();

        for (Goods goods: goodsArr){
            String goodsStr = "";
            goodsStr = goods.getPublicName() + "    "
                    +  goods.getQuantityGoods() + "x"
                    +  goods.getPrize() + "     "
                    +  goods.getPrize().multiply(BigDecimal.valueOf(goods.getQuantityGoods()))
                    +  "\n";
            this.goods.append(goodsStr);

        }
        goods.append("___________________________" + "\n");
        goods.append("Итого: ");
        goods.append(checkDto.getSum().toString());
    }
}
