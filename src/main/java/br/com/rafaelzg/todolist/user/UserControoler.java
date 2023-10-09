package br.com.rafaelzg.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Modificar
 * public
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserControoler {
    
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel)  {
        System.out.println(userModel.name);
    }
}
