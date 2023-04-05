package com.yogeshaswar.expensetracking.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    @ColumnInfo(name = "title")
    private String NoteTitle;
    @ColumnInfo(name = "note")
    private String Note;
    @ColumnInfo(name = "pinned")
    private boolean isPinned = false;

    public NoteModel(int id, String noteTitle, String note, boolean isPinned) {
        this.id = id;
        NoteTitle = noteTitle;
        Note = note;
        this.isPinned = isPinned;
    }
    @Ignore
    public NoteModel(String noteTitle, String note, boolean isPinned) {
        NoteTitle = noteTitle;
        Note = note;
        this.isPinned = isPinned;
    }
    @Ignore
    public NoteModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}

