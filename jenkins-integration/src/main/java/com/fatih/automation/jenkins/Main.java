package com.fatih.automation.jenkins;

import com.fatih.automation.common.model.TestClass;
import com.fatih.automation.common.model.TestMethod;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.quality.NotNull;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @SneakyThrows
    public static List<TestMethod> findTestMethods(@NotNull File file) {
        checkFileExists(file);
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

    @SneakyThrows
    public static File findTestClassFileByPackageName(File repository, String packageName) {
        checkFileExists(repository);

        // find all test classes in the given directory
        for (var file : repository.listFiles()) {
            // skip non-java files
            if (file.isFile() && !isJavaFile(file)) {
                continue;
            }

            if (file.isDirectory()) {
                var actual = findTestClassFileByPackageName(file, packageName);
                if (actual != null) {
                    return actual;
                }
                continue;
            }

            var compilationUnit = StaticJavaParser.parse(file);
            var classDeclaration = compilationUnit.getTypes().get(0);

            if (classDeclaration == null || compilationUnit.getPackageDeclaration().isEmpty()) {
                return null;
            }

            var actualPackageName = compilationUnit.getPackageDeclaration().get().getName().asString();
            if (packageName.equals(actualPackageName)) {
                return file;
            }
        }

        return null;
    }

    /**
     * find all test classes in the given directory
     *
     * @param file is a directory that contains test classes
     * @return list of test classes
     */
    @SneakyThrows
    public static List<TestClass> findTestClasses(@NotNull File file) {
        checkFileExists(file);
        // classes store all test classes
        var classes = new ArrayList<TestClass>();

        // find all test classes in the given directory
        for (File f : file.listFiles()) {
            // skip non-java files
            if (f.isFile() && !isJavaFile(f)) {
                continue;
            }
            // if file is a directory, search for test classes in that directory
            // else if file is a test class, add it to the list
            if (f.isDirectory()) {
                classes.addAll(findTestClasses(f));
            } else if (isTestClass(f)) {
                // add test class
                var testClass = getTestClass(f);
                classes.add(testClass);
            }
        }
        return classes;
    }

    @SneakyThrows
    public static TestClass getTestClass(File file) {
        var compilationUnit = StaticJavaParser.parse(file);
        var classDeclaration = compilationUnit.getTypes().get(0);
        var packageName = compilationUnit.getPackageDeclaration().isPresent() ?
                compilationUnit.getPackageDeclaration().get().getNameAsString() : null;

        return new TestClass()
                .setName(classDeclaration.getNameAsString())
                .setPackageName(packageName);
    }

    /**
     * checks if the given file is a java file
     * @param file is a file that we want to check
     * @return true if the file is a java file, false otherwise
     */
    public static boolean isJavaFile(File file) {
        return file.getName().endsWith(".java");
    }

    /**
     * checks if the given file is a test class
     * @param file is a file that we want to check
     * @return true if the file is a test class, false otherwise
     */
    @SneakyThrows
    public static boolean isTestClass(File file) {
        var compilationUnit = StaticJavaParser.parse(file);
        var classDeclaration = compilationUnit.getTypes().get(0);

        return isTestClass(classDeclaration);
    }

    /**
     * checks if the given file exists
     *
     * @param file is a file that we want to check
     * @throws RuntimeException if the file does not exist
     */
    public static void checkFileExists(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File does not exist: " + file.getAbsolutePath());
        }
    }

    /**
     * checks if the given type declaration is a test class
     * @param typeDeclaration is a type declaration that we want to check
     * @return true if the type declaration is a test class, false otherwise
     */
    public static boolean isTestClass(TypeDeclaration<?> typeDeclaration) {
        if (typeDeclaration == null) {
            return false;
        }

        for (var method : typeDeclaration.getMethods()) {
            var annotation = method.getAnnotationByName("Test");
            if (annotation.isPresent()) {
                return true;
            }
        }

        return false;
    }
}
