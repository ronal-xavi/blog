package com.ronal.blog.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_commentary")
public class CommentaryDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCommentary;
    private String nombre;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private PublicationDAO publicationDAO;

}
