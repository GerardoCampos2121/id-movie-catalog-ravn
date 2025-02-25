package com.ravn.challenge.ravn_challenge.dto;

import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PagingResultDTO<T> {
	
	 private Collection<T> content;
	    private Integer totalPages;
	    private long totalElements;
	    private Integer size;
	    private Integer page;
	    private boolean empty;

	    public PagingResultDTO(Collection<T> content, Integer totalPages, long totalElements, Integer size, Integer page, boolean empty) {
	        this.content = content;
	        this.totalPages = totalPages;
	        this.totalElements = totalElements;
	        this.size = size;
	        this.page = page + 1;
	        this.empty = empty;
	    }

}
