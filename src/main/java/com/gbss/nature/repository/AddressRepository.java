package com.gbss.nature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbss.nature.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
