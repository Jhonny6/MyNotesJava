package com.example.mynotesjava.interfaces;

import com.example.mynotesjava.data.Note;

public interface NotesInteractionListener {

    void editNoteClick(Note note);
    void deleteNoteClick(Note note);
    void favoriteNoteClick(Note note);
}
