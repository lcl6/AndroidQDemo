package com.my.plugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by  on 2021/8/25.
 */

@MyAnnotation
public class CoreTestUtils implements Plugin<Project> {
    @java.lang.Override
    public void apply(Project target) {
//        System.out.prinln("==============");
        ExtBean ext= (ExtBean) target.getExtensions().create("androidR",ExtBean.class);

        target.afterEvaluate(new Action<Project>() {
            @java.lang.Override
            public void execute(Project project) {
                System.out.println("=============="+ext.getName());
                System.out.println("=============="+ext.getAge());
            }
        });
    }
}
