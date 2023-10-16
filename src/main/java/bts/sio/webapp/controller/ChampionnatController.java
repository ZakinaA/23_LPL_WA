package bts.sio.webapp.controller;


import bts.sio.webapp.model.Championnat;
import bts.sio.webapp.service.ChampionnatService;
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
public class ChampionnatController {

    @Autowired
    private ChampionnatService championnatService;

    /*mettre lien avec palmares*/

    @GetMapping("/championnats")
    public String home(Model model) {
        Iterable<Championnat> listChampionnat = championnatService.getChampionnats();
        model.addAttribute("championnat", listChampionnat);
        return "championnat/ListChampionnat";
    }

    @GetMapping("/createChampionnat")
    public String createChampionnat(Model model) {
        Championnat c = new Championnat();
        model.addAttribute("championnat", c);
       /*mettre lien palmares*/


        return "championnat/formNewChampionnat";
    }

    @GetMapping("/updateChampionnat/{id}")
    public String updateChampionnat(@PathVariable("id") final int id, Model model) {
        Championnat ch = championnatService.getChampionnat(id);
        model.addAttribute("championnat", ch);
        return "championnat/formUpdateChampionnat";
    }

    @GetMapping("/deleteChampionnat/{id}")
    public ModelAndView deleteAthlete(@PathVariable("id") final int id) {
        championnatService.deleteChampionnat(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveChampionnat")
    public ModelAndView saveChampionnat(@ModelAttribute Championnat championnat) {
        System.out.println("controller save=" + championnat.getLibelle());
        if(championnat.getId() != null) {
            Championnat current = championnatService.getChampionnat(championnat.getId());
            championnat.setLibelle(current.getLibelle());
        }
        championnatService.saveChampionnat(championnat);
        return new ModelAndView("redirect:/");
    }


}
