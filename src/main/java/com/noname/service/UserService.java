package com.noname.service;

import com.noname.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends BaseService<User>{

    String userImport(List<ArrayList<String>> userExcle);
}
