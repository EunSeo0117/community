package ktb.week4.semi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ktb.week4.semi.CourseDto.*;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseCreateRequest request) {
        Long courseId = courseService.createCourse(request);
        return ResponseEntity.ok(courseId);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable("courseId") Long courseId,
                                       @RequestBody CourseUpdateRequest request) {
        courseService.updateCourse(request, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable("courseId") Long courseId) {
        CourseResponse response = courseService.getCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }


}
