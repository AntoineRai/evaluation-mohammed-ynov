package com.example.borrowingservice.rest;

import com.example.borrowingservice.dto.BookDTO;
import com.example.borrowingservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceClient {
    private final RestTemplate restTemplate;

    private static final String BOOK_SERVICE_URL = "http://book-service/books/";
    private static final String USER_SERVICE_URL = "http://user-service/users/";

    @Autowired
    public ServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookDTO getBookById(Long bookId) {
        ResponseEntity<BookDTO> response = restTemplate.exchange(
                BOOK_SERVICE_URL + bookId,
                HttpMethod.GET,
                null,
                BookDTO.class
        );
        return response.getBody();
    }

    public UserDTO getUserById(Long userId) {
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                USER_SERVICE_URL + userId,
                HttpMethod.GET,
                null,
                UserDTO.class
        );
        return response.getBody();
    }

    public void updateBookAvailability(Long bookId, boolean isAvailable) {
        restTemplate.put(BOOK_SERVICE_URL + bookId + "/availability/" + isAvailable, null);
    }

    public void lockUser(Long userId) {
        restTemplate.put(USER_SERVICE_URL + userId + "/lock", null);
    }
}
