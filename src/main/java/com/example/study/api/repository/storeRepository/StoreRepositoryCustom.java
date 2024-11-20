package com.example.study.api.repository.storeRepository;

import com.example.study.api.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
