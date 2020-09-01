# testAnnotation
测试在springboot项目中各注解的使用

* @Tractional 事务传播中的应用
1. REQUIRED（默认）：支持使用当前事务，如果当前事务不存在，创建一个新事务。
2. SUPPORTS：支持使用当前事务，如果当前事务不存在，则不使用事务。
3. MANDATORY：中文翻译为强制，支持使用当前事务，如果当前事务不存在，则抛出Exception。
4. REQUIRES_NEW：创建一个新事务，如果当前事务存在，把当前事务挂起。
5. NOT_SUPPORTED：无事务执行，如果当前事务存在，把当前事务挂起。
6. NEVER：无事务执行，如果当前有事务则抛出Exception。
7. NESTED：嵌套事务，如果当前事务存在，那么在嵌套的事务中执行。如果当前事务不存在，则表现跟REQUIRED一样。

* @Configuation
相当于<Beans><Beans>

* @Bean
相当于<Bean></Bean>

* @ComponentScan
相当于<context:component-scan base-package=""/>
1. @Configuration + @Bean 注册bean
2. @Component 注册bean
3. @Import 注册bean

* @PropertySource + @Value 实现配置文件设置值
* 多环境配置
<filters>
    <filter>
    </filter>
</filters>


### 线程池的几种使用方式
1. springPoolTaskExecutor <br>
 1.1 非注解方式 <br>
 1.2 注解方式 @EnableAsync + @Async 加了@EnableAsync才能使@Async有效 <br>
2. 扩展ThreadPoolExecutor <br>
3. Future <br>
* 配合applicationContext.getBean实现获取单独Bean实现业务逻辑。 eg: 获取各菜单气泡数






