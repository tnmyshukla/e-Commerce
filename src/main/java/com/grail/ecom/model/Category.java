package com.grail.ecom.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "category_id")
  private int id;
  @Column(name = "category_name")
  private String name;
}
