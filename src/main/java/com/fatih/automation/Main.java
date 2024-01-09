package com.fatih.automation;

import com.fatih.automation.model.TestMethod;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

public class Main {

    @SneakyThrows
    public static List<TestMethod> javaParser(@NotNull File file) {
        var compilationUnit = StaticJavaParser.parse(file);
        var methods = new ArrayList<TestMethod>();
        // Extract class information
        var classDeclaration = compilationUnit.getTypes().get(0);
        if (classDeclaration != null) {
            System.out.println("Class Name: " + classDeclaration.getName());

            // Extract field information
            for (FieldDeclaration field : classDeclaration.getFields()) {
                System.out.println("Field: " + field.getVariable(0).getNameAsString());
            }

            // Extract method information
            for (MethodDeclaration method : classDeclaration.getMethods()) {
                System.out.println("Method: " + method.getName());

                var annotation = method.getAnnotationByName("Test");
                System.out.println("Method is Test: " + annotation.isPresent());
                if (annotation.isPresent()) {
                    var testMethod = new TestMethod();
                    testMethod.setName(method.getName().asString());
                    testMethod.setClassName(classDeclaration.getName().asString());
                    methods.add(testMethod);
                }
            }
        }
        return methods;
    }

    public static List<TestMethod> printPaths(File file) {
        return printPaths(file, 0);
    }

    @SneakyThrows
    public static List<TestMethod> printPaths(@NotNull File file, int depth) {
        var methods = new ArrayList<TestMethod>();
        for (File f : file.listFiles()) {
            if (f.isFile() && !f.getName().endsWith(".java")) {
                continue;
            }
            System.out.println("  ".repeat(depth) + f.getName());
            if (f.isDirectory()) {
                methods.addAll(printPaths(f, depth + 1));
            } else {
                System.out.println("-------------------");
                System.out.println("file: " + f.getAbsolutePath());
                methods.addAll(javaParser(f));
            }
        }
        return methods;
    }
}
