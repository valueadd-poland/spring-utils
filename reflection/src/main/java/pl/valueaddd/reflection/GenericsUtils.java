package pl.valueaddd.reflection;

import lombok.experimental.UtilityClass;

import java.lang.reflect.ParameterizedType;

/**
 * @author Jakub Trzcinski kuba@valueadd.pl
 * @since 24-09-2020
 */
@UtilityClass
public class GenericsUtils {
    public Class<?> getGeneric(Object obj){
        return (Class<?>) ((ParameterizedType) obj.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public Class<?> getGeneric(Object obj, int index){
        return (Class<?>) ((ParameterizedType) obj.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[index];
    }
}

