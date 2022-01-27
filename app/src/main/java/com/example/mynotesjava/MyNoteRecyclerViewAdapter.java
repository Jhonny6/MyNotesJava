package com.example.mynotesjava;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mynotesjava.data.Note;
import com.example.mynotesjava.interfaces.NotesInteractionListener;
import com.example.mynotesjava.databinding.FragmentNoteBinding;
import java.util.List;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final NotesInteractionListener mListener;

    public MyNoteRecyclerViewAdapter(List<Note> items, NotesInteractionListener listener) {
        mValues = items;
        this.mListener = listener;
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
            if (null != mListener) {
                mListener.favoriteNoteClick(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;
        public final TextView mContentView;
        public final ImageView ivFavorite;
        public Note mItem;

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