package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.AD;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ResolveService<T> {

    private Class<T> typeArgumentClass;

    public ResolveService(Class<T> typeArgumentClass) {
        this.typeArgumentClass = typeArgumentClass;
    }

    @SneakyThrows
    public T GetValues(Entry entry) {

        T result = typeArgumentClass.newInstance();

        Field[] fields = result.getClass().getDeclaredFields();

        for (Field field : fields) {
            AD property = field.getAnnotation(AD.class);
            if (property != null) {
                if (property.name().equalsIgnoreCase("dn")) {
                    field.setAccessible(true);
                    field.set(result, entry.getDn().getName());
                } else {
                    if (entry.get(property.name()) != null) {
                        String value = entry.get(property.name()).getString();
                        field.setAccessible(true);
                        field.set(result, value);
                    }
                }
            }
        }

        return result;
    }

}