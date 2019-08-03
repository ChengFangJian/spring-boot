package com.example.service.impl;

import com.example.service.UserService;
import com.generator.tables.pojos.TUser;
import com.generator.tables.records.TUserRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.UpdateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    DSLContext dsl;

//    @Autowired
//    private TUserDao userDao;

    com.generator.tables.TUser u =  com.generator.tables.TUser.T_USER.as("u");

    @Override
    public void insert(TUser user) {
        // mysql 插入数据不能使用别名
        dsl.insertInto(new com.generator.tables.TUser())
                .columns(u.MOBILE,u.USER_NAME,u.PASSWORD)
                .values(user.getMobile(),user.getUserName(),user.getPassword())
                .execute();
    }

    @Override
    public void update(TUser user) {
        // 构建update语句
        UpdateQuery<TUserRecord> update = dsl.updateQuery(u);
        update.addValue(u.USER_NAME, user.getUserName());
        update.addValue(u.MOBILE, user.getMobile());
        update.addConditions(u.ID.eq(user.getId()));
        update.execute();
    }

    @Override
    public void delete(Integer id) {
        dsl.delete(u).where(u.ID.eq(id));
    }

    @Override
    public TUser getUserById(Integer id) {
        List<TUser> result = dsl.select(u.ID, u.USER_NAME, u.MOBILE)
                .from(u)
                .where(u.ID.eq(id))
                .fetch()
                .into(com.generator.tables.pojos.TUser.class);
        return result.get(0);
    }

    @Override
    public List<TUser> findUser(HashMap<String, Object> map) {
        List<TUser> result = dsl.select().from(u)
                .orderBy(u.ID.desc())
                .limit((Integer) map.get("pageSize"))
                .offset((Integer) map.get("pageNum"))
                .fetch()
                .into(TUser.class);

        return result;
    }
}
