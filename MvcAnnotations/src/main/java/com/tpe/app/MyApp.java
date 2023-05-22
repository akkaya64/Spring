package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Course;
import com.tpe.service.CourseService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        //Spring Freamwork bize bu calass i hazirlamis. Bu class ile spring framework ile konusacagiz
        //Bu class ile configuration class larinin yerlerini annotation yapabilecegiz

        Course course = new Course();
        course.setName("SQL");

        CourseService service =  context.getBean("zoomService", CourseService.class);
        service.techCourse(course);
        service.saveCourse(course);

        // zoomService i new lemeden kullandik
        // zoomService ayni zamanda Repo yada ihtiyaci var ama biz onu da newlemedik.. 

    }
}
