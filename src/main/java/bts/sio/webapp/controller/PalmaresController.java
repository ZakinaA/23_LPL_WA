package bts.sio.webapp.controller;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Palmares;
import bts.sio.webapp.model.Pays;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.service.PalmaresService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.templateparser.reader.ParserLevelCommentMarkupReader;

@Data
@Controller
public class PalmaresController {
    @Autowired
    private PalmaresService palmaresService;



    @GetMapping("/Palmares")
    public String home(Model model) {
        Iterable<Palmares> listPalmares = palmaresService.getPalmaress();
        model.addAttribute("palmares", listPalmares);
        return "home";
    }

    @GetMapping("/createPalmares")
    public String createPalmares(Model model) {
        Palmares p = new Palmares();
        model.addAttribute("palmares", p);
        /*Iterable<Sport> listSport = sportService.getSports();
        Iterable<Pays> listPays = paysService.getLesPays();
        model.addAttribute("listPays", listPays);
        model.addAttribute("listSport", listSport);*/

        return "palmares/formNewPalmares";
    }

    @GetMapping("/updatePalmares/{id}")
    public String updatePalmares(@PathVariable("id") final int id, Model model) {
        Palmares a = palmaresService.getPalmares(id);
        model.addAttribute("Palmares", a);
        return "palmares/formUpdatePalmares";
    }

    @GetMapping("/deletePalmares/{id}")
    public ModelAndView deletePalmares(@PathVariable("id") final int id) {
        palmaresService.deletePalmares(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/savePalmares")
    public ModelAndView savePalmares(@ModelAttribute Palmares palmares) {
        System.out.println("controller save=" + palmares.getAnnee());
        if(palmares.getId() != null) {
            Palmares current = palmaresService.getPalmares(palmares.getId());
            palmares.setAnnee(current.getAnnee());
        }
        palmaresService.savePalmares(palmares);
        return new ModelAndView("redirect:/");
    }
}
