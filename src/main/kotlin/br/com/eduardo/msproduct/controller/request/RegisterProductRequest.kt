package br.com.eduardo.msproduct.controller.request

import br.com.eduardo.msproduct.model.*
import java.math.BigDecimal

data class RegisterProductRequest(
    val description: String,
    var coast: BigDecimal,
    var barcode: String,
    var unity: UnityRequest,
    var group: GroupRequest,
    var brand: BrandRequest,
    var size: SizeRequest,
    var observation: String,
    var imageUrl: String
) {
    fun toModel(): Product = Product(
        this.barcode,
        this.description,
        this.coast,
        this.unity.toModel(),
        this.group.toModel(),
        this.brand.toModel(),
        this.size.toModel(),
        this.observation,
        this.imageUrl
    )
}

class GroupRequest(
    val name: String
) {
    fun toModel() = Group(this.name)
}

class BrandRequest(
    val name: String
) {
    fun toModel() = Brand(this.name)
}

class SizeRequest(
    val name: String
) {
    fun toModel() = Size(this.name)
}

class UnityRequest(
    val name: String
) {
    fun toModel() = Unity(this.name)
}