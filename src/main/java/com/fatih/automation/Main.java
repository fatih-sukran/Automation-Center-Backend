package com.fatih.automation;

import com.github.javaparser.StaticJavaParser;
import com.google.common.reflect.ClassPath;
import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import javax.tools.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.*;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Main {
    public static final String PATH = "/users/fatih.sukran/Downloads/api/src/test";

    public static void main(String[] args) {
        var file = new File(PATH);
        System.out.println("--------------------");
        var methods = printPaths(file);
        System.out.println("--------------------g");
        methods.forEach(method -> System.out.println(method.getName().asString()));
    }

    @SneakyThrows
    public static List<MethodDeclaration> javaParser(@NotNull File file) {
        // Load the Java source code file
//        CompilationUnit cu = JavaParser.parse(String.valueOf(new File("YourJavaFile.java")));
        var compilationUnit = StaticJavaParser.parse(file);
        var methods = new ArrayList<MethodDeclaration>();
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
                System.out.println("Method is Test: " + method.getAnnotationByClass(Test.class).isPresent());
                if (method.getAnnotationByClass(Test.class).isPresent()) {
                    methods.add(method);
                }
            }
        }
        return methods;
    }

    public static List<MethodDeclaration> printPaths(File file) {
        return printPaths(file, 0);
    }

    @SneakyThrows
    public static List<MethodDeclaration> printPaths(@NotNull File file, int depth) {
        var methods = new ArrayList<MethodDeclaration>();
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

    @SneakyThrows
    public static void guava(@NotNull File root) {
        var classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
        var classes = ClassPath.from(classLoader)
                .getAllClasses()
                .stream()
                .filter(classInfo -> classInfo.getPackageName().startsWith("com.hangikredi.api"))
                .filter(classInfo -> !classInfo.getSimpleName().isBlank())
                .map(ClassPath.ClassInfo::load)
                .toList();
//        classes.forEach(clazz -> System.out.println("Class: " + clazz.getName()));
        for (var clazz: classes) {
            try {
                var methods = clazz.getDeclaredMethods();
            }
            catch (Error e) {
                System.out.println("Class: " + clazz.getName() + " got error");
                System.out.println("Error: " + e.getMessage());
            }
        }
//        classes
//                .stream()
//                .filter(clazz -> Arrays
//                        .stream(clazz.getDeclaredMethods())
//                        .anyMatch(method -> method.getAnnotation(Test.class) != null))
//                .forEach(clazz -> System.out.println("Class: " + clazz.getName()));
    }

    @SneakyThrows
    public static void compileProject(File root) {
        // Load and instantiate compiled class.
//        var clazz = classLoader.loadClass("com.hangikredi.api.services.BaseService");
//        var cls = Class.forName("com.hangikredi.api.annotations.BasePojo", true, classLoader); // Should print "hello".
//
//        System.out.println("Class Name: " + clazz.getName());
//        System.out.println("Fields:");
//        for (var field: clazz.getDeclaredFields()) {
//            System.out.println("  " + field.getName());
//        }
    }

    @SneakyThrows
    public static Set<String> extractClassNamesFromJavaFile(String javaFilePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(new File(javaFilePath)));

        Set<String> classNames = new HashSet<>();

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
        boolean success = task.call();

        if (success) {
            for (var diagnostic : diagnostics.getDiagnostics()) {
                System.out.println("diagnostic: " + diagnostic);
                // Handle any compilation errors or warnings here
            }

            // Get class names from the compiled units
            for (JavaFileObject fileObject : compilationUnits) {
                if (fileObject.getKind() == JavaFileObject.Kind.SOURCE) {
                    var className = fileObject.getName();
                    var accessLevel = fileObject.getAccessLevel();
                    var uri = fileObject.toUri();
                    System.out.println("---------------------");
                    System.out.println("className: " + className);
                    System.out.println("accessLevel: " + accessLevel);
                    System.out.println("uri: " + uri);
                    className = className.substring(0, className.lastIndexOf('.')).replace(File.separator, ".");
                    classNames.add(className);
                }
            }
        }

        fileManager.close();
        return classNames;
    }

}

@Data
class FClazz {
    private String name;
    private File file;
    private Class<?> clazz;
    private List<FTest> fTests;
}

@Data
class FTest {
    private String name;
}
