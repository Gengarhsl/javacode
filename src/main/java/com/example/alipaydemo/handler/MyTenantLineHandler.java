package com.example.alipaydemo.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.example.alipaydemo.common.LiteralExpression;
import com.example.alipaydemo.controller.pojo.tUser;
import net.sf.jsqlparser.expression.Expression;
import org.springframework.stereotype.Component;


/**
 * @author hsl
 * 多租户拦截器
 */
@Component
public class MyTenantLineHandler implements TenantLineHandler {

    @Override
    public String getTenantIdColumn() {
        return "tenant_id"; // 租户字段名
    }

    @Override
    public Expression getTenantId() {
        // 从上下文中获取当前租户ID
        return new LiteralExpression(1);
    }

    @Override
    public boolean ignoreTable(String tableName) {
        // 忽略某些表不使用多租户机制
        return false;
    }
}