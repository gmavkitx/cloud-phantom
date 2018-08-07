#### 讲解的内容与实战
***********************

#### 内容简介

> 前半部分是软技能，关于方法论相关的知识。后半部分是硬技能，关于项目的实战。个人觉得软技能是拥有过硬技能的基础。我更希望把这次的会议定位一个个人发展的培训课程。(关于前半部分内容，也可以当成是在brag)。这份文档在项目开发过程中难免会有遗漏，随时欢迎大家进行补充。在过程中有任何疑问的地方都可以随时提问。

#### 意义

> 希望大家能找到自己的学习方法，能在项目实战中更有效率的锻炼自己。编程编的不是代码，而是思想。

#### 时间

> 时间可能会很长，也可能不太长，会根据大家的反馈进行调整。希望大家能够有耐心。


#### 一. 关于学习的一些看法
> 1. 如何学习？
    - 把工作当成一种兴趣，这一点很难做到，但是做到了，你的成长速度很快。
    - 在一个点上深究，你会发现另外一个点。
    - 没事多看看书，不一定是技术书籍。
    - 增强自己的软技能(思维能力)，ppt做的很棒，文件整理的很棒。效率工具用的很棒(滴答，sublime)，墨菲定律(简单的事情，出错的事情)，瀑布？XP?结对？
    - 对自己学会的技能做记录，你会发现很多新的东西。金字塔模型
    - 掌握自己的学习方法，也就是方法论：5w2h
    - 很多领域是共通的，要形成一种悟的效应(装逼的说法)，简单说就是举一反三
    - 英语很重要，英语很重要，英语很重要！(重要的事情复制三遍)
    - 有自己的资料库，资源库，我的资源库
2. 怎么在工作中去学习？
	- 从业务点中扩展出技能线，业务逻辑、业务经验是技能成熟的基础
    - 先和产品沟通好需求理解无误，想清楚自己的思路之后，再开始写代码。
    - 写代码过程中，对于不理解的地方好奇的地方，进源码看看，即使暂时看不懂
	- 技能依赖于具体的业务场景，不同情景使用不同的技术
	- 在项目中接触不同的技能栈，不要急于给自己定性，找到自己的发展领域或者说擅长的领域
    - 在项目中想学习什么技术，或者想用什么技术可以提出来，一块讨论。架构是死的而人是活的，毛遂自荐可以让你快速脱颖而出。
	- 项目工具：熟悉自己使用的工具，善其事必先利其器，md,idea,git,gradle(知乎)
3. 如何问问题
    - [怎样才是提问的正确打开方式？让别人愿意为你解答？](http://note.youdao.com/noteshare?id=651a7583bfa22a99d8ff6c6ce0b7ffb0)
4. 搜索的艺术
    - [如何高效的搜索](http://note.youdao.com/noteshare?id=e2f1f3c7cc7840cf3eb14cadb60e48a2)

*********************
#### 二. 微服务,分布式微服务基础
> 1. 系统架构的发展
	- 单体应用
	- soa和microService
        - 对soa更细粒度的划分，完善了生态组件
        - 更加强调组件化, 去中心化
        - 可以细化到一个funtion
        - 都有服务注册发现
        - micro去中心化，强调整体的生态，从开发到部署DevOps
        - 画个图解释下(公司部门来演示)
    - 没有一定的基础技术能力之前，不要过度深究架构(不是框架)
2. 什么是微服务
	- 是一种架构风格
    - 每个服务都是无状态的，那么什么是无状态？比如session
	- 可以跨语言、跨平台
    - 更好的应对需求变更
	- 开发更加快速，可以发挥每个人的长处
    - 但是也随之而来了一些问题：事物，日志，运维，
3. 什么是springboot？
	- 只是一种整合框架
	- 简化了依赖
	- 应用监控(actuator)
	- 自动配置(减少模板代码)
	- 简化了配置(yml)
    - 是构成微服务的基础组件
4. 微服务中有哪些问题？
    - 日志的管理？
    - 分布式事物？
    - 服务注册发现？
    - 服务状态？
    - 配置如何管理？
    - 入口？
    - 负载？
    - 断路怎么处理？
    - 服务跟踪？
5. spring cloud的生态环境，以及我为什么没有使用dubbo，zookper?
    - elk
    - tcc+补偿, 两阶段事物，cap理论
    - spring eureka
    - spring admin
    - spring config
    - spring zuul
    - spring ribbon
    - spring hystrix
    - spring sleuth
6. spring cloud 与 dubbo对比

|Dubbo|Spring Cloud|| 
|--|--|--|
|服务注册中心| Zookeeper |Spring Cloud Netflix Eureka|
|服务调用方式 |RPC |REST API|
|服务监控 |Dubbo-monitor |Spring Boot Admin|
|断路器| 不完善 |Spring Cloud Netflix Hystrix|
|服务网关| 无 |Spring Cloud Netflix Zuul|
|分布式配置| 无 |Spring Cloud Config|
|服务跟踪| 无 |Spring Cloud Sleuth|
|消息总线| 无 |Spring Cloud Bus|
|数据流 |无 |Spring Cloud Stream|
|批量任务 |无 |Spring Cloud Task|

> 7.spring cloud 项目流程

<a href="img/frame1.jpeg">simple</a>
<a href="img/frame2.jpeg">complex</a>

#### 三. 项目实战(待完善)

> 1. 关于项目的git管理

    - 仓库结构，画图解释。
    - 克隆 git clone https://github.com/KingBoyWorld/cloud-phantom.git;git submodule init;git submodule update;
    - 整体切换分支，git module foreach git checkout master
    - 提交单个仓库的变更
        - 先进入变更的模块，git add .;git commit -m "提交信息";git pull;git push
        - 进入根目录：git add .;git commit -m "提交信息";git pull;git push
    - 提交多个仓库的变更
        - 在根目录下执行: git submodule foreach "git add .;git commit -m '提交信息';git pull;git push"
2. 异常怎么使用
    - 异常体系的流程图
    - 实际代码演示异常
3. Feign的使用及注意点
    - 不要在类上加requestMapping注解
    - requestParam要写具体的Value
    - 不加注解的参数会当成requestBody进行处理
4. yml文件的使用
    - 和properties进行对比
5. swagger文档的写法
    - 实际操作
6. 涉及到跨资源事物，要写好补偿方法(示例：优惠券使用)
8. 每个人新建的模块要在appInfo.md文件中备注清楚
9. 每个模块可以根据自己的习惯或者业务情景选用不同的技术或包结构(DDD)，但是是在充分考虑业务情景的前提下
10. 分页的注意事项，分页实战
11. 使用hibernate,就要搞清楚jpa的一套接口作用(接口的实现是怎么来的？这就可以学习下动态代理)
12. 枚举类型的定义
13. 参数校验的实例(所有参数校验写在Service层的接口中)
14. url的正确书写方式
12. 项目的执行流程分析
    - 简要说明
13. 使用网关进行文件上传
    - 在路径前加zuul路径 http://wangguan.com/zuul/storage/file/upload