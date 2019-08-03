package com.example.service.impl;

import com.example.service.UserService;
import com.generator.tables.pojos.TUser;
import com.generator.tables.records.TUserRecord;
import org.jooq.DSLContext;
import org.jooq.UpdateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<TUser> findUser(HashMap<String, Object> params, int pageNum, int pageSize) {
        StringBuilder builder = new StringBuilder();
        List<Object> objectList = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                objectList.add(entry.getValue());
                if (builder.length() == 0) {
                    builder.append(entry.getKey()).append(" = ?");
                } else {
                    builder.append(" and ").append(entry.getKey()).append(" = ?");
                }
            }
        }

        List<TUser> result = dsl.select().from(u)
                .where(builder.toString(), objectList.toArray())
                .orderBy(u.ID.desc())
                .limit(pageSize)
                .offset(pageNum)
                .fetch()
                .into(TUser.class);

        return result;
    }
}
