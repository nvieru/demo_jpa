package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String autor;
  private String title;
  @Column(nullable = false)
  private Integer volume;

  public Book() {
  }

  public Book(String autor, String title, Integer volume) {
    this.autor = autor;
    this.title = title;
    this.volume = volume;
  }

  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", autor='" + autor + '\'' +
            ", title='" + title + '\'' +
            ", volume=" + volume +
            '}';
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getVolume() {
    return volume;
  }

  public void setVolume(Integer volume) {
    this.volume = volume;
  }
}
