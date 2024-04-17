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

# API 开放平台-模拟接口

## 模拟接口项目 fishmanAPI-interface

提供三个模拟接口

1. GET 接口
2. POST 接口（URL 传参）
3. POST 接口 （Restful)

### 调用接口

几种 HTTP 调用方式：

1. HttpClient
2. RestTemplate
3. 第三方库（OKHTTP、Hutool)

Hutool:https://hutool.cn/docs/#/

Http 工具类：https://hutool.cn/docs/#/http/Http%E5%AE%A2%E6%88%B7%E7%AB%AF%E5%B7%A5%E5%85%B7%E7%B1%BB-HttpUtil

### API 签名认证

本质：

1. 签名签发
2. 使用签名（校验签名）

为什么需要签名认证？

保证安全性，防止恶意调用

### 如何实现 API 签名认证呢？

通过 http request header 头传递参数

- 参数1： accessKey 调用的标识 userA, userB (复杂、无序、无规律)

- 参数2： secretKey 密钥 （复杂、无序、无规律），**该参数不能放在请求头中**

  类似于用户名和密码，区别：accessKey、 secretKey 是无状态的

  密钥一般不用在服务器之间的传递，因为在传递过程中可能被拦截

- 参数3： 用户请求参数

- 参数4： sign

- 参数5：加 none 随机数， 只能用一次（服务端要保存用过的随机数）

- 参数6： 加 timestamp 时间戳， 校验时间戳是否过期

加密方式：对称加密、非对称加密、md5 签名（不可解密）

用户参数 + 密钥 => **签名生成算法(MD5、Hmac、Sha1)** => 不可解密的值

如：abc + abcdefgh => sfasfafffsfsa



如何验证签名？

**服务端用一模一样的参数和算法去生成签名，只要和用户传的一致，就表示一致。**



如果防止重放？

**服务端要保存用过的随机数**



**API 签名认证是一个很灵活的设计，具体要有哪个参数、参数名如何需要根据场景来设计。比如： userId、 appId、version、固定值等**

## 开发一个简单易用的 SDK 

理想状态下：开发者只需要关心调用哪些接口、传递哪些参数、就跟调用自己写的代码一样。

开发 starter 的好处：开发者引入后，可以直接在 application.yml 中写配置，自动创建客户端

**spring-boot-configuration-processor 的作用是自动生成配置的代码提示**

### starter 的开发流程

初始化，环境依赖（一定要移除 build）

```java
	<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
    </dependency>

    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
    </dependency>

    <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
	</dependency>
```

编写配置类（启动类）

```java
@Configuration
// 能读取 application 中的配置属性
@ConfigurationProperties("mofeng.client")
@Data
@ComponentScan
public class MofengClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public MofengApiClient mofengApiClient(){
        return new MofengApiClient(accessKey, secretKey);
    }
}
```

注册配置类

resources/META_INF/spring.factories

```java
# starter
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.mofeng.mofengclientsdk.MofengClientConfig
```

mvn install 打包为本地项目

创建新项目（复用 server 项目）、测试



# 接口发布-下线-调用

1. 开发接口发布、下线的功能（管理员）
2. 前端去浏览接口、查看接口文档、申请签名（注册）
3. 在线调试（用户）
4. 统计用户调用接口的次数
5. 优化系统 - API 网关

## 开发接口发布/下线功能

后台接口：

发布接口（仅管理员可操作）

1. 校验该接口是否存在
2. 判断该接口是否可以调用
3. 修改接口数据库中的状态字段为 1

下线接口（仅管理员可操作）

1. 校验接口是否存在
2. 修改接口数据库中的状态字段为 0

## 查看接口文档

动态路由，用 url 来传递 id, 加载不同的接口信息

## 申请签名

用户在注册成功时，自动分配 accessKey、secretKey 

扩展点：用户可以申请更换签名

## 在线调用

```json
[
    {"name":"username", "type":"string"}
]
```

先跑通整个接口流程，再去针对不同的请求头或者接口类型来设计界面和表单，给用户更好的体验。（可以参考 swagger、postman、knife4j)

## 调用流程

流程：

1. 前端将用户输入的请求参数和要测试的接口 id 发给平台后端
2. 在调用前可以做一些校验
3. 平台后端去调用模拟接口

![image-20240416222231707](C:\Users\fishman\AppData\Roaming\Typora\typora-user-images\image-20240416222231707.png)

## TODO

- 判断该接口是否可以调用时有固定方法名改为根据测试地址来调用
- 用户测试接口固定方法名改为根据测试地址来调用
- 模拟接口改为从数据库校验 accessKey





# 网关设计

1. 开发接口调用次数的设计
2. 优化整个系统的架构（API 网关）
   1. 网关是什么？
   2. 网关的作用
   3. 网关的应用场景及实现
   4. 结合业务应用网关

## 接口调用次数统计

需求：

1. **用户每次调用接口成功，次数 + 1**
2. 给用户分配或者用户自主申请接口调用次数

业务流程：

1. 用户调用接口
2. 修改数据库，调用次数 + 1

设计库表：

哪个用户？哪个接口？

用户 => 接口 （多对多关系）

用户调用接口关系表：

```sql
-- 用户调用接口关系表
create table if not exists user_interface_info
(
    id            bigint auto_increment comment 'id' primary key,
    userId        bigint  comment '调用用户 id',
    interfaceInfoId        bigint  comment '接口 id',
    totalNum       int     default 0 comment '总调用次数',
    leftNum       int     default 0 comment '剩余调用次数',
    status       int     default 0 comment '0-正常， 1-禁用',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 not null comment '是否删除'
) comment '用户调用接口关系表';
```

步骤：

1. 开发基本的增删改查（给管理员用）
2. 开发用户调用接口次数 + 1 的功能（service）

问题：

如果每个接口的方法都写调用次数 + 1， 过于麻烦

![image-20240417214937695](C:\Users\fishman\AppData\Roaming\Typora\typora-user-images\image-20240417214937695.png)

致命问题：接口开发者需要自己去添加统计代码

解决方案：通用方法；AOP；或则是server的拦截器（过滤器）

使用 AOP 切面的优点：独立于接口，在每个接口调用后次数 + 1

AOP 切面的缺点：只存在单个项目中，如果每个团队都要开发自己的模拟接口，那么都要写一个切面，如果要使用统计的功能就需要别人引入一个包不是很方便

![image-20240417215640575](C:\Users\fishman\AppData\Roaming\Typora\typora-user-images\image-20240417215640575.png)

###　网关

![image-20240417220041116](C:\Users\fishman\AppData\Roaming\Typora\typora-user-images\image-20240417220041116.png)

### 网关的作用

1. 路由
2. 负载均衡
3. 统一鉴权
4. 跨域
5. 统一业务处理（缓存）
6. 访问控制
7. 发布控制
8. 流量染色
9. 接口保护
   1. 限制请求
   2. 信息脱敏
   3. 降级（熔断）
   4. 限流：学习令牌桶算法、学习漏桶算法，学习一下 RedisLimitHandler
   5. 超时时间
10. 统一日志
11. 统一文档

### 路由

起到转发的作用，比如有接口 A 和接口 B， 网关会记录这些信息，根据用户访问的地址和参数，转发请求到对应的接口（服务器/集群）

/a => 接口 A

/b => 接口 B

Gateway 路由：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories

### 负载均衡

在路由的基础上

/c => 服务 A / 集群 A （随机转发到其中的某一个机器）

uri 从固定地址改成 lb:xxxx

### 统一处理跨域

网关统一处理跨域，不用在每个项目里单独处理

GateWay 处理跨域：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#cors-configuration

### 发布控制

灰度发布，比如上线新接口，先给新接口分配 20% 的流量，老接口 80%， 再慢慢调整比例

https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-weight-route-predicate-factory

### 流量染色

给请求（流量）添加一些标识，一般是设置请求头中，添加新的请求头

https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-addrequestheader-gatewayfilter-factory

全局染色：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#default-filters

### 统一接口保护

1. 限制请求：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#requestheadersize-gatewayfilter-factory
2. 信息脱敏：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-removerequestheader-gatewayfilter-factory
3. 降级（熔断）：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#fallback-headers
4. 限流：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-requestratelimiter-gatewayfilter-factory
5. 超时时间：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#http-timeouts-configuration
6. 重试（业务保护）：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-retry-gatewayfilter-factory

### 统一业务处理

把每个项目中都要做的通用逻辑放到上层（网关），统一处理，比如本项目的次数统计

### 统一鉴权

判断用户是否有权限进行操作，无论访问什么接口，都统一验证权限，避免重复写验证权限操作。

### 访问控制

黑白名单，比如限制 DDOS IP

### 统一日志

统一的请求、响应信息记录

### 统一文档

将下游项目的文档进行聚合，在一个页面统一查看

可以使用 knife4j : https://doc.xiaominfo.com/docs/middleware-sources/aggregation-introduction

## 网关的分类

1. 全局网关（接入层网关）： 作用是负载均衡、请求日志等，不和业务逻辑绑定
2. 业务网关（微服务网关）： 存在一些业务逻辑，作用是将请求转发到不同的业务/项目/接口/服务

参考文章：https://blog.csdn.net/qq_21040559/article/details/122961395

## 实现网关

1. Nginx （全局网关）、Kong 网关 （API 网关， Kong: https://github.com/Kong/kong), 编程成本相对高点
2. Spring Cloud GateWay (取代了 Zuul ) , 性能高、可以用 Java 代码来写逻辑，适合学习

网关的技术选型：https://zhuanlan.zhihu.com/p/500587132

## Spring Cloud GateWay 用法

官网：https://spring.io/projects/spring-cloud-gateway/

官方文档：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/

### 核心概念

路由（根据什么条件，转发到哪里去）

断言：一组规则、条件， 用来确定如何转发路由

过滤器： 对请求进行一系列的处理， 比如添加请求头、添加请求参数

请求流程：

1. 客户端发起请求
2. Handler Mapping: 根据断言，将请求转发到对应的路由
3. Web Handler： 处理请求（一层层经过过滤器）
4. 实际调用服务

### 两种配置方式

1. 配置式（方便、规范）
   1. 简化版
   2. 全称版
2. 编程式（灵活、相对麻烦）

### 开启日志

```yml
logging:
  level:
    org:
      springframework:
        cloud:
          gateway: trace
```

### 断言

1. After 在 xx 时间之后
2. Before 在 xx 时间之前
3. Between 在 xx 时间之间
4. 请求类别
5. 请求头（包含 cookie）
6. 查询参数
7. 客户端地址
8. **权重**

### 过滤器

基本功能：对请求头、请求参数、响应头的增删改查

1. 添加请求头
2. 添加请求参数
3. 添加响应头
4. 降级
5. 限流
6. 重试

引入：

```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-reactor-resilience4j</artifactId>
</dependency>
```







