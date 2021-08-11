package kr.neko.sokcuri.naraechat.Obfuscated;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObfuscationReflectionHelper {
    public static <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, String fieldName) {
        try {
            Field f = classToAccess.getDeclaredField(fieldName);
            f.setAccessible(true);
            return (T) f.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, E> void setPrivateValue(
            final Class<? super T> classToAccess,
            final T instance,
            final E value,
            final String fieldName) {
        try {
            Field f = classToAccess.getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(instance, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method findMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) {
        try {
            Method m = clazz.getDeclaredMethod(methodName, parameterTypes);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
