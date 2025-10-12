package ktb.week4.semi;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static ktb.week4.semi.CourseDto.*;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final EntityManager em;

    @Transactional
    public Long createCourse(CourseCreateRequest request) {
        Course course = new Course(request);
        em.persist(course);

        List<Chapter> chapters = new ArrayList<>();
        for (int i=1; i<=request.chapterCount().longValue(); i++) {
            chapters.add(createChapter(course, i+"주차"));
        }

        return course.getId();
    }

    private Chapter createChapter(Course course, String title) {
        Chapter chapter = new Chapter(course, title);
        chapterRepository.save(chapter);
        return chapter;
    }

    @Transactional
    public void updateCourse(CourseUpdateRequest request, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new IllegalArgumentException("course not found"));

        if (request.courseTitle()!=null) {
            course.updateCourseTitle(request.courseTitle());
        }

        if (request.courseDescription()!=null) {
            course.updateCourseDescription(request.courseDescription());
        }

        courseRepository.save(course);
    }

    @Transactional
    public CourseResponse getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("course not found"));

        List<Chapter> chapters = course.getChapters();
        List<ChapterResponse> chapterResponses = new ArrayList<>();
        for (Chapter chapter : chapters) {
            ChapterResponse response = ChapterResponse.of(chapter);
            chapterResponses.add(response);
        }

        return CourseResponse.of(course, chapterResponses);
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
