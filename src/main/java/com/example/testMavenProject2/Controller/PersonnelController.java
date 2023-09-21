package com.example.testMavenProject2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.testMavenProject2.Entity.PersonnelEntity;
import com.example.testMavenProject2.Repository.PersonnelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/crud")
@Controller
public class PersonnelController {

	@Autowired
    private PersonnelRepository personnelRepository;

	@Autowired
    ObjectMapper objectMapper = new ObjectMapper(); 

	

	@RequestMapping(value = "/formulaire", method = RequestMethod.GET)
	public String inscription(Model model){
		PersonnelEntity personnel = new PersonnelEntity();
		model.addAttribute("personnel", personnel);
		return "formPersonnel";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPersonnelBis(@ModelAttribute PersonnelEntity personnel,Model model){
    	// RequestParam / RequestBody
		try {
            String personnelJson = objectMapper.writeValueAsString(personnel);
            System.out.println("Etape 1" + personnelJson);
            
        } catch (Exception e) {
        }
		personnelRepository.save(personnel);
		List<PersonnelEntity> personnels = listPersonnel(model);
		model.addAttribute("personnels", personnels);
		return "personnel";
	}

    

	@RequestMapping(value="delete/{id}", method = RequestMethod.GET)
	public String deletePersonnel(@PathVariable(value = "id") Long id, Model model){
		System.err.println(id);
		try {
			personnelRepository.deleteById(id);
			List<PersonnelEntity> personnels = listPersonnel(model);
			model.addAttribute("personnels", personnels);
			return "personnel";
		} catch (Exception e) {
			 return "erreur";
		}
		
	}

	@RequestMapping(value="/read/{id}", method = RequestMethod.GET)
	public String getPersonnnelById(@PathVariable(value="id")Long id, Model model){
		try {
			PersonnelEntity personnel = personnelRepository.findById(id).get();
			model.addAttribute("personnel", personnel);
			return "updatePersonnel";
		} catch (Exception e) {
			return "erreur";
		}
	}

	@RequestMapping(value="read", method = RequestMethod.GET)
	public String getAllPersonnnel(Model model){
		// List<PersonnelEntity> personnels = personnelRepository.findAll();
		List<PersonnelEntity> personnels = listPersonnel(model);
		model.addAttribute("personnels", personnels);
		return "personnel";
	}

	@RequestMapping(value="update/{id}", method = RequestMethod.POST)
	public String updatePersonnel(@ModelAttribute PersonnelEntity personnel, @PathVariable(value="id")Long id, Model model){
		try {
			String personnelJson = objectMapper.writeValueAsString(personnel);
			System.out.println("Etape 1" + personnelJson);
			personnelRepository.save(personnel);
			List<PersonnelEntity> personnels = listPersonnel(model);
			model.addAttribute("personnels", personnels);
			return "personnel";
		} catch (Exception e) {
			// TODO: handle exception
			return "erreur";
		}	
	}

	public List<PersonnelEntity> listPersonnel(Model model){
		List<PersonnelEntity> personnels = personnelRepository.findAll();
		return personnels;
	}
}
