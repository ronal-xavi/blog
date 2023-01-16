package com.ronal.blog.dao;


import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_publication")
public class PublicationDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublication")
    private Long idPublication;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "publicationDAO", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentaryDAO> commentaryDAO = new HashSet<>();

}
