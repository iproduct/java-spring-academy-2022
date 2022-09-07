package course.spring.blogs.entity;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    @Size(min=2, max=30)
    @NonNull
    private String firstName;
    @NotNull
    @Size(min=2, max=30)
    @NonNull
    private String lastName;
    @NotNull
    @Size(min=2, max=30)
    @NonNull
    private String username;
    @NotNull
    @Size(min=8)
    @NonNull
    private String password;
    private Role role = Role.READER;
    @URL
    private String imageUrl;
    @PastOrPresent
    private LocalDateTime created = LocalDateTime.now();
    @PastOrPresent
    private LocalDateTime modified = LocalDateTime.now();
}
