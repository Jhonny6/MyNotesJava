package com.example.mynotesjava;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.mynotesjava.db.entity.NoteEntity;

public class NewNoteDialogFragment extends DialogFragment {

    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }
    private View view;
    private EditText edtTitle, edtContent;
    private RadioGroup rgColor;
    private Switch swFavorite;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_note_dialog_fragment, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInsatanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.new_note_title);
        builder.setMessage(R.string.new_note_label)
                .setPositiveButton(R.string.save_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = edtTitle.getText().toString();
                        String content = edtContent.getText().toString();
                        String color = "azul";
                        switch (rgColor.getCheckedRadioButtonId()) {
                            case R.id.radioButtonRed:
                                color = "rojo"; break;
                            case R.id.radioButtonGreen:
                                color = "verde"; break;
                        }

                        boolean isFavorite = swFavorite.isChecked();

                        NewNoteDialogViewModel mViewModel = new ViewModelProvider(getActivity()).get(NewNoteDialogViewModel.class);
                        mViewModel.insertNote(new NoteEntity(title, content, isFavorite, color));
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.new_note_dialog_fragment, null);
        edtTitle = view.findViewById(R.id.edt_title);
        edtContent = view.findViewById(R.id.edt_content);
        rgColor = view.findViewById(R.id.radio_g_color);
        swFavorite = view.findViewById(R.id.switch_fav);

        builder.setView(view);
        return builder.create();
    }
}