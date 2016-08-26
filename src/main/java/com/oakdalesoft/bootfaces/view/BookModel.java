package com.oakdalesoft.bootfaces.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.oakdalesoft.bootfaces.domain.Book;
import com.oakdalesoft.bootfaces.persistence.BookRepository;


/**
 * Created by Alex on 07/03/2015.
 */
@ManagedBean(name = "model", eager = true)
@RequestScoped
public class BookModel {

	@ManagedProperty(value = "#{book}")
	private BookView book;

	@ManagedProperty(value = "#{bookRepository}")
	private BookRepository bookRepository;



	public void setBook(BookView book) {

		this.book = book;
	}



	public BookView getBook() {

		return book;
	}



	public BookRepository getBookRepository() {

		return bookRepository;
	}



	public void setBookRepository(BookRepository bookRepository) {

		this.bookRepository = bookRepository;
	}



	public String doCreateBook() {

		Book created = new Book();
		created.setId(this.book.getId());
		created.setTitle(this.book.getTitle());
		created.setPrice(this.book.getPrice());
		created.setnbofpage(this.book.getnbofpage());
		created.setDescription(this.book.getDescription());
		Book newBook = this.bookRepository.save(created);

		FacesContext.getCurrentInstance().addMessage("errors",
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
				"The book " + created.getTitle() + " has been created with id=" + newBook.getId()));

		this.book.setTitle("");
		this.book.setPrice(null);
		this.book.setDescription("");
		this.book.setIllustrations(false);
		this.book.setnbofpage(null);

		return "index.xhtml";
	}



	public void doFindBookById() {

		Book found = bookRepository.findOne(this.book.getId());
		this.book.setId(found.getId());
		this.book.setTitle(found.getTitle());
		this.book.setPrice(found.getPrice());
		this.book.setnbofpage(found.getnbofpage());
		this.book.setDescription(found.getDescription());
		this.book.setDescription(found.getDescription());
	}



	public List<BookView> findAllBooks() {

		List<BookView> books = new ArrayList<BookView>();
		for (Book entity : this.bookRepository.findAll()) {
			BookView view = new BookView();
			view.setId(entity.getId());
			view.setTitle(entity.getTitle());
			view.setPrice(entity.getPrice());
			view.setnbofpage(entity.getnbofpage());
			view.setDescription(entity.getDescription());
			view.setDescription(entity.getDescription());
			books.add(view);
		}
		return books;
	}
}
