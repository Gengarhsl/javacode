package com.example.alipaydemo.config;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * @author hsl
 * 代码生成器
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
public class GeneratorCodeConfig {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in, "utf-8");
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 数据库连接url
        String dbUrl = "jdbc:mysql://10.180.28.200/mg_device";
        // 数据库用户名
        String dbUsername = "root";
        // 数据库密码
        String dbPassword = "123456";
        FastAutoGenerator.create(dbUrl, dbUsername, dbPassword)
                // 全局配置
                .globalConfig(builder -> builder
                        // 禁止打开输出目录
                        .disableOpenDir()
                        // 设置输出目录
                        .outputDir(System.getProperty("user.dir") + "/monitor-center-business-web-api/src/main/java")
                        // 设置作者信息
                        .author("hsl")
                        // 开启 swagger 模式
                        .enableSwagger()
                        // 实体类中时间策略
                        .dateType(DateType.ONLY_DATE)
                        // 生成日期
                        .commentDate("yyyy-MM-dd HH:mm")
                )

                // 包配置
                .packageConfig(builder -> builder
                                // 父包名
                                .parent("com.chinatower")
                                // 父包模块名 注释即为无
//                         .moduleName(scanner("monitor-center-business-web-api"))
                                // Entity 包名
                                .entity("domain")
                                // Service 包名
                                .service("service")
                                // Service Impl 包名
                                .serviceImpl("service.impl")
                                // Mapper 包名 dao层
                                .mapper("mapper")
                                // Mapper XML 包名
                                .xml("mapper")
                                // Controller 包名
                                .controller("controller")
                                // other(String)
//                                .other("utils")
                                // 将Mapper xml生成到resources目录下
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/monitor-center-business-web-api/src/main/resources/mapper/" + scanner("模块名,xml里面生成包")))
                )

                // 策略配置
                .strategyConfig((scanner, builder) -> {
                    // 需要生成得表
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                            // 开启大写命名
                            // .enableCapitalMode()
                            // 开启跳过视图
                            // .enableSkipView()
                            // 增加过滤表前缀
                            .addTablePrefix("ot_", "b_", "iot_", "t_", "sys_")
                            // 增加过滤表后缀
                            .addTableSuffix("_n")
                            // 增加过滤字段前缀
                            .addFieldPrefix("")
                            // 增加过滤字段后缀
                            .addFieldSuffix("")

                            // 实体策略配置
                            .entityBuilder()
                            // 禁用生成 serialVersionUID
//                            .disableSerialVersionUID()
                            // 开启链式模型
                            .enableChainModel()
                            // 开启 lombok 模型
                            .enableLombok()
                            // 开启生成实体时生成字段注解
                            .enableTableFieldAnnotation()
                            // 乐观锁字段名(数据库)
                            .versionColumnName("version")
                            // 乐观锁属性名(实体)
                            .versionPropertyName("version")
                            // 逻辑删除字段名(数据库)
                            .logicDeleteColumnName("is_delete")
                            // 逻辑删除属性名(实体)
                            .logicDeletePropertyName("isDelete")
                            // 	数据库表映射到实体的命名策略 -- 下划线转驼峰命名
                            .naming(NamingStrategy.underline_to_camel)
                            // 数据库表字段映射到实体的命名策略 -- 下划线转驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 雪花id
                            .idType(IdType.ASSIGN_ID)

                            // 阿里巴巴开发规范之创建时间、更新时间 交由mybatis-plus处理，如若交给数据库处理，则取消此设置
                            .addTableFills(new Column("create_time", FieldFill.INSERT), new Column("update_time", FieldFill.INSERT_UPDATE)
                                    , new Column("is_delete", FieldFill.INSERT))
                            // mapper 策略配置
                            .mapperBuilder()
                            // 设置父类
                            .superClass(BaseMapper.class)
                            // 格式化 mapper 文件名称
                            .formatMapperFileName("%sMapper")
                            // 格式化 xml 实现类文件名称
                            .formatXmlFileName("%sMapper")
                            // 开启 @Mapper 注解
                            .enableMapperAnnotation()
                            // 生成通用的resultMap
                            .enableBaseResultMap()

                            // service 策略配置
                            .serviceBuilder()
                            // 格式化 service 接口文件名称
                            .formatServiceFileName("%sService")
                            // 格式化 service 实现类文件名称
                            .formatServiceImplFileName("%sServiceImpl")

                            // controller 策略配置
                            .controllerBuilder()
                            // 格式化文件名称
                            .formatFileName("%sController")
                            // 开启生成@RestController 控制器
                            .enableRestStyle();
                })
                // 以下为解决实体类data注解，若不需要则可以注释
                // ------------------开始-------------------
                /*.templateConfig(builder -> {
                    // 实体类使用我们自定义模板 -- 模板位置
                    builder.entity("templates/myEntity.java");
                })*/
                .templateEngine(new FreemarkerTemplateEngine())
                // ------------------结束-------------------
                // 开始处理
                .execute();
        System.out.println("------end------");
    }


    /**
     * 处理 all 情况
     **/
    public static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}



