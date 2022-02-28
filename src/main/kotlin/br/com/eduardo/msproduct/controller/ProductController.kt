package br.com.eduardo.msproduct.controller

import br.com.eduardo.msproduct.controller.request.RegisterProductRequest
import br.com.eduardo.msproduct.controller.response.ProductResponse
import br.com.eduardo.msproduct.repository.ProductRepository
import br.com.eduardo.msproduct.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController(
    val productService: ProductService

) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)

    @PostMapping
    fun registerProduct(
        @RequestHeader(required = false, name = "Correlation-id") correlationId: String?,
        @RequestBody request: RegisterProductRequest,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Any> {
        val cId = correlationId ?: UUID.randomUUID().toString()

        val product = productService.registerProduct(request, cId)

        val uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.barcode).toUri()

        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping("/{idProduct}")
    fun deleteProduct(
        @RequestHeader(required = false, name = "Correlation-id") correlationId: String?,
        @PathVariable idProduct: String
    ): ResponseEntity<Any> {
        val cId = correlationId ?: UUID.randomUUID().toString()

        productService.deleteProduct(idProduct, cId)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/{idProduct}")
    fun findById(
        @RequestHeader(required = false, name = "Correlation-id") correlationId: String?,
        @PathVariable idProduct: String
    ): ResponseEntity<ProductResponse> {
        val cId = correlationId ?: UUID.randomUUID().toString()

        val product = productService.findById(idProduct, cId)

        return ResponseEntity.ok(ProductResponse(product))
    }
}