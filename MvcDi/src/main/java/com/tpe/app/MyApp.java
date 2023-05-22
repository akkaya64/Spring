package com.tpe.app;

import com.tpe.Service.CourseService;
import com.tpe.Service.MeetService;
import com.tpe.Service.ZoomService;
import com.tpe.domain.Course;
import com.tpe.repo.CloudRepo;
import com.tpe.repo.Repo;

public class MyApp {
    public static void main(String[] args) {
        Course course = new Course();
        course.setName("Adv Java");
        //Dersin zoom ile anlatilmasini istiyorum.
//        ZoomService zoomService = new ZoomService();
//        zoomService.teachCourse(course);

        //Dersin googler meet ile anlatilmasini istiyorum

//        MeetService meetService = new MeetService();
//        meetService.teachCourse(course);

        // daha kolay bir bakim ve gelistirme icin interface kullandik
//        CourseService courseService = new MeetService();
//        courseService.teachCourse(course);

        //Dersi buluta yada lokale kaydetmek istenirse (Persis: kalici hale getirmek )

        Repo repo = new CloudRepo();
        CourseService service2 = new ZoomService(repo);
        service2.teachCourse(course);
        service2.save(course);
    }
}
