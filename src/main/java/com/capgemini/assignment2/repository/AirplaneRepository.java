package com.capgemini.assignment2.repository;

        import com.capgemini.assignment2.models.Airplane;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Integer> {
//    Airplane findOne(long id);
}
