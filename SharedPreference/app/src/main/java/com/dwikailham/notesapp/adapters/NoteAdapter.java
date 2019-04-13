package com.dwikailham.notesapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dwikailham.notesapp.R;
import com.dwikailham.notesapp.models.Constant;
import com.dwikailham.notesapp.models.Note;
import com.dwikailham.notesapp.models.Settings;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context context;
    private List<Note> notes;
    private int layout;
    private Settings settings;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        this.settings = new Settings(context);
    }

    public NoteAdapter(Context context, List<Note> notes, int layout) {
        this.context = context;
        this.notes = notes;
        this.layout = layout;
        this.settings = new Settings(context);
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getItemViewType(int position) {
        return layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (getItemViewType(i)) {
            case Constant.LAYOUT_MODE_GRID:
                View gridView = LayoutInflater.from(context)
                        .inflate(R.layout.item_note_grid, viewGroup, false);
                return new GridViewHolder(gridView);

            default:
                View listView = LayoutInflater.from(context)
                        .inflate(R.layout.item_note_list, viewGroup, false);
                return new ListViewHolder(listView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Note note = notes.get(i);
        viewHolder.onBindViewHolder(note);
    }

    @Override
    public int getItemCount() {
        return (notes != null) ? notes.size() : 0;
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected abstract void onBindViewHolder(Note note);
    }

    public class ListViewHolder extends ViewHolder {

        TextView titleText;
        TextView dateText;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            dateText = itemView.findViewById(R.id.text_date);
            float textSize = settings.getTextSize();
            titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }

        @Override
        protected void onBindViewHolder(Note note) {
            titleText.setText(note.getTitle());
            dateText.setText(note.getFormattedDate());
        }
    }

    public class GridViewHolder extends ViewHolder {

        TextView titleText;
        TextView contentText;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            contentText = itemView.findViewById(R.id.text_content);
            float textSize = settings.getTextSize();
            titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }

        @Override
        protected void onBindViewHolder(Note note) {
            titleText.setText(note.getTitle());
            contentText.setText(note.getContent());
        }
    }
}