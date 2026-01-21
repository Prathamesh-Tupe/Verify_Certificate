package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.model.Certificate;
import com.repository.cerRepo;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") 
public class CertificateController {
    
	@Autowired
	cerRepo repository;
	
	@PostMapping("/api/certificates")	
	
	public ResponseEntity<?> uploadCertificate(
	        @RequestParam String studentName,
	        
	        @RequestParam String courseName,
	        @RequestParam MultipartFile file) throws IOException {

	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    String uploadDir = "C:\\Users\\PRATHAMESH\\Downloads\\atmAPI\\atmAPI\\uploads\\certificates/";
	    File saveFile = new File(uploadDir + fileName);
	    file.transferTo(saveFile);

	    Certificate cert = new Certificate();
	    cert.setCertificateId("CERT-" + UUID.randomUUID());
	    cert.setStudentName(studentName);
	    cert.setCourseName(courseName);
	    cert.setImagePath("/uploads/certificates/" + fileName);
	    cert.setStatus("ACTIVE");

	    repository.save(cert);

	    return ResponseEntity.ok(cert);
	    
	}
	
	
	@GetMapping("/api/verify/{certificateId}")
	public ResponseEntity<?> verify(@PathVariable String certificateId) {

	    Optional<Certificate> cert = repository.findByCertificateId(certificateId);

	    if (cert.isPresent() && cert.get().getStatus().equals("ACTIVE")) {
	        return ResponseEntity.ok(cert.get());
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Invalid or Revoked Certificate");
	}


}
