package com.developmentalportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentalportal.entity.Developers;

@Repository
public interface DeveloperRepository extends JpaRepository<Developers, Long> {

}
