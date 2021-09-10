package com.example.myprocessor;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        creatClz(processingEnv);
        return true;
    }


    private void creatClz(ProcessingEnvironment processingEnv) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.OTHER,"-------processingEnv----------");
        ClassName activity = ClassName.get("android.app", "Activity");

        TypeSpec.Builder mainActivityBuilder = TypeSpec.classBuilder("MActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(activity);

        ClassName override = ClassName.get("java.lang", "Override");

        ClassName bundle = ClassName.get("android.os", "Bundle");

        ClassName nullable = ClassName.get("androidx.annotation", "Nullable");

        ParameterSpec savedInstanceState = ParameterSpec.builder(bundle, "savedInstanceState")
                .addAnnotation(nullable)
                .build();

        MethodSpec onCreate = MethodSpec.methodBuilder("onCreate")
                .addAnnotation(override)
                .addModifiers(Modifier.PROTECTED)
                .addParameter(savedInstanceState)
                .addStatement("super.onCreate(savedInstanceState)")
//                .addStatement("setContentView(R.layout.activity_main)")
                .build();

        TypeSpec mainActivity = mainActivityBuilder.addMethod(onCreate)
                .build();

        JavaFile file = JavaFile.builder("com.my.plugin", mainActivity).build();

        try {
            file.writeTo(processingEnv.getFiler());
//            file.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
            return new TreeSet<>(Arrays.asList(
                    MyAnnotaion.class.getCanonicalName()));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}