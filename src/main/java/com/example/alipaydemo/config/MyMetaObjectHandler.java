package com.example.alipaydemo.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * mybatis-plus 自动填充策略
 *
 * @author hsl
 * @since 2024-07-25
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

//    @Resource
//    private SecurityUtils securityUtils;


    @Override
    public void insertFill(MetaObject metaObject) {
        /*try{*/
//            SysUser loginUser = securityUtils.getLoginUser();
            this.strictInsertFill(metaObject, "createTime", DateUtil::date, Date.class);
            this.strictInsertFill(metaObject, "updateTime", DateUtil::date, Date.class);
        this.strictInsertFill(metaObject, "creator", () -> "unregisteredUser", String.class);
        this.strictInsertFill(metaObject, "updater", () -> "unregisteredUser", String.class);
//            this.strictInsertFill(metaObject, "createBy", loginUser::getUserName, String.class);
//            this.strictInsertFill(metaObject, "updateBy", loginUser::getUserName, String.class);
//            this.strictInsertFill(metaObject, "tenantId", securityUtils::getTenantId, Long.class);
        /*}catch (Exception e) {
            this.strictInsertFill(metaObject, "createTime", DateUtil::date, Date.class);
            this.strictInsertFill(metaObject, "updateTime", DateUtil::date, Date.class);
            this.strictInsertFill(metaObject, "createBy", () -> "unregisteredUser", String.class);
            this.strictInsertFill(metaObject, "updateBy", () -> "unregisteredUser", String.class);
            this.strictInsertFill(metaObject, "tenantId", () -> -1L, Long.class);
        }*/
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /*try{*/
//            SysUser loginUser = securityUtils.getLoginUser();
            this.strictUpdateFill(metaObject, "updateTime", DateUtil::date, Date.class);
            this.strictUpdateFill(metaObject, "updater", () -> "unregisteredUser", String.class);
//            this.strictUpdateFill(metaObject, "updateBy", loginUser::getUserName, String.class);
        /*}catch (Exception e) {
            this.strictUpdateFill(metaObject, "updateTime", DateUtil::date, Date.class);
            this.strictUpdateFill(metaObject, "updateBy", () -> "unregisteredUser", String.class);
        }*/
    }

}

