package io.web.bootstrap;

import io.web.model.Author;
import io.web.model.Book;
import io.web.model.Publisher;
import io.web.repositories.AuthorRepository;
import io.web.repositories.BookRepository;
import io.web.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Author maya = new Author("Maya", "Angelou");
        Publisher publisher = new Publisher();
        publisher.setName("Harper Collins");
        publisher.setAddress("New York");
        publisherRepository.save(publisher);

        Book fly = new Book("I Fly", "89477A", publisher);
        maya.getBooks().add(fly);
        fly.getAuthors().add(maya);

        authorRepository.save(maya);
        bookRepository.save(fly);


        Author rob = new Author("Robert", "Galbraith");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Pearson");
        publisher1.setAddress("London");
        publisherRepository.save(publisher1);

        Book cuckoo = new Book("Cuckoo's Calling", "35477A", publisher1);
        rob.getBooks().add(cuckoo);
        cuckoo.getAuthors().add(rob);

        authorRepository.save(rob);
        bookRepository.save(cuckoo);

    }
}