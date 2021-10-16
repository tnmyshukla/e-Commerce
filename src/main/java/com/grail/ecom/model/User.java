package com.grail.ecom.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotEmpty
  @NotNull
  private String firstName;

  private String lastName;

  @NotEmpty
  @NotNull
  @Column(unique = true)
  @Email(message = "errors.invalid_email")
  private String email;

  //not used not null bcz of google auth
  private String password;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
          inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")}
  )
  private List<Role> roles;
  public User(User user){
    this.firstName= user.getFirstName();
    this.lastName= user.getLastName();
    this.email= user.getEmail();
    this.password= user.getPassword();
    this.roles=user.getRoles();
  }
  public User(){

  }

}
