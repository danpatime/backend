package com.example.api.contracts;

import com.example.api.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

interface ContractRepository extends JpaRepository<Contract, Long> {
}
