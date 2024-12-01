package com.ethan.ethandemo241101.myclassloader;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.util.jar.*;

public class SignedClassLoader extends ClassLoader {
    private PublicKey publicKey; // 用于验证签名的公钥

    public SignedClassLoader(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 获取类文件的路径（假设类文件是以 .class 文件形式存在）
            String classFileName = name.replace('.', '/') + ".class";
            byte[] classBytes = Files.readAllBytes(Paths.get(classFileName));
            
            // 验证类文件的签名
            if (!verifySignature(classFileName)) {
                throw new SecurityException("Class file signature verification failed: " + classFileName);
            }

            // 使用 defineClass 定义类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + name, e);
        }
    }

    private boolean verifySignature(String classFileName) throws IOException {
        // 假设类文件存储在 JAR 文件中，并且其签名信息在 META-INF 中
        File jarFile = new File("signed-classes.jar");
        try (JarFile jar = new JarFile(jarFile, true)) {
            JarEntry entry = jar.getJarEntry(classFileName);

            // 确保 JAR 条目不为 null
            if (entry == null) {
                throw new FileNotFoundException("Class file not found in JAR: " + classFileName);
            }

            // 获取类文件的输入流
            InputStream inputStream = jar.getInputStream(entry);

            // 验证签名
            byte[] buffer = new byte[8192];
            while (inputStream.read(buffer) != -1) {
                // 读取数据以触发签名验证
            }

            // 检查签名者证书
            Certificate[] certs = entry.getCertificates();
            if (certs == null) {
                return false; // 没有证书，签名验证失败
            }

            for (Certificate cert : certs) {
                cert.verify(publicKey);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        // 加载用于验证的公钥
        PublicKey publicKey = loadPublicKey("publicKey.pem");

        // 使用自定义类加载器加载类
        SignedClassLoader loader = new SignedClassLoader(publicKey);
        Class<?> clazz = loader.loadClass("com.example.MyClass");

        // 实例化和调用方法
        Object obj = clazz.getDeclaredConstructor().newInstance();
        clazz.getMethod("someMethod").invoke(obj);
    }

    private static PublicKey loadPublicKey(String filePath) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(filePath));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(spec);
    }
}