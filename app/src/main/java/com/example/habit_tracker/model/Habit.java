package com.example.habit_tracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habits")  // Creates "habits" table in Room Database
public class Habit {
    @PrimaryKey(autoGenerate = true)
    public int id;  // Unique habit ID

    public String name;  // Habit name (e.g., "Exercise")
    public int streak;  // Consecutive days completed
    public long lastCompleted;  // Timestamp of last completion

    // Constructor
    public Habit(String name, int streak, long lastCompleted) {
        this.name = name;
        this.streak = streak;
        this.lastCompleted = lastCompleted;
    }

    public String getName() {
        return name;  // Returns the habit name
    }

}
