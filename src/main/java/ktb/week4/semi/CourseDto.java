package ktb.week4.semi;

import java.util.List;

public class CourseDto {
    public record CourseCreateRequest(
            String courseTitle,
            String courseDescription,
            Integer chapterCount
    ) {}

    public record CourseUpdateRequest(
            String courseTitle,
            String courseDescription
    ) {}

    public record CourseResponse(
            String courseTitle,
            String courseDescription,
            List<ChapterResponse> chapters
    ) {
        public static CourseResponse of(Course course, List<ChapterResponse> chapters) {
            return new CourseResponse(
                    course.getCourseTitle(),
                    course.getCourseDescription(),
                    chapters
            );
        }
    }

    public record ChapterResponse(
            String chapterTitle,
            String chapterDescription
    ) {
        public static ChapterResponse of(Chapter chapter) {
            return new ChapterResponse(
                    chapter.getChapterTitle(),
                    chapter.getChapterDescription()
            );
        }
    }

    public record ChapterUpdateRequest(
            String chapterTitle,
            String chapterContent
    ) {}

}
