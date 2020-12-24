package com.minco.zhushou.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/18 11:34
 */
@Component
public class BeanFactoryAwareUtils implements BeanFactoryAware {
    protected static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory arg) throws BeansException {
        beanFactory = arg;
    }

    public static <T> T getBean(String beanName) {
        return (T) beanFactory.getBean(beanName);
    }

    public static <T> T getBean(Class<T> c) {
        return (T) beanFactory.getBean(c);
    }

}
