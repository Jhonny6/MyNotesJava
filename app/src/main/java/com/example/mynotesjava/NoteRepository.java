package com.example.mynotesjava;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mynotesjava.data.NoteRoomDatabase;
import com.example.mynotesjava.db.dao.NoteDao;
import com.example.mynotesjava.db.entity.NoteEntity;

import java.util.List;

import javax.xml.transform.Result;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNotes;
    private LiveData<List<NoteEntity>> allFavoritesNotes;

    public NoteRepository(Application application) {
        NoteRoomDatabase roomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = roomDatabase.noteDao();
        allNotes = noteDao.getAll();
        allFavoritesNotes = noteDao.getAllFavorites();
    }

    public LiveData<List<NoteEntity>> getAll() { return allNotes; }
    public LiveData<List<NoteEntity>> getAllFavoritesNotes() { return allFavoritesNotes; }

    public void insert (NoteEntity noteEntity) {
        new insertAsyncTask(noteDao).execute(noteEntity);
    }

    private static class insertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao noteDaoAsynkTask;

        insertAsyncTask(NoteDao dao) {
            noteDaoAsynkTask = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDaoAsynkTask.insert(noteEntities[0]);
            return null;
        }
    }
}
