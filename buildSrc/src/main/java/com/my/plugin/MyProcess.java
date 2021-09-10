package com.my.plugin;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Created by  on 2021/9/9.
 */

@SupportedAnnotationTypes({"com.example.myprocessor.MyAnnotaion"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class MyProcess extends AbstractProcessor {
    private ProcessingEnvironment processingEnv;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("-------start process----------");
        creatClz(processingEnv);
        return true;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        super.init(processingEnv);

        System.out.println("-------start init----------");

    }

    private void creatClz(ProcessingEnvironment processingEnv) {
        System.out.println("-------processingEnv----------");
        ClassName activity = ClassName.get("android.app", "Activity");

        TypeSpec.Builder mainActivityBuilder = TypeSpec.classBuilder("MActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(activity);

        ClassName override = ClassName.get("java.lang", "Override");

        ClassName bundle = ClassName.get("android.os", "Bundle");

        ClassName nullable = ClassName.get("android.support.annotation", "Nullable");

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
        return Collections.singleton("aaa");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
