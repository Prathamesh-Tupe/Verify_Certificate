package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Certificate;

@Repository
public interface cerRepo extends JpaRepository<Certificate, Long> {
	Optional<Certificate> findByCertificateId(String certificateId);
}
