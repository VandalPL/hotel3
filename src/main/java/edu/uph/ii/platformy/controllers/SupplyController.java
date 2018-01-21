package edu.uph.ii.platformy.controllers;

import edu.uph.ii.platformy.controllers.commands.RoomFilter;
import edu.uph.ii.platformy.controllers.commands.SupplyFilter;
import edu.uph.ii.platformy.services.SupplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @ModelAttribute("searchCommand")
    public SupplyFilter getSimpleSearch(){
        return new SupplyFilter();
    }


    @GetMapping(value="/supply.html", params = {"all"})
    public String resetehicleList(@ModelAttribute("searchCommand") SupplyFilter search){
        search.clear();
        return "redirect:supply.html";
    }
    @RequestMapping(value="/supply.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showVehicleList(Model model, @ModelAttribute("searchCommand") SupplyFilter search, Pageable pageable){
        model.addAttribute("supplyList", supplyService.getAllSupplies(search, pageable));
        return "supply";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/supply.html", params={"did"})
    public String deleteSupply(long did, HttpServletRequest request){
        supplyService.deleteSupply(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:supply.html?%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
    }





    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "productionDate", dateEditor);

        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));

        binder.setDisallowedFields("createdDate");//ze względu na bezpieczeństwo aplikacji to pole nie może zostać przesłane w formularzu

    }
}
