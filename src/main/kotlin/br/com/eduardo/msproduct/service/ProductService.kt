package br.com.eduardo.msproduct.service

import br.com.eduardo.msproduct.controller.request.RegisterProductRequest
import br.com.eduardo.msproduct.model.Product
import br.com.eduardo.msproduct.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Validated
@Service
class ProductService(
    val productRepository: ProductRepository
) {
    private val logger = LoggerFactory.getLogger(ProductService::class.java)

    fun registerProduct(@Valid request: RegisterProductRequest, cId: String): Product {
        val product = productRepository.save(request.toModel())
        logger.info("action=save, cId=$cId")

        return product
    }

    fun deleteProduct(idProduct: String, cId: String) {
        productRepository.delete(idProduct)
        logger.info("action=delete, idProduct=$idProduct, cId=$cId")
    }

    fun findById(idProduct: String, cId: String): Product {
        val product = productRepository.findById(idProduct)
        logger.info("action=findById, idProduct=$idProduct, cId=$cId")

        return product
    }
}
