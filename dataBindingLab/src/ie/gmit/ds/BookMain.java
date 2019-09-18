package ie.gmit.ds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookMain {

	private static final String BOOKSTORE_XML = "./bookstore-jaxb.xml";

	public static void main(String[] args) {
		//createBookstore();
		
//		BookStore bookStore = readBookstoreFromXML(BOOKSTORE_XML);
//		// prove unmarshalling worked by printing the books list
//		for (Book book : bookStore.getBooksList()) {
//			System.out.printf("Book name: %s - ISBN: %s%n",
//					book.getName(), book.getIsbn());
//		}
		
		Book book = readSingleBookJSON("./book.json");
		System.out.printf("Book name: %s - ISBN: %s%n",
				book.getName(), book.getIsbn());
	}
	
	private static void createBookstore() {
		ArrayList<Book> bookList = new ArrayList<Book>();

		// create books
		Book book1 = new Book();
		book1.setIsbn("9780099512158");
		book1.setName("The Leopard");
		book1.setAuthor("Giuseppe Tomasi De Lampedusa");
		book1.setPublisher("Vintage Classics");
		bookList.add(book1);

		Book book2 = new Book();
		book2.setIsbn("978-0-6796-4019-6");
		book2.setName("Shadow Country");
		book2.setAuthor("Peter Matthiessen");
		book2.setPublisher("Random House");
		bookList.add(book2);

		// create bookstore, assigning book
		BookStore bookstore = new BookStore();
		bookstore.setName("Charlie Byrne's");
		bookstore.setLocation("Galway");
		bookstore.setBookList(bookList);
		
		JAXBContext context;
		try {
			// == XML ==
			context = JAXBContext.newInstance(BookStore.class);
			Marshaller jaxbMarshaller = context.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Write to System.out
			jaxbMarshaller.marshal(bookstore, System.out);
			// Write to File
			jaxbMarshaller.marshal(bookstore, new File("./bookstore-jaxb.xml"));
			
			// == JSON ==
			// Object to JSON
			ObjectMapper jsonMarshaller = new ObjectMapper();
			jsonMarshaller.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			String jsonBookStore =
					jsonMarshaller.writerWithDefaultPrettyPrinter()
					.writeValueAsString(bookstore);
			System.out.println(jsonBookStore);
			FileWriter fileWriter = new FileWriter("./bookstore.json");
			fileWriter.write(jsonBookStore);
			fileWriter.close();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static BookStore readBookstoreFromXML(String path) {
		Unmarshaller jaxbUnmarshaller;
		BookStore bookStore = null;
		try {
			jaxbUnmarshaller = JAXBContext.newInstance(BookStore.class).createUnmarshaller();
			bookStore = (BookStore) jaxbUnmarshaller.unmarshal(new FileReader(BOOKSTORE_XML));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return bookStore;
	}
	
	private static Book readSingleBookJSON(String path) {
		Book book = null;
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			URL url = new URL("file:" + path);
			book =  objectMapper.readValue(url, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
}