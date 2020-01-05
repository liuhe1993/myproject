package com.eric.base.classloader;

import android.content.Context;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class LoadUtil {



    public static void loadClass(Context context, String pluginPath) {

        try {
            // BaseDexClassLoader 的Class对象
            Class<?> clazz = Class.forName("dalvik.system.BaseDexClassLoader");

            // 获取pathList的Field
            Field pathListField = clazz.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            // pathclaaloader
            PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

            // 获取宿主的pathList的值
            Object hostPathList = pathListField.get(pathClassLoader);

            // 获取宿主的dexElements
            Class<?> hostPathListClass = hostPathList.getClass();
            Field hostDexElementsField = hostPathListClass.getDeclaredField("dexElements");
            hostDexElementsField.setAccessible(true);
            Object[] hostDexElements = (Object[]) hostDexElementsField.get(hostPathList);

            // 加载插件
            DexClassLoader dexClassLoader = new DexClassLoader(pluginPath, null,
                    null, pathClassLoader);
            Object pluginPathList = pathListField.get(dexClassLoader);

            // 获取插件的dexElements
            Class<?> pluginPathListClass = pluginPathList.getClass();
            Field pluginDexElement = pluginPathListClass.getDeclaredField("dexElements");
            pluginDexElement.setAccessible(true);
            Object[] pluginDexElements = (Object[]) pluginDexElement.get(pluginPathList);

            // 创建数组
            Object[] newDexElements = (Object[]) Array.newInstance(hostDexElements.getClass().getComponentType(),hostDexElements.length+pluginDexElements.length);

            System.arraycopy(hostDexElements, 0, newDexElements, 0, hostDexElements.length);
            System.arraycopy(pluginDexElements, 0, newDexElements, hostDexElements.length-1, pluginDexElements.length);

            hostDexElementsField.set(hostPathList, newDexElements);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
