package com.mustafakaplan.notalma;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mustafakaplan.entity.Note;
import com.mustafakaplan.service.NoteService;


@Controller
public class HomeController 
{
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("baslik", "Not Alma");
		model.addAttribute("serverTime","/"  ); 
		
		return "index";
	}
	
	@RequestMapping(value = "/detay/{id}", method = RequestMethod.GET)
	public String mustafa(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("id",id);
		
		return "detail";
	}
	
	@RequestMapping(value = "/ekle", method = RequestMethod.GET)
	public String ekle(Model model) {
		
		
		return "addNote";
	}
	
	@RequestMapping(value = "/error_404", method = RequestMethod.GET)
	public String error(Model model) {
		 
		return "error_404";
	}
	
	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note, HttpServletRequest request)
	{
		noteService.createNote(note, request);
		
		return new ResponseEntity<>("OK", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateNote", method = RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody Note note, HttpServletRequest request)
	{
		Note oldNote = noteService.getFindByNoteId(note.getId());
		oldNote.setContent(note.getContent());
		oldNote.setTitle(note.getTitle());
		
		noteService.updateNote(oldNote, request);
		
		return new ResponseEntity<>("OK", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note, HttpServletRequest request)
	{
		Note oldNote = noteService.getFindByNoteId(note.getId());
		
		noteService.deleteNote(oldNote, request);
		
		return new ResponseEntity<>("OK", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getNotes", method = RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes(HttpServletRequest request)
	{

		return new ResponseEntity<>(noteService.getAll((long) 1), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getNote", method = RequestMethod.POST)
	public ResponseEntity<Note> getNote(@RequestBody String id, HttpServletRequest request)
	{

		return new ResponseEntity<>(noteService.getFindByNoteId(Long.parseLong(id)), HttpStatus.CREATED);
	}
}
