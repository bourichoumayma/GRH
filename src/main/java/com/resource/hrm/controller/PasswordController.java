package com.resource.hrm.controller;

import com.resource.hrm.entity.User;
import com.resource.hrm.repository.UserRepository;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PasswordController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/password")
    public String password(Model model){
        return "password";
    }

    @PostMapping("/passwrod/change")
    public String changePassword(Model model,@RequestParam String oldpassword,@RequestParam String password,@RequestParam String passwordConfirmation){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByUsername(principal.getUsername());
        if(!user.getPassword().equals("{noop}" + oldpassword)){
            model.addAttribute("passowrdIncorrectError","Ancien mot de passe est incorrect");
            return "password";
        }
        if(StringUtils.isEmpty(password)){
            model.addAttribute("passowrdIncorrectError","les mots de passe ne sont pas identiques");
            return "password";
        }
        if(!password.equals(passwordConfirmation)){
            model.addAttribute("passowrdIncorrectError","les mots de passe ne sont pas identiques");
            return "password";
        }
        user.setPassword("{noop}" + password);
        user.setEnabled(true);
        userRepository.save(user);
        model.addAttribute("successPasswordUpdate","Mot de passe mis à jour avec succès");
        return "password";
    }
}
