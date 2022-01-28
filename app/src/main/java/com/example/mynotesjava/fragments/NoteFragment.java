package com.example.mynotesjava.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.mynotesjava.MyNoteRecyclerViewAdapter;
import com.example.mynotesjava.R;
import com.example.mynotesjava.data.NoteEntity;
import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NoteEntity> noteEntityList;
    private MyNoteRecyclerViewAdapter adapterNote;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numColums = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numColums, StaggeredGridLayoutManager.VERTICAL));
            }

            noteEntityList = new ArrayList<>();
            noteEntityList.add(new NoteEntity("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteEntityList.add(new NoteEntity("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteEntityList.add(new NoteEntity("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            noteEntityList.add(new NoteEntity("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteEntityList.add(new NoteEntity("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteEntityList.add(new NoteEntity("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            noteEntityList.add(new NoteEntity("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteEntityList.add(new NoteEntity("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteEntityList.add(new NoteEntity("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            noteEntityList.add(new NoteEntity("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteEntityList.add(new NoteEntity("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteEntityList.add(new NoteEntity("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            adapterNote = new MyNoteRecyclerViewAdapter(noteEntityList, getActivity());
            recyclerView.setAdapter(adapterNote);
        }
        return view;
    }
}