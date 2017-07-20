package system.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    @Autowired
    private JpaRepository<T, ID> repository;

    @Override
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }
}