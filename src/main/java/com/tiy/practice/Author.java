package com.tiy.practice;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by crci1 on 1/28/2017.
 */
@Entity
public class Author
{
    private Long authorId;
    private String authorName;
    private Set<Book> books;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="author_id")
    public Long getAuthorId()
    {
        return authorId;
    }
    public void setAuthorId(Long authorId)
    {
        this.authorId = authorId;
    }

    @Column(name="author_name")
    public String getAuthorName()
    {
        return authorName;
    }
    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    @ManyToMany(cascade=CascadeType.ALL, mappedBy="authors")
    public Set<Book> getBooks()
    {
        return books;
    }
    public void setBooks(Set<Book> books)
    {
        this.books = books;
    }
}
