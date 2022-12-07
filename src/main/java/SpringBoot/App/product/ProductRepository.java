package SpringBoot.App.product;

import SpringBoot.App.manufacture.Manufacture;
import SpringBoot.App.product.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>  {
    @Query("FROM Product p WHERE p.name LIKE :name")
    Product getByName(@Param("name") String name);

    @Query (value = "SELECT * FROM product  p WHERE  p.manufacture_id :: text  = ':id' ",
                    nativeQuery = true)
    Set<Product> getManufactureProductsById(@Param("id") UUID id);

    @Query (
            nativeQuery = true, value =
            "SELECT p.id, p.name, p.price, p.manufacture_id FROM product p JOIN manufacture m ON m.id = p.manufacture_id WHERE m.name LIKE  :name "
    )
    Set<Product> getManufactureProductsByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM product WHERE manufacture_id::text = ':id' ")
    void deleteManufactureProducts(@Param("id") String id);
}
