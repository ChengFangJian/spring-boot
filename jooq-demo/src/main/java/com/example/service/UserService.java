package com.example.service;

import com.generator.tables.pojos.TUser;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    void insert(TUser user);

    void update(TUser user);

    void delete(Integer id);

    TUser getUserById(Integer id);

    List<TUser> findUser(HashMap<String, Object> map, int pageNum, int pageSize);
}
