package com.example.habit_tracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habit_tracker.adapter.HabitAdapter;
import com.example.habit_tracker.database.HabitDatabase;
import com.example.habit_tracker.model.Habit;
import com.example.habit_tracker.ui.AddHabitActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewHabits;
    private HabitAdapter habitAdapter;
    private HabitDatabase habitDatabase;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHabits = findViewById(R.id.recyclerViewHabits);
        FloatingActionButton fabAddHabit = findViewById(R.id.fabAddHabit);
        habitDatabase = HabitDatabase.getInstance(this);

        // Initialize RecyclerView
        habitAdapter = new HabitAdapter();
        recyclerViewHabits.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHabits.setAdapter(habitAdapter);

        // Fetch habits asynchronously
        loadHabits();

        fabAddHabit.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddHabitActivity.class));
        });
    }
    private void loadHabits() {
        executorService.execute(() -> {
            List<Habit> habitList = habitDatabase.habitDao().getAllHabits();
            runOnUiThread(() -> {
                if (habitList != null) {
                    habitAdapter.updateHabits(habitList);
                }
            });
        });
    }



}

