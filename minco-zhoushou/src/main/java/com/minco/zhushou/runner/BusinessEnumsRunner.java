package com.minco.zhushou.runner;

import cn.hutool.core.lang.ClassScaner;
import cn.hutool.core.util.StrUtil;
import com.xmk.common.enums.BusinessEnum;
import com.xmk.common.enums.EnumItem;
import com.xmk.common.enums.EnumVO;
import com.xmk.common.enums.KeyEnumHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/23 22:56
 */
@Slf4j
@Component
public class BusinessEnumsRunner implements CommandLineRunner {

    public static String ENUMS_PACKAGE = "com.minco.zhushou.enums";


    @Override
    public void run(String... args) throws Exception {
        getEnums(ENUMS_PACKAGE);

    }

    private void getEnums(String packageName) {
        Set<Class<?>> classes = ClassScaner.scanPackageByAnnotation(packageName, BusinessEnum.class);

        for (Class aClass : classes) {

            BusinessEnum businessEnum = (BusinessEnum) aClass.getAnnotation(BusinessEnum.class);
            String key = getBusinessEnumKey(businessEnum, aClass);
            if (KeyEnumHolder.containsKey(key)) {
                log.error("存在重复key值枚举：{}", key);
                System.exit(-1);
            }
            Object[] enumConstants = aClass.getEnumConstants();
            if (enumConstants == null || enumConstants.length < 1) {
                return;
            }


            List<EnumItem> enumItemList = new ArrayList<>();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() != aClass) {
                    continue;
                }
                try {
                    Object enumValue = field.get(null);
                    Method getTypeMethod = aClass.getDeclaredMethod("getValue");
                    if (getTypeMethod == null) {
                        continue;
                    }
                    Object typeValue = getTypeMethod.invoke(enumValue);
                    if (typeValue == null) {
                        continue;
                    }
                    String name = field.getName();
                    enumItemList.add(new EnumItem(name, typeValue.toString()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    continue;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            if (enumItemList.size() > 0) {
                KeyEnumHolder.put(key, enumItemList);
            }
        }
    }


    private String getBusinessEnumKey(BusinessEnum businessEnum, Class<?> aClass) {
        String key = businessEnum.key();
        if (StrUtil.isBlank(key)) {
            key = aClass.getSimpleName();
        }
        key = key.substring(0, 1).toLowerCase() + key.substring(1);
        return key;
    }
}
