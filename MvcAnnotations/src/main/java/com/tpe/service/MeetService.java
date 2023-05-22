package com.tpe.service;

import com.tpe.domain.Course;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MeetService implements CourseService{

//private Repository repository;
//
//    public MeetService(@Qualifier ("LocalRepository") Repository repository) {
//        this.repository = repository;
//    }

    @Override
    public void techCourse(Course course) {
        //System.out.println(course.getName() + " dersi meet ile anlatiliyor");
    }

    @Override
    public void saveCourse(Course course) {
        //repository.save(course);
    }
}
