package com.example.study.api.service.query;

import com.example.study.api.entity.Review;
import com.example.study.api.entity.Store;
import com.example.study.api.repository.ReviewRepository;
import com.example.study.api.repository.storeRepository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        int validPage = Math.max(page - 1, 0); // 요청한 page를 0-based로 변환
        Page<Review> reviews = reviewRepository.findAllByStore(store.getId(), PageRequest.of(validPage, 10));

        System.out.println("Fetched Reviews: " + reviews.getContent());
        return reviews;
    }

}
