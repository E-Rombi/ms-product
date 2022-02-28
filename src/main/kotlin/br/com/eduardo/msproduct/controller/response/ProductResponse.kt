package br.com.eduardo.msproduct.controller.response

import br.com.eduardo.msproduct.model.*
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal

class ProductResponse(
    product: Product
) {
    val barcode: String
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val status: ProductStatus
    val description: String
    val coast: BigDecimal
    val unity: UnityResponse
    val group: GroupResponse
    val brand: BrandResponse
    val size: SizeResponse
    val observation: String
    val imageUrl: String

    init {
        this.barcode = product.barcode
        this.status = product.status
        this.description = product.description
        this.coast = product.coast
        this.unity = UnityResponse(product.unity)
        this.group = GroupResponse(product.group)
        this.brand = BrandResponse(product.brand)
        this.size = SizeResponse(product.size)
        this.observation = product.observation
        this.imageUrl = product.imageUrl
    }
}

class UnityResponse(
    unity: Unity
) {
    val name: String

    init {
        this.name = unity.name
    }
}

class GroupResponse(
    unity: Group
) {
    val name: String

    init {
        this.name = unity.name
    }
}

class BrandResponse(
    unity: Brand
) {
    val name: String

    init {
        this.name = unity.name
    }
}

class SizeResponse(
    unity: Size
) {
    val name: String

    init {
        this.name = unity.name
    }
}