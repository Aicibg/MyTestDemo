package com.example;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

/**
 * Created by DongHao on 2016/9/27.
 * Description:
 */

public class CakeProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager=processingEnv.getMessager();
        for (Element element:roundEnv.getElementsAnnotatedWith(GetMsg.class)){
            PackageElement packageElement=(PackageElement) element.getEnclosingElement();
            //获取注解所在类的包名
            String packageName=packageElement.getQualifiedName().toString();
            TypeElement classElement= (TypeElement) element;
            //获取注解所在类的类名
            String className=classElement.getSimpleName().toString();
            //获取注解所在类的全类名
            String fullClassName=classElement.getQualifiedName().toString();
            VariableElement variableElement= (VariableElement) element.getEnclosingElement();
            String methodName=variableElement.getSimpleName().toString();

            //获取注解的值
            int id=classElement.getAnnotation(GetMsg.class).id();
            String name=classElement.getAnnotation(GetMsg.class).name();

            messager.printMessage(Diagnostic.Kind.NOTE,"Annotation class: packageName="+packageName);
            messager.printMessage(Diagnostic.Kind.NOTE,"Annotation class: className="+className);
            messager.printMessage(Diagnostic.Kind.NOTE,"Annotation class: methodName="+methodName);
            messager.printMessage(Diagnostic.Kind.NOTE,"Annotation class: fullClassName="+fullClassName);
            messager.printMessage(Diagnostic.Kind.NOTE,"Annotation class: id="+id+" name="+name);
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types=new LinkedHashSet<>();
        types.add(GetMsg.class.getCanonicalName());
        return types;
    }
}
