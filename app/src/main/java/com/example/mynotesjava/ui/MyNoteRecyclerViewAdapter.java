package com.example.mynotesjava.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynotesjava.R;
import com.example.mynotesjava.db.entity.NoteEntity;
import com.example.mynotesjava.databinding.FragmentNoteBinding;
import java.util.List;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context ctx;

    public MyNoteRecyclerViewAdapter(List<NoteEntity> items, Context context) {
        mValues = items;
        this.ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(holder.mItem.getTitle());
        holder.mContentView.setText(holder.mItem.getContent());

        if (holder.mItem.isFavorite()) {
            holder.ivFavorite.setImageResource(R.drawable.ic_baseline_bookmark_24);
        }

        holder.ivFavorite.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNewNotes(List<NoteEntity> newNotes) {
        this.mValues = newNotes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;
        public final TextView mContentView;
        public final ImageView ivFavorite;
        public NoteEntity mItem;

        public ViewHolder(FragmentNoteBinding binding) {
            super(binding.getRoot());
            mTitle = binding.titleTv;
            mContentView = binding.contentTv;
            ivFavorite = binding.savedIv;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}