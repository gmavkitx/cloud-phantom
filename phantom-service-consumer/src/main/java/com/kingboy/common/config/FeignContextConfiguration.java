package com.kingboy.common.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/20 上午3:39
 * @link https://github.com/spring-cloud/spring-cloud-netflix/issues/1952
 * @desc 解决关闭容器时的异常Singleton bean creation.
 */
@Component
public class FeignContextConfiguration implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
        bd.setDependsOn("eurekaServiceRegistry", "inetUtils");
    }
}
