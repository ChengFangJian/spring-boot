/*
 * This file is generated by jOOQ.
 */
package com.generator;


import com.generator.tables.FlywaySchemaHistory;
import com.generator.tables.TUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JooqDemo extends SchemaImpl {

    private static final long serialVersionUID = -832840891;

    /**
     * The reference instance of <code>jooq-demo</code>
     */
    public static final JooqDemo JOOQ_DEMO = new JooqDemo();

    /**
     * The table <code>jooq-demo.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = com.generator.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * 用户表
     */
    public final TUser T_USER = com.generator.tables.TUser.T_USER;

    /**
     * No further instances allowed
     */
    private JooqDemo() {
        super("jooq-demo", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            TUser.T_USER);
    }
}