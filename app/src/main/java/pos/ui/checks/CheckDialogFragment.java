package pos.ui.checks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import lombok.NonNull;
import lombok.Setter;
import pos.Connection.SendClass;
import pos.Dto.CheckDto;

@Setter
public class CheckDialogFragment extends DialogFragment {
    private static final String TAG = "logsCheckDialogFragment";
    private String titleDialg;
//    private ArrayList<CheckDto> checkedListChecks = new ArrayList<CheckDto>();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(titleDialg)
                .setTitle("Вы точно хотите удалить чек(и):")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        ((ChecksActivity) getActivity()).okClicked();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        ((ChecksActivity) getActivity()).cancelClicked();
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
