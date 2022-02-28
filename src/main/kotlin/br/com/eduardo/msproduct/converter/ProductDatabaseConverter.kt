package br.com.eduardo.msproduct.converter

import br.com.eduardo.msproduct.model.*
import java.math.BigDecimal

class ProductDatabaseConverter {

    companion object {
        fun toProduct(productDatabase: ProductDatabase): Product {
            with(productDatabase) {
                return Product(
                    barcode.orEmpty(),
                    description.orEmpty(),
                    coast ?: BigDecimal.ZERO,
                    toUnity(unity),
                    toGroup(group),
                    toBrand(brand),
                    toSize(size),
                    observation.orEmpty(),
                    imageUrl.orEmpty(),
                    status!!
                )
            }
        }

        private fun toSize(sizeDatabase: SizeDatabase?): Size {
            return Size(sizeDatabase?.name.orEmpty())
        }

        private fun toBrand(brandDatabase: BrandDatabase?): Brand {
            return Brand(brandDatabase?.name.orEmpty())
        }

        private fun toGroup(groupDatabase: GroupDatabase?): Group {
            return Group(groupDatabase?.name.orEmpty())
        }

        private fun toUnity(unityDatabase: UnityDatabase?): Unity {
            return Unity(unityDatabase?.name.orEmpty())
        }
    }



}