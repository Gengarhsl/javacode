package com.example.alipaydemo.config.properties;

import lombok.Data;

/**
 * @author hsl
 * @version 1.0
 * @date 2024-08-27 13:32
 */

@Data
public class SingleServerConfig {

    private String address;
    private int subscriptionConnectionMinimumIdleSize;
    private int subscriptionConnectionPoolSize;
    private int connectionMinimumIdleSize;
    private int connectionPoolSize;
    private int database;
    private long dnsMonitoringInterval;
}
