package com.feng.ucenterservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @Author shf
 * @Date 2020/7/8 12:18
 */
public class CodeGenerator {
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        //String projectPath = System.getProperty("user.dir");
        //gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir("C:\\Users\\shf\\Desktop\\projects\\online_edu_parent\\service\\service_ucenter" + "/src/main/java");
        gc.setAuthor("shf");
        gc.setOpen(false);//在资源管理器中打开
        gc.setFileOverride(false); // 是否覆盖已经存在的目录
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.ASSIGN_ID);//主键随数据库自增
        gc.setDateType(DateType.ONLY_DATE);//使用jdk1.8新的time包
        gc.setSwagger2(true);//开启swagger注解
        generator.setGlobalConfig(gc);

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/online_edu?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        generator.setDataSource(dsc);

        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("ucenterservice");//设置模块
        pc.setParent("com.feng");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        generator.setPackageInfo(pc);
        //模板配置
        TemplateConfig templateConfig=new TemplateConfig();


        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("ucenter_member"); // 设置要映射的表名,接受参数可变，可以多张表
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        //自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2
        generator.setStrategy(strategy);

        generator.execute();

    }
}