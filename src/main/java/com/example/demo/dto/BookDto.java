package com.example.demo.dto;

import javax.persistence.Column;

public class BookDto {
    private Long id;
    private String autor;
    private String title;
    private Integer volume;

    public BookDto() {
    }

    public BookDto(Long id, String autor, String title, Integer volume) {
        this.id = id;
        this.autor = autor;
        this.title = title;
        this.volume = volume;
    }
}
