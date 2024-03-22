
package sasf.jayon.soapservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sasf.jayon.soapservice.model.ProductDB;


public interface ProductRepository extends JpaRepository<ProductDB, Integer> {

    ProductDB findByName(String name);

}
