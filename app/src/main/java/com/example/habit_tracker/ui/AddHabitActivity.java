package com.example.habit_tracker.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.habit_tracker.R;
import com.example.habit_tracker.database.HabitDatabase;
import com.example.habit_tracker.model.Habit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddHabitActivity extends AppCompatActivity {
    private EditText editTextHabitName;
    private Button buttonSaveHabit;
    private HabitDatabase habitDatabase;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        editTextHabitName = findViewById(R.id.editTextHabitName);
        buttonSaveHabit = findViewById(R.id.buttonSaveHabit);
        habitDatabase = HabitDatabase.getInstance(this);

        buttonSaveHabit.setOnClickListener(view -> saveHabit());
    }

    private void saveHabit() {
        String habitName = editTextHabitName.getText().toString().trim();
        if (!habitName.isEmpty()) {
            Habit newHabit = new Habit(habitName, 0, System.currentTimeMillis());

            executorService.execute(() -> {
                habitDatabase.habitDao().insert(newHabit);
                runOnUiThread(() -> {
                    setResult(RESULT_OK, new Intent());
                    finish();
                });
            });
        }
    }
}
