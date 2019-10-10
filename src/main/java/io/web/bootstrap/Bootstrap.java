package io.web.bootstrap;

import io.web.model.Author;
import io.web.model.Book;
import io.web.repositories.AuthorRepository;
import io.web.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author maya = new Author("Maya", "Angelou");
        Book book = new Book("I Fly", "89477A", "Harper Collins");
        maya.getBooks().add(book);
        book.getAuthors().add(maya);
        authorRepository.save(maya);
        bookRepository.save(book);

        Author rob = new Author("Robert", "Galbraith");
        Book book1 = new Book("Cuckoo's Calling", "35477A", "Harper Collins");
        rob.getBooks().add(book1);
        book1.getAuthors().add(rob);
        authorRepository.save(rob);
        bookRepository.save(book1);
    }
}