package ru.dvkorol.simbirtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dvkorol.simbirtest.models.SimbirData;
import ru.dvkorol.simbirtest.service.impl.SimbirDataServiceImpl;

import javax.validation.Valid;

@Controller
public class SimbirController {

    @Autowired
    private SimbirDataServiceImpl simbirDataService;

    @GetMapping("/index")
    public String index (@ModelAttribute("simbirData") SimbirData simbirData, Model model){
        return "index";
    }

    @PostMapping("/add")
    public String dataPostAdd (@ModelAttribute("simbirData") @Valid SimbirData simbirData,
                                BindingResult bindingResult, Model model){

        //@Valid проверяет корректность значений
        //BindingResult bindingResult ошибка валидации. Должен быть после валидир-го объекта
        if (bindingResult.hasErrors()){
            return "/index";
        }

        simbirDataService.addData(simbirData);

        return "redirect:/index";
    }

}
