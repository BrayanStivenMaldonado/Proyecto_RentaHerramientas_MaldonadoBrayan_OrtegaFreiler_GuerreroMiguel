package com.alquiler.alquiler_app.infrastructure.repository.Returns;

import com.alquiler.alquiler_app.domain.entities.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {
}
