package com.example.mynotesjava.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotesjava.MyNoteRecyclerViewAdapter;
import com.example.mynotesjava.R;
import com.example.mynotesjava.data.Note;
import com.example.mynotesjava.interfaces.NotesInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Note> noteList;
    private MyNoteRecyclerViewAdapter adapterNote;
    private NotesInteractionListener mListener;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NotesInteractionListener) {
            mListener = (NotesInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement NotesInteractionListener");
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
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            noteList = new ArrayList<>();
            noteList.add(new Note("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteList.add(new Note("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            noteList.add(new Note("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteList.add(new Note("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            noteList.add(new Note("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteList.add(new Note("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            noteList.add(new Note("Lista de la compra", "pan", true, android.R.color.holo_blue_light));
            noteList.add(new Note("Pendientes", "Puerta no cierra bien", false, android.R.color.holo_orange_dark));
            noteList.add(new Note("Claves de acceso", "Usuario asdasdasdasda contraseña asasfdasdfsdf", true, android.R.color.darker_gray));
            adapterNote = new MyNoteRecyclerViewAdapter(noteList, mListener);
            recyclerView.setAdapter(adapterNote);
        }
        return view;
    }
}