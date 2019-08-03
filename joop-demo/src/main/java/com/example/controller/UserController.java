package com.example.controller;

import com.example.service.UserService;
import com.generator.tables.pojos.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET,value = "/delete/{id}")
    public void delete(@PathVariable("id")int id){
        userService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/insert")
    public void insert(TUser user){
        userService.insert(user);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/update/{id}")
    public void update(TUser user){
        userService.update(user);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public TUser get(@PathVariable("id")int id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/findUser")
    public List<TUser> findUser(int pageNum, int pageSize){
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        return userService.findUser(map);
    }

}
