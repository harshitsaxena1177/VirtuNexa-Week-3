package com.example.habit_tracker.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.habit_tracker.model.Habit;

@Dao
public interface HabitDao {
    @Insert
    void insert(Habit habit);

    @Query("SELECT * FROM habits ORDER BY id DESC")
    List<Habit> getAllHabits();

    @Query("UPDATE habits SET streak = streak + 1 WHERE id = :habitId")
    void updateStreak(int habitId);
}