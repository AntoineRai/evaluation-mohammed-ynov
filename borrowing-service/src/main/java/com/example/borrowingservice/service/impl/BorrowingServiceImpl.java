package com.example.borrowingservice.service.impl;

import com.example.borrowingservice.dto.BookDTO;
import com.example.borrowingservice.dto.UserDTO;
import com.example.borrowingservice.entity.Borrowing;
import com.example.borrowingservice.repository.BorrowingRepository;
import com.example.borrowingservice.rest.ServiceClient;
import com.example.borrowingservice.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing findById(Long id) {
        return borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));
    }

    @Override
    @Transactional
    public Borrowing save(Borrowing borrowing) {
        // Vérifier si le livre est disponible
        BookDTO book = serviceClient.getBookById(borrowing.getBookId());
        if (!book.isAvailable()) {
            throw new RuntimeException("Le livre n'est pas disponible pour l'emprunt");
        }

        // Vérifier si l'utilisateur peut emprunter
        UserDTO user = serviceClient.getUserById(borrowing.getUserId());
        long nombreEmpruntsActuels = borrowingRepository.countByUserId(user.getId());
        if (nombreEmpruntsActuels >= user.getNombreMaxEmprunt()) {
            throw new RuntimeException("L'utilisateur a atteint son maximum d'emprunts");
        }

        // Enregistrer l'emprunt
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setReturnDate(null); // Non encore retourné
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        // Mettre à jour la disponibilité du livre
        serviceClient.updateBookAvailability(book.getId(), false);

        // Vérifier si l'utilisateur doit être bloqué
        if (nombreEmpruntsActuels + 1 >= user.getNombreMaxEmprunt()) {
            serviceClient.lockUser(user.getId());
        }

        return savedBorrowing;
    }

    @Override
    @Transactional
    public void returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        // Vérifier si déjà retourné
        if (borrowing.getReturnDate() != null) {
            throw new RuntimeException("Ce livre a déjà été retourné.");
        }

        // Mettre à jour l'emprunt
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);

        // Mettre à jour la disponibilité du livre
        serviceClient.updateBookAvailability(borrowing.getBookId(), true);
    }


    @Override
    public void deleteById(Long id) {
        borrowingRepository.deleteById(id);
    }
}
