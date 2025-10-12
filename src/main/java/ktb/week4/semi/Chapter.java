package ktb.week4.semi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chapters")
@Getter
@NoArgsConstructor
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private String chapterTitle;
    private String chapterDescription;

    public Chapter(Course course, String chapterTitle) {
        this.course = course;
        this.chapterTitle = chapterTitle;
        this.chapterDescription = "설명작성란";
    }
}
