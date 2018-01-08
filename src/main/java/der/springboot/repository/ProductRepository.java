package der.springboot.repository;

import der.springboot.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

/** 
* @FileName:ProductRepository
* @Description:
* @Author: Derrick Ye
*/
public interface ProductRepository extends JpaRepository<Products,Long> {
    /**
     * return a single product by the given producnt name
     * @param productname
     * @return
     */
    public Products findByProductname(String productname);
}
