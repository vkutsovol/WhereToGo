package com.luxoft.wheretogo.controller;

import com.luxoft.wheretogo.controller.editor.CategoryEditor;
import com.luxoft.wheretogo.model.Category;
import com.luxoft.wheretogo.model.Event;
import com.luxoft.wheretogo.model.User;
import com.luxoft.wheretogo.repositories.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	@Autowired
	@Qualifier("categories")
	private AbstractRepository categoryRepository;

	@Autowired
	@Qualifier("events")
	private AbstractRepository eventRepository;

	@Autowired
	private CategoryEditor categoryEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, categoryEditor);
	}

	@RequestMapping("/categories")
	public String categories(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categories";
	}

	@RequestMapping("/event")
	public String event(@RequestParam(value = "name", required = true) String name, Model model) {
		model.addAttribute("event", eventRepository.getByName(name));
		return "event";
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public String createEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addEvent";
	}


	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	public String addEvent(@ModelAttribute("event") Event event, Model model) {
		event.setOrganizer(new User(1, "Current", "User", "Current", "User"));
		((Category) categoryRepository.getByName(event.getCategory().getName())).getEvents().add(event);
		eventRepository.add(event);
		model.addAttribute("name", event.getName());
		return "event";
	}
}
