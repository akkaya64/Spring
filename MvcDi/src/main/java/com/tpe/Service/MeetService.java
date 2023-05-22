package com.tpe.Service;

import com.tpe.domain.Course;
import com.tpe.repo.Repo;

public class MeetService implements CourseService {
    private Repo repo;

    public MeetService(Repo repo) {
        this.repo = repo;
    }

    @Override // asagidaki methodu zaten olusturmustuk. CourseService ye implement ettigimiz icin MeetService ye
    // CourseService in Constarctor methodunu @Override yapmamiz gerekiyor. Ancak  @Override yazmamaiza gerek
    // yok obsiyonel olarak yazabiliriz.
    public void teachCourse(Course course) {
        System.out.println(course.getName() + " dersi Meet ile anlatiliyor");
    }

    @Override
    public void save(Course course) {
        repo.save(course);
    }

}
