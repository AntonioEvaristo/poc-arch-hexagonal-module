package br.com.ae.integration.repository;

import br.com.ae.integration.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaEntityRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByCodigo(String codigo);
}
