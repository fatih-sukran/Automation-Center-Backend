package com.fatih.automation.restapi;

import com.fatih.automation.restapi.model.TestClass;
import com.fatih.automation.restapi.model.TestMethod;
import com.github.javaparser.StaticJavaParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<TestMethod> findTestMethods(String absolutePath) {
        var file = new File(absolutePath);
        return findTestMethods(file);
    }

    @SneakyThrows
    public static List<TestMethod> findTestMethods(@NotNull File file) {
        var compilationUnit = StaticJavaParser.parse(file);
        var classDeclaration = compilationUnit.getTypes().get(0);
        if (classDeclaration == null) {
            return List.of();
        }

        var methods = new ArrayList<TestMethod>();
        for (var method : classDeclaration.getMethods()) {
            var annotation = method.getAnnotationByName("Test");
            if (annotation.isPresent()) {
                var testMethod = new TestMethod()
                        .setName(method.getName().asString());
                methods.add(testMethod);
            }
        }

        return methods;
    }

    public static List<TestClass> findTestClasses(File file) {
        return findTestClasses(file, 0);
    }

    @SneakyThrows
    private static List<TestClass> findTestClasses(@NotNull File file, int depth) {
        var classes = new ArrayList<TestClass>();
        for (File f : file.listFiles()) {
            if (f.isFile() && !isJavaFile(f)) {
                continue;
            }
            if (f.isDirectory()) {
                classes.addAll(findTestClasses(f, depth + 1));
            } else if (isTestClass(f)) {
                var testClass = new TestClass()
                        .setName(f.getName())
                        .setPath(f.getAbsolutePath());
                classes.add(testClass);
            }
        }
        return classes;
    }

    public static boolean isJavaFile(File file) {
        return file.getName().endsWith(".java");
    }

    @SneakyThrows
    public static boolean isTestClass(File file) {
        var compilationUnit = StaticJavaParser.parse(file);
        var classDeclaration = compilationUnit.getTypes().get(0);

        if (classDeclaration == null) {
            return false;
        }

        for (var method : classDeclaration.getMethods()) {
            var annotation = method.getAnnotationByName("Test");
            if (annotation.isPresent()) {
                return true;
            }
        }
        return false;
    }
}
