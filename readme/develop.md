### 一、编码规范

**项目编码统一使用UTF-8**

> 1. 项目名统一格式：{项目简称}-service-{服务名称}
2. serve的端口号从7000开始，service的端口号从8000开始
3. 必须熟悉common模块中的工具类，有缺少可以添加，但不可以重复造轮子
4. 使用restful,post get put delete进行增删改查,
5. 使用restful风格，url不允许驼峰等标志(只是因为特丑)
    - url统一使用小写英文
    - 多个单词间使用下划线连接
    - 正确url示例:

|操作|url|请求方法|方法名|说明|
|--|--|--|--|--|
|C| /user   |post   | saveUser()/insertUser()|新增用户|
|R| /user   |get    | listUser()    | 查询用户(分页，条件) |
|R| /user/1 |get    | getUser()|  获取单个用户详情|
|U| /user/1 |put    | updateUser()| 更新用户 |
|D| /user/1 |delete | removeUser()/deleteUser| 删除用户 |

6. 项目中涉及到时间的使用LocalDateTime进行定义
7. 涉及到集合操作的请使用java8的流操作
    - funtion-prediction-consumer-super
    - map-filter-foreach-collect
    - limit count max min count
8. 涉及到金钱计算，使用BigDecimal，为什么？
    - 1 * 0.3 = 0.3?
    - 100.0/0 ?
    - 0.0/0.0 ?
9. 类中属性的定义强制使用包装类型
    - Integer 而不是int
    - Double 而不是double
10. 涉及到File操作的请使用 Files和Path的组合
11. 关于一个对象的状态，要使用枚举类型。
12. 使用lombok简化代码，
    - @Data 
    - @NoArgsConstructor
    - @AllArgsConstructor
13. controller层所有入参都必须进行校验
    - @NotNull
    - @NotBlank 
    - @Max 
    - @Min 
    - [@Pattern]()
14. 异常的命名：项目名前四位大写英文 + _ + 异常编号(4位)，
    - 例如 provider模块的异常编码格式为：`PROV_0001=用户ID不能为空`

### 二、代码规范

> 1. 代码格式符合阿里巴巴代码规范
2. 四个空格代替制表符(idea默认)
3. 每个类要有相应的文档注释说明其功能
4. 禁止import多余的包
5. 包名使用全小写英文
6. 接口实现类命名以Impl结尾，如UserServiceImpl.java
7. 方法体中，功能块间使用空格隔开
8. if语句不可以省略大括号
9. Long类型以大写L结尾,Double以D结尾，int大数值类型可以使用_分割
10. 方法参数不超过五个，多余五个请封装对象(推荐)
11. 每行代码不超过140个字符(强制)
12. 一个方法不超过60行(强制)
13. if判断的条件使用有意义的变量代替(推荐)
    正例:

    ```java
        boolean isExistUser = userList != null && userList.size > 0;
        if ( isExistUser ) { 
            //.......... 
        }
    ```

14. 多个if条件判断请使用卫语句

    ```
    if (con > 1) {
        //....
    }
    if (con = 1) {
        //....
    }
    if (con < 1) {
        //....
    }
    ```

### 三、项目规范

1. 包结构及用法
    - 包结构正在规划中，基本会有两种，一种是多层架构，而另一种是DDD(测试阶段)，过程中会对功能进行封装，敬请期待
2. 项目使用swagger进行测试
    - 所有controller层必须有完善的swagger注释
        - controller类
            - 使用@Api(description = "订单操作")，表示该类的作用描述
        - controller方法
            - 使用@ApiOperation(value = "删除订单", notes = "别删除错了")，表示该方法的作用，
            - notes属性表示提示性信息、注意点等,
        - 普通参数
            - 使用@ApiParam(name = "订单id", required = true)注解，
            - name是对参数的描述，required表示参数是否必须。
        - 对象参数
            - 在参数对象的内部做如下添加：
            - 参数类上添加@ApiModel(value = "物流请求载体")，对象的描述
            - 对象的属性上添加@ApiModelProperty(value = "订单ID",required = true, example = "1089909")
    - service/repository/entity注释
        - 每个类上都要有注释
            ```
                /**
                 * @author kingboy--KingBoyWorld@163.com
                 * @date 2017/11/19 下午10:48
                 * @desc 用户实体.
                 */
            ```
    - 方法内注释
        - 多行注释使用/**/
        - 单行注释使用//，单独一行
        - 方法内多个功能点之间使用空格隔开

3. 待办/待处理统一使用TODO进行标记，例：
        ```
        //TODO 标记人:kingboy 标记时间:2017/11/24 下午3:04 预处理时间:2017/11/24 下午3:04
        ```

### 四、数据存储规范

1. servide层进行事物控制
    - 写操作 
        @Transactional(rollback = Exception.class)
    - 读操作
        @Transactional(readonly = true)
2. 有数据操作的模块中，要创建以下两个文件，不要使用hibernate的自动生成。
    - shema.sql(数据定义)
    - data.sql(假数据)两个文件
3. 数据库名称为自己的模块业务名称
    - 例：phantom-service-shop模块的对应连接库为shop,
    - 连接串：jdbc:mysql:///provider?useUnicode=true&characterEncoding=utf-8&useSSL=false
4. 建表语句
    - 头部格式为『create table if not exists 表名』，否则建表语句报错
    - 建表语句中要加上索引，减少后续优化的麻烦。[如何加索引？](http://note.youdao.com/noteshare?id=c1b986758272732e7277e00d1f95d5f1)
5. 表名
    - 由英文小写组成
    - 根据表的职责进行命名
6. 字段名
    - 统一英文小写
    - 多个单词使用下划线分割
    - 命名尽量不要太长，控制在三个单词以内
    - 具有实际意义的表要包含modify_time、create_time两个字段


### 四、代码提交规范

1. 每天不能少于一次远程代码更新
2. 按照分支管理策略进行分支创建,master,develop,test,fix_*,feature_*的格式进行命名
2. 每开发一个功能点/或者修复bug都必须切换新的分支
3. 提交前先拉取远程代码到本地，减少冲突
4. 提交代码前，使用checkStyle进行格式检查=
5. 提交代码前，使用FindBugs自查Bug
6. 提交的常规流程如下(推荐)
```
    git pull origin branch
    git add .        //添加文件
    git status       //查看文件的状态
    git commit -m "message"   //提交到本地仓库
    git push origin branch //推送到远程仓库
```