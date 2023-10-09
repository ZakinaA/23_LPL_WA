package bts.sio.webapp.controller;
import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Medaille;
import bts.sio.webapp.model.Pays;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.service.AthleteService;
import bts.sio.webapp.service.MedailleService;
import bts.sio.webapp.service.PaysService;
import bts.sio.webapp.service.SportService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class MedailleController {

    @Autowired
    private MedailleService medailleservice;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Medaille> listMedailles = medailleservice.getMedailles();
        model.addAttribute("Medailles", listMedailles);
        return "home";
    }

    @GetMapping("/createMedaille")
    public String createMedaille(Model model) {
        Medaille a = new Medaille();
        model.addAttribute("medaille", a);

        return "medaille/formNewMedaille";
    }

    @GetMapping("/updateMedaille/{id}")
    public String updateMedaille(@PathVariable("id") final int id, Model model) {
        Medaille a = medailleservice.getMedaille(id);
        model.addAttribute("medaille", a);
        return "medaille/formUpdateMedaille";
    }

    @GetMapping("/deleteMedaille/{id}")
    public ModelAndView deleteMedaille(@PathVariable("id") final int id) {
        medailleservice.deleteMedaille(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveMedaille")
    public ModelAndView saveMedaille(@ModelAttribute Medaille medaille) {
        System.out.println("controller save=" + medaille.getLibelle());
        if(medaille.getId() != null) {
            Medaille current = medailleservice.getMedaille(medaille.getId());
            medaille.setLibelle(current.getLibelle());
        }
        medailleservice.saveMedaille(medaille);
        return new ModelAndView("redirect:/");
    }
}