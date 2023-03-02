package com.nbcamp.gamematching.matchingservice.common.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class CreatePageable {

    public static Pageable createPageable(int page) {
//        Sort.Direction direction = Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, "modifiedAt");
        Pageable pageable = PageRequest.of(page - 1, 5);
        return pageable;
    }
}
