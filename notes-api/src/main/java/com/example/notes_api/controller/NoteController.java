package com.example.notes_api.controller;

import com.example.notes_api.model.Note;
import com.example.notes_api.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService)
    {
        this.noteService=noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {

        return noteService.getAllNotes();
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }


}

