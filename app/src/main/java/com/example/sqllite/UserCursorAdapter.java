package com.example.sqllite;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserCursorAdapter extends RecyclerView.Adapter<UserCursorAdapter.UserViewHolder> {

    private Cursor cursor;

    public UserCursorAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    // Mise à jour du curseur
    public void updateCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close(); // Libère le curseur précédent
        }
        cursor = newCursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));

            holder.textViewName.setText(name);
            holder.textViewAge.setText("Âge : " + age);
        }
    }

    @Override
    public int getItemCount() {
        return (cursor == null) ? 0 : cursor.getCount();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewAge;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
        }
    }
}
