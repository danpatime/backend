package com.example.api.contracts;

import com.example.api.domain.Contract;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface ContractRepository extends JpaRepository<Contract, Long> {
}
