# 个人总结

##### 一、个人对学习的一些看法，以及对微服务的一些介绍，外加实战的点

[框架讲解](readme/frame.md)

##### 二、项目开发的规范，及注意点

[项目开发说明](readme/develop.md)

##### 三、项目的创建流程


[项目创建流程](readme/create.md)


##### 四、项目实例说明

> ### 服务注册中心
>> 端口：7000/7001/7002
>>
>> 名称: phantom-server-eureka
>>
>> 地址: http://localhost:7000

> ### hystrix监控
>> 端口：7003
>>
>> 名称: phantom-server-hystrix
>>
>> 地址: localhost:7003/hystrix.stream
>> 地址: localhost:7003/hystrix

> ### turbine监控
>> 端口：7004
>>
>> 名称: phantom-server-turbine
>>
>> 地址: http://localhost:7004/hystrix
>> localhost:7004/turbine.stream
>> localhost:port/hystrix.stream

> ### 配置中心
>> 端口：7005
>>
>> 名称: phantom-server-config
>>
>> 地址: http://localhost:7005

> ### 管理中心
>> 端口：7006
>>
>> 名称: phantom-server-admin
>>
>> 地址: http://localhost:7006

> ### 调用链跟踪中心
>> 端口：7007
>>
>> 名称: phantom-server-zipkin
>>
>> 地址: http://localhost:7007

> ### 网关中心
>> 端口：7008
>>
>> 名称: phantom-gateway-zuul
>>
>> 地址: http://localhost:7008

> ### 生产者应用
>> 端口：8000
>>
>> 名称: phantom-service-provider
>>
>> 地址: http://localhost:8000

> ### 消费者应用
>> 端口：8001
>>
>> 名称: phantom-service-consumer
>>
>> 地址: http://localhost:8001

> ### 文件上传
>> 端口：8002
>>
>> 名称: phantom-service-storage
>>
>> 地址: http://localhost:8002