package az.lms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Entity
@Table(name = "books")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(name = "book_image")
    private String image;
    @Column(name = "book_count")
    private int count;
    @Column(name = "book_name", nullable = false)
    private String name;
    @Column(name = "published_time")
    private LocalDate publishedTime;
    @Column(name = "author_name", nullable = false)
    private String authorName;


    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    private Set<Author> authors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categories_id")
    private Category categories;

}
