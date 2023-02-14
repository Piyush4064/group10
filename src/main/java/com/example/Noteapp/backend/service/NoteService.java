package com.example.Noteapp.backend.service;

import com.example.Noteapp.backend.entity.Note;
import com.example.Noteapp.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public String saveNote(Note note){
        noteRepository.save(note);
        return "Note added";
    }

    public Note getNote(int id){
        return noteRepository.findById(id);
    }

    public List<Note> getAllNotes(int userId){
        return noteRepository.findByUserId(userId);
    }
}
