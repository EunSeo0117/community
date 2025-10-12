package ktb.week4.semi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static ktb.week4.semi.CourseDto.*;

@Entity
@Table(name = "courses")
@Getter
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String courseTitle;

    private String courseDescription;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chapter> chapters;

    public Course(CourseCreateRequest request) {
        this.courseTitle = request.courseTitle();
        this.courseDescription = request.courseDescription();
        this.chapters = new ArrayList<>();
    }

    public void updateCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void updateCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

}
