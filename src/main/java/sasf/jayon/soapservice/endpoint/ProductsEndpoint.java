
package sasf.jayon.soapservice.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import sasf.jayon.soapservice.model.ProductDB;
import sasf.jayon.soapservice.repository.ProductRepository;
import sasf.jayon.soapservice.springsoap.products.gen.GetProductRequest;
import sasf.jayon.soapservice.springsoap.products.gen.GetProductResponse;
import sasf.jayon.soapservice.springsoap.products.gen.GetProductsRequest;
import sasf.jayon.soapservice.springsoap.products.gen.GetProductsResponse;
import sasf.jayon.soapservice.springsoap.products.gen.PostProductRequest;
import sasf.jayon.soapservice.springsoap.products.gen.PostProductResponse;
import sasf.jayon.soapservice.springsoap.products.gen.Product;
import sasf.jayon.soapservice.utils.Mapper;

@Endpoint
public class ProductsEndpoint {

    private static final String NAMESPACE_URI = "http://soapservice.jayon.sasf/springsoap/products/gen";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper _map;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {

        GetProductResponse response = new GetProductResponse();
        response.setProduct(_map.mapObject(
                productRepository.findByName(request.getName()), Product.class));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {

        List<ProductDB> productsFromDb = productRepository.findAll();
        List<Product> products = productsFromDb.stream()
                .map(product -> _map.mapObject(product, Product.class))
                .collect(Collectors.toList());

        GetProductsResponse response = new GetProductsResponse();
        response.getProducts().addAll(products);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postProductRequest")
    @ResponsePayload
    public PostProductResponse postProducts(@RequestPayload PostProductRequest request) {

        PostProductResponse response = new PostProductResponse();

        ProductDB product = _map.mapObject(request.getProduct(), ProductDB.class);

        response.setProduct(_map.mapObject(productRepository.save(product), Product.class));

        return response;
    }

}
