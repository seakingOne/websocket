package com.ynhuang.websocket.demo1.classloader;

import com.ynhuang.websocket.demo1.lock.DeadLock;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 018399
 * @Date: 2019/2/28 09:35
 * @Description:
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("com.ynhuang.websocket.demo1.lock.DeadLock");
        System.out.println(clazz.getClassLoader());

        MyClassLoader myClassLoader = new MyClassLoader();
        DeadLock deadLock = (DeadLock) myClassLoader.loadClass("com.ynhuang.websocket.demo1.lock.DeadLock").newInstance();
        System.out.println(deadLock.getClass().getClassLoader());
    }


    private static class MyClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {

            //获取类名称
            String className = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream resourceAsStream = getClass().getResourceAsStream(className);

            try {
                byte[] bytes = new byte[resourceAsStream.available()];
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("classloader load fail");
            }

        }
    }

}
