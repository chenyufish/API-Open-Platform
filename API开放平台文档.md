# API开放平台

## 项目介绍

本项目是一个面向开发者的API平台，提供AP!接口供开发者调用。用户通过注册登录，可以开通接口调用权限，并可以浏览和调用接口。每次调用都会进行统计，用户可以根据统计数据进行分析和优化。管理员可以发布接口、下线接口、接入接口，并可视化接口的调用情况和数据。

![](C:\Users\fishman\AppData\Roaming\Typora\typora-user-images\image-20240410214122743.png)

在这所有的功能前面都要通过一个API网关，区别于AOP

## 需求分析

1. 管理员可以对接口信息进行增删改查
2. 用户可以访问前台，查看接口信息

## 技术栈

### 前端

Ant Design Pro

React

Umi

Umi Request（Axios封装）

### 后端

Java Spring Boot

Spring Boot Startter（SDK开发）

Spring Cloud Getway（网关）



# 数据库表设计

### 接口信息表

```sql
-- 接口信息表
create table if not exists mofeng.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '用户名',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `userId` varchar(256) not null comment '创建人',
    `status` int default 0 not null comment '接口状态（0 - 关闭， 1 - 开启））',
    `method` varchar(256) not null comment '请求类型',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '接口信息表';
```

## 项目脚手架

前端：Ant Design Pro 脚手架

后端：Spring Boot 通用模板

## 基础功能

增删改查、登录（复制、粘贴）

前端接口调用：oneapi 插件自动生成

openapi  的规范







