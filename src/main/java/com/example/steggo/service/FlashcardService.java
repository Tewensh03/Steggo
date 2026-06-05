package com.example.steggo.service;

import com.example.steggo.model.Collection;
import com.example.steggo.model.Flashcard;
import com.example.steggo.repository.CollectionRepository;
import com.example.steggo.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final CollectionRepository collectionRepository;

    public FlashcardService(FlashcardRepository flashcardRepository, CollectionRepository collectionRepository) {
        this.flashcardRepository = flashcardRepository;
        this.collectionRepository = collectionRepository;
    }

    public List<Flashcard> getFlashcardByCollection(Long collectionId) {
        return flashcardRepository.findByCollectionId(collectionId);
    }

    public Flashcard addFlashcardToCollection(Long collectionId, Flashcard flashcard) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RuntimeException("Collection not found with id" + collectionId));

        flashcard.setCollection(collection);

        return flashcardRepository.save(flashcard);
    }

    public Optional<Flashcard> getFlashcardById(Long id) {
        return flashcardRepository.findById(id);
    }

    public void deleteFlashcardById(Long id) {
        flashcardRepository.deleteById(id);
    }
}
