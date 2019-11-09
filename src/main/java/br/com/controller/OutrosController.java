package br.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outros")
public class OutrosController {

	@GetMapping("/forms")
	public String forms(){
		return "pages/outros/forms";
	}
	
	@GetMapping("/tables")
	public String tables(){
		return "pages/outros/tables";
	}
	
	@GetMapping("/buttons")
	public String botons(){
		return "pages/outros/buttons";
	}
	
	@GetMapping("/notifications")
	public String notifications(){
		return "pages/outros/notifications";
	}
	
	@GetMapping("/typography")
	public String typography(){
		return "pages/outros/typography";
	}
	
	@GetMapping("/icons")
	public String icons(){
		return "pages/outros/icons";
	}
	
	@GetMapping("/grid")
	public String grid(){
		return "pages/outros/grid";
	}
	
	@GetMapping("/panels-wells")
	public String panelsWells(){
		return "pages/outros/panels-wells";
	}
	
	@GetMapping("/morris")
	public String morris(){
		return "pages/outros/morris";
	}
	
	@GetMapping("/flot")
	public String flot(){
		return "pages/outros/flot";
	}
}
