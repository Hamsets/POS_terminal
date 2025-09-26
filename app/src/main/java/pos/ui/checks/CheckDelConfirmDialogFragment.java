package pos.ui.checks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import lombok.NonNull;
import lombok.Setter;

@Setter
public class CheckDelConfirmDialogFragment extends DialogFragment {
    private static final String TAG = "logsCheckDelConfirmDialogFragment";
    private String countCheckText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(countCheckText)
                .setTitle("Вы точно хотите удалить чек(и):")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ((ChecksActivity) getActivity()).checkDelConfirmOkClicked();
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
