package ru.javamentor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс представляющий модель топика
 *
 * @version 1.0
 * @author Java Mentor
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    @NotNull
    private String title;

    @Column
    @NotEmpty
    @NotNull
    private String content;

    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @Column
    @NotNull
    private boolean isModerate = false;

    @Column
    @NotNull
    private boolean completed;

    @Column
    @NotNull
    private Integer likes = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_topics", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> authors;

    @ManyToMany
    @JoinTable(name = "hashtags_topics", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private Set<Hashtag> hashtags;

    @ManyToMany
    @JoinTable(name = "topics_themes", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private Set<Theme> themes;

    @PreRemove
    public void preRemove() {
        for (Hashtag tag : hashtags) {
            Set<Topic> topics = tag.getTopics();
            topics.remove(this);
        }
    }

    public Topic(String title, String content, boolean completed, Set<User> authors, LocalDateTime dateCreated, boolean isModerate) {
        this.title = title;
        this.content = content;
        this.authors = authors;
        this.dateCreated = dateCreated;
        this.isModerate = isModerate;
        this.completed = completed;
    }

    public Topic(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
