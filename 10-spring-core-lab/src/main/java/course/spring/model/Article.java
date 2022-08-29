package course.spring.model;

import course.spring.dao.Identifiable;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Article implements Identifiable<Long> {
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private String imageUrl;
    @NonNull
    private List<String> tags;
    @NonNull
    private String author;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();
}
