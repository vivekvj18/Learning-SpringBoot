package com.example.notes_api.service;

import com.example.notes_api.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    public List<Note> getAllNotes()
    {
        return List.of(
                new Note(1L, "Note 1", "Content 1"),
                new Note(2L, "Note 2", "Content 2")
        );
    }

    public Note createNote(Note note) {
        return note;
    }

    public Note getNoteById(Long id) {
        return new Note(
                id,
                "Sample Note",
                "Note with id " + id
        );
    }


}
