package com.example.Noteapp.backend.controller;

import com.example.Noteapp.backend.entity.Note;
import com.example.Noteapp.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/note/addNote")
    public String addNote(@ModelAttribute Note note){
        return noteService.saveNote(note);
    }

    @GetMapping("/note/getNote")
    public Note getNote(@RequestParam int id){
        return noteService.getNote(id);
    }

    @GetMapping("/note/getAllNotes")
    public List<Note> getAllNotes(@RequestParam int userId){
        return noteService.getAllNotes(userId);
    }
}
