/**

 */

package com.taurus.service;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.taurus.config.MongoManager;
import com.taurus.dao.*;
import com.taurus.entity.DatabaseInfos;
import com.taurus.entity.TableInfos;
import com.taurus.factory.MongoDBCollectionFactory;
import com.taurus.utils.GenUtils;
import com.taurus.utils.PageUtils;
import com.taurus.utils.Query;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysGeneratorService {
    @Autowired
    private GeneratorDao generatorDao;


    public PageUtils queryList(Query query) {
        Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = generatorDao.queryList(query);
        int total = (int) page.getTotal();
        if (generatorDao instanceof MongoDBGeneratorDao) {
            total = MongoDBCollectionFactory.getCollectionTotal(query);
        }
        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    public Map<String, String> queryTable(String tableName) {
        return generatorDao.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }


    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        if (MongoManager.isMongo()) {
            GenUtils.generatorMongoCode(tableNames, zip);
        }


        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public byte[] generatorCodeCustom(DatabaseInfos databaseInfos) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        String[] tableNames = databaseInfos.getTableName().split(",");


        if (databaseInfos.getDriver().contains("mysql")) {
            for (String tableName : tableNames) {
                TableInfos tableInfos = mysqlQuery(tableName, databaseInfos);
                //查询表信息
                Map<String, String> table = tableInfos.getTable();
                //查询列信息
                List<Map<String, String>> columns = tableInfos.getColumns();
                //生成代码
                GenUtils.generatorCode(table, columns, zip);
            }

        }

        if (databaseInfos.getDriver().contains("oracle")) {
            for (String tableName : tableNames) {
                TableInfos tableInfos = oracleQuery(tableName, databaseInfos);
                //查询表信息
                Map<String, String> table = tableInfos.getTable();
                //查询列信息
                List<Map<String, String>> columns = tableInfos.getColumns();
                //生成代码
                GenUtils.generatorCode(table, columns, zip);
            }

        }

        if (databaseInfos.getDriver().contains("sqlserver")) {
            for (String tableName : tableNames) {
                TableInfos tableInfos = sqlServerQuery(tableName, databaseInfos);
                //查询表信息
                Map<String, String> table = tableInfos.getTable();
                //查询列信息
                List<Map<String, String>> columns = tableInfos.getColumns();
                //生成代码
                GenUtils.generatorCode(table, columns, zip);
            }

        }

        if (databaseInfos.getDriver().contains("postgresql")) {
            for (String tableName : tableNames) {
                TableInfos tableInfos = postgreSqlQuery(tableName, databaseInfos);
                //查询表信息
                Map<String, String> table = tableInfos.getTable();
                //查询列信息
                List<Map<String, String>> columns = tableInfos.getColumns();
                //生成代码
                GenUtils.generatorCode(table, columns, zip);
            }

        }


        if (MongoManager.isMongo()) {
            GenUtils.generatorMongoCode(tableNames, zip);
        }


        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


    public TableInfos mysqlQuery(String tableName, DatabaseInfos databaseInfos) {

        PooledDataSource dataSource = new PooledDataSource();

        dataSource.setDriver(databaseInfos.getDriver());
        dataSource.setUrl(databaseInfos.getUrl());
        dataSource.setUsername(databaseInfos.getUserName());
        dataSource.setPassword(databaseInfos.getPassword());

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources("classpath:/mapper/MySQLGeneratorDao.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SqlSession sqlsession = sqlSessionFactory.openSession();

        // 调用数据库操作方法


        MySQLGeneratorDao mapper = sqlsession.getMapper(MySQLGeneratorDao.class);
        List<Map<String, String>> columns = mapper.queryColumns(tableName);

        Map<String, String> table = mapper.queryTable(tableName);


        sqlsession.close();

        return TableInfos.builder().build().setColumns(columns).setTable(table);
    }


    public TableInfos oracleQuery(String tableName, DatabaseInfos databaseInfos) {

        PooledDataSource dataSource = new PooledDataSource();

        dataSource.setDriver(databaseInfos.getDriver());
        dataSource.setUrl(databaseInfos.getUrl());
        dataSource.setUsername(databaseInfos.getUserName());
        dataSource.setPassword(databaseInfos.getPassword());

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources("classpath:/mapper/OracleGeneratorDao.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SqlSession sqlsession = sqlSessionFactory.openSession();

        // 调用数据库操作方法


        OracleGeneratorDao mapper = sqlsession.getMapper(OracleGeneratorDao.class);
        List<Map<String, String>> columns = mapper.queryColumns(tableName);

        Map<String, String> table = mapper.queryTable(tableName);


        sqlsession.close();

        return TableInfos.builder().build().setColumns(columns).setTable(table);
    }


    public TableInfos postgreSqlQuery(String tableName, DatabaseInfos databaseInfos) {

        PooledDataSource dataSource = new PooledDataSource();

        dataSource.setDriver(databaseInfos.getDriver());
        dataSource.setUrl(databaseInfos.getUrl());
        dataSource.setUsername(databaseInfos.getUserName());
        dataSource.setPassword(databaseInfos.getPassword());

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources("classpath:/mapper/PostgreSQLGeneratorDao.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SqlSession sqlsession = sqlSessionFactory.openSession();

        // 调用数据库操作方法


        PostgreSQLGeneratorDao mapper = sqlsession.getMapper(PostgreSQLGeneratorDao.class);
        List<Map<String, String>> columns = mapper.queryColumns(tableName);

        Map<String, String> table = mapper.queryTable(tableName);


        sqlsession.close();

        return TableInfos.builder().build().setColumns(columns).setTable(table);
    }


    public TableInfos sqlServerQuery(String tableName, DatabaseInfos databaseInfos) {

        PooledDataSource dataSource = new PooledDataSource();

        dataSource.setDriver(databaseInfos.getDriver());
        dataSource.setUrl(databaseInfos.getUrl());
        dataSource.setUsername(databaseInfos.getUserName());
        dataSource.setPassword(databaseInfos.getPassword());

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources("classpath:/mapper/SQLServerGeneratorDao.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setDataSource(dataSource);

        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SqlSession sqlsession = sqlSessionFactory.openSession();

        // 调用数据库操作方法


        SQLServerGeneratorDao mapper = sqlsession.getMapper(SQLServerGeneratorDao.class);
        List<Map<String, String>> columns = mapper.queryColumns(tableName);

        Map<String, String> table = mapper.queryTable(tableName);


        sqlsession.close();

        return TableInfos.builder().build().setColumns(columns).setTable(table);
    }


}
