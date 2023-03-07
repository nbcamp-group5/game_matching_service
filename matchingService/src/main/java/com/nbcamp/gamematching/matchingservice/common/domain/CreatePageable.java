package com.nbcamp.gamematching.matchingservice.common.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class CreatePageable {

    public static Pageable createPageable(int page) {
//        Sort.Direction direction = Sort.Direction.ASC;
//        Sort sort = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(page - 1, 50);
        return pageable;
    }
}
