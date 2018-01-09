package der.springboot.web;

import der.springboot.entity.Products;
import der.springboot.service.IProductService;
import der.springboot.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
* @FileName:ProductsWeb
* @Description:
* @Author: Derrick Ye
*/
@Controller
@RequestMapping("/product")
public class ProductsWeb {
    @Autowired
    private IProductService productService;

    /**
     * add pagination to the products listing page,and show 5 products per page
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String getAll(@RequestParam(name = "page",defaultValue = "0") int page,
                         @RequestParam(name = "size",defaultValue = "5") int size,
                         Model model){
        Pageable pageable=new PageRequest(page,size);
        Page<Products> productPage=productService.findAll(pageable);
        PageWrapper<Products> pageWrapper=new PageWrapper<Products>(productPage,"/product/products");
        model.addAttribute("products", pageWrapper.getContent());
        model.addAttribute("page", pageWrapper);
        return "product/products";
    }

    /**
     * show the product by the given id
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id}")
    public String  getProductById(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product/productshow";
    }

    /**
     * show the product form page for updating the product by the given id
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product/productform";
    }

    /**
     * save or update the specific product and redirect the user to the product page
     * @param product
     * @return
     */
    @RequestMapping(value="/form",method=RequestMethod.POST)
    public String saveProduct(Products product){
        productService.updateProduct(product);
        return "redirect:/product/"+product.getId();
    }

    /**
     * show the product form page for new product
     * @param model
     * @return
     */
    @RequestMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Products());
        return "product/productform";
    }

    /**
     * delete the product by the given id and redirect the user to the product listing page
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/product/products";
    }
}
