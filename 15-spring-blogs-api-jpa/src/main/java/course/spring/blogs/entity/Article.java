package course.spring.blogs.entity;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Size(min=3, max=40)
    @NonNull
    private String title;
    @NotNull
    @Size(min=15, max=1024)
    @NonNull
    private String content;
    @NotNull
    @URL
    @NonNull
    private String imageUrl;
    @NonNull
    @ElementCollection
    private Set<String> tags;
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private User author;
    @PastOrPresent
    private LocalDateTime created = LocalDateTime.now();
    @PastOrPresent
    private LocalDateTime modified = LocalDateTime.now();
}
