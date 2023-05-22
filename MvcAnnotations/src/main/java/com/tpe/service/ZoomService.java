package com.tpe.service;

import com.tpe.domain.Course;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ZoomService implements CourseService{
    // Injection Islemi icin onumuzde 3 yol var :

    // 1) Field Injection
//    @Autowired
//    @Qualifier("cloudRepository")//secici
//    private Repository repository;

//
//    // 2) Setter Injection
//    @Autowired
//    @Qualifier("cloudRepository")
//    private Repository repository;
//    public void setRepository(Repository repository) {
//        this.repository = repository;
//    }

    //    // 3) Const. Injection.
    private Repository repository;
    @Autowired
    public ZoomService(@Qualifier("cloudRepository") Repository repository) {

        this.repository = repository;
    }

    @Override
    public void techCourse(Course course) {
        System.out.println(course.getName() + " dersi zoom ile anlatiliyor");
    }

    @Override
    public void saveCourse(Course course) {
        repository.save(course);
    }
}
