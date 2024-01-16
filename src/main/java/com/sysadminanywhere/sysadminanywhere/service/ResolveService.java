package com.sysadminanywhere.sysadminanywhere.service;

import com.sysadminanywhere.sysadminanywhere.domain.AD;
import lombok.SneakyThrows;
import org.apache.directory.api.ldap.model.entry.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResolveService<T> {

    private Class<T> typeArgumentClass;

    public ResolveService(Class<T> typeArgumentClass) {
        this.typeArgumentClass = typeArgumentClass;
    }

    @SneakyThrows
    public T getValues(Entry entry) {

        T result = typeArgumentClass.newInstance();

//        Collection<Attribute> attributes = entry.getAttributes();

        Field[] fields = result.getClass().getDeclaredFields();

        for (Field field : fields) {
            AD property = field.getAnnotation(AD.class);
            if (property != null) {
                field.setAccessible(true);
                if (property.name().equalsIgnoreCase("distinguishedname")) {
                    field.set(result, entry.getDn().getName());
                } else {
                    if (entry.get(property.name()) != null) {
                        Value value = entry.get(property.name()).get();

                        if (field.getType().getName().equalsIgnoreCase(String.class.getName())) {
                            field.set(result, value.getString());
                        }

                        if (field.getType().getName().equalsIgnoreCase(LocalDateTime.class.getName())) {
                            field.set(result, getLocalDateTime(value.getString()));
                        }

                        if (field.getType().getName().equalsIgnoreCase("java.util.List")) {
                            List<String> list = new ArrayList<>();
                            for (Value v : entry.get(property.name())) {
                                list.add(v.getString());
                            }
                            field.set(result, list);
                        }

                        if (field.getType().getName().equalsIgnoreCase(int.class.getName())) {
                            field.set(result, Integer.valueOf(value.getString()));
                        }

                        if (field.getType().getName().equalsIgnoreCase(long.class.getName())) {
                            field.set(result, Long.valueOf(value.getString()));
                        }

                        if (field.getType().getName().equalsIgnoreCase(boolean.class.getName())) {
                            field.set(result, Boolean.valueOf(value.getString()));
                        }

                        if (field.getType().getName().equalsIgnoreCase(byte[].class.getName())) {
                            field.set(result, value.getBytes());
                        }

                    }
                }
            }
        }

        return result;
    }

    private LocalDateTime getLocalDateTime(String value) {
        try {
            if (value.endsWith(".0Z")) {
                int year = Integer.valueOf(value.substring(0, 4));
                int month = Integer.valueOf(value.substring(4, 6));
                int day = Integer.valueOf(value.substring(6, 8));

                int hour = 0;
                int minute = 0;
                int second = 0;

                if (value.length() > 8) {
                    hour = Integer.valueOf(value.substring(8, 10));
                    minute = Integer.valueOf(value.substring(10, 12));
                    second = Integer.valueOf(value.substring(12, 14));
                }
                return LocalDateTime.of(year, month, day, hour, minute, second);
            } else {
                return LocalDateTime.parse(value);
            }
        } catch (Exception e) {
            return null;
        }
    }

}