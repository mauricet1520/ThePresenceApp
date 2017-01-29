package com.tiy.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by crci1 on 1/28/2017.
 */
public class BookTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testBookandTableJoined() {
        Set<Author> howToProgramWithJavaAuthor = new HashSet<Author>();
        Set<Author> howToProgramWithJava2ndAuthors = new HashSet<Author>();
        Set<Author> howToPlayGuitarAuthor = new HashSet<Author>();

        Set<Book> trevorsBooks = new HashSet<Book>();
        Set<Book> johnsBooks = new HashSet<Book>();

        Author author = new Author();
        author.setAuthorName("Trevor Page");
        howToProgramWithJavaAuthor.add(author);

        assertEquals("Trevor Page", author.getAuthorName());

        Author author2 = new Author();
        author2.setAuthorName("John Doe");

        assertEquals("John Doe", author2.getAuthorName());

        howToProgramWithJava2ndAuthors.add(author);
        howToProgramWithJava2ndAuthors.add(author2);
        howToPlayGuitarAuthor.add(author2);

        assertEquals(howToPlayGuitarAuthor.size() +1, howToProgramWithJava2ndAuthors.size());

        Book book = new Book();
        book.setBookName("How to Program with Java");

        Book book2 = new Book();
        book2.setBookName("How to Program with Java 2nd Edition");

        Book book3 = new Book();
        book3.setBookName("How to Play Guitar");

        trevorsBooks.add(book);
        trevorsBooks.add(book2);
        johnsBooks.add(book2);
        johnsBooks.add(book3);
        author.setBooks(trevorsBooks);
        author2.setBooks(johnsBooks);
        book.setAuthors(howToProgramWithJavaAuthor);
        book2.setAuthors(howToProgramWithJava2ndAuthors);
        book3.setAuthors(howToPlayGuitarAuthor);
    }



}