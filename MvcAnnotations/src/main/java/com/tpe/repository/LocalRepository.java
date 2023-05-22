package com.tpe.repository;

import com.tpe.domain.Course;
import org.springframework.stereotype.Component;

@Component
public class LocalRepository implements Repository{
    @Override
    public void save(Course course) {
        System.out.println(course.getName() + " dersi locale kaydediliyor");
    }
}
