package com.sentry.notes.config;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableConfig {

    public static Pageable pageable(int numberOfPage, int numberOfSize, String... field){

        int page = Math.max(numberOfPage, 0);
        int size = (numberOfSize <= 0) ? 10 : Math.min(numberOfSize, 50);

        if(field == null || field.length == 0 || field[0].isBlank())
            return PageRequest.of( page, size);

        return PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC,field)
        );
    }
}
