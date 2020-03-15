package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book  {
    @Id
    @GeneratedValue(generator = "bookid_generator")
    @SequenceGenerator(
            name = "bookid_generator",
            sequenceName = "bookid_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private Author author;

    public void setAuthor (Author author)
    {
        System.out.println (author.getFirstName ());
        this.author = author;
        System.out.println ("end setAuthor");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
