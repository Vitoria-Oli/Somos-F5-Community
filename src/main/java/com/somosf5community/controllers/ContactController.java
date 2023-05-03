package com.somosf5community.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somosf5community.models.Contact;
import com.somosf5community.services.ContactService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/contacts")
public class ContactController {
    
    private final ContactService contactService;
    
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.findAll();
    }
    
    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
       return contactService.findById(id) ;
    }

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        Contact savedContact = contactService.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.ok("Contact deleted successfully");
    }

    @PostMapping("/addContact/{id}")
    public ResponseEntity<String> addContact(@PathVariable Long id) {
        contactService.addContactToUser(id);
        return ResponseEntity.ok("Contact added successfully");
    }
}
