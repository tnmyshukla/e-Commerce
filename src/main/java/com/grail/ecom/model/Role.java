package com.grail.ecom.model;

import lombok.Data;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotEmpty
  @Column(nullable = false,unique = true)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;
}
