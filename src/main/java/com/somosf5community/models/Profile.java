package com.somosf5community.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Column(nullable = true)
    private String name = "Nombre";
    @Column(nullable = true)
    private String surname = "Apellido";
    @Column(nullable = true)
    private String location;
    @Column(nullable = true)
    private String github;
    @Column(nullable = true)
    private String linkedin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Post> posts; 

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profiles_contacts", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private Set<Contact> contacts;
}
