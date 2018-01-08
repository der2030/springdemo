package der.springboot.service;

import der.springboot.entity.Products;
import der.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
/**
* @FileName:ProductService
* @Description:
* @Author: Derrick Ye
*/
@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Products> findAll(Pageable pageable) {
        if(pageable==null){
            return null;
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public Products saveProduct(Products products) {
        if(products==null)
            return null;
        return productRepository.save(products);
    }

    @Override
    public Products updateProduct(Products products) {
        if(products==null)
            return null;
        return productRepository.saveAndFlush(products);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Boolean res=false;
        if(id!=null ){
            productRepository.delete(id);
            res=true;
        }
        return res;
    }
}
