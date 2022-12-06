package SpringBoot.App.product;

import SpringBoot.App.manufacture.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>  {
    @Query("FROM Product p WHERE p.name LIKE :name")
    Product getByName(@Param("name") String name);
}
