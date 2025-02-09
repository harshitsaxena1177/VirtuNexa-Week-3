package com.example.habit_tracker.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habit_tracker.R;
import com.example.habit_tracker.model.Habit;
import java.util.ArrayList;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private List<Habit> habitList = new ArrayList<>(); // Initialize habitList to prevent NullPointerException

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.textViewHabitName.setText(habit.getName());
    }

    @Override
    public int getItemCount() {
        return habitList.size(); // Now habitList is never null
    }

    // Update the list safely
    public void updateHabits(List<Habit> newHabitList) {
        if (newHabitList != null) {
            habitList.clear();
            habitList.addAll(newHabitList);
            notifyDataSetChanged();
        }
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHabitName;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHabitName = itemView.findViewById(R.id.textHabitName);


        }
    }
}
