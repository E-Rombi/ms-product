package br.com.eduardo.msproduct.model

import com.amazonaws.services.dynamodbv2.datamodeling.*
import org.apache.catalina.mbeans.GroupMBean
import java.math.BigDecimal

@DynamoDBTable(tableName = "product")
class Product(
    @field:DynamoDBHashKey var barcode: String,
    @field:DynamoDBAttribute var description: String,
    @field:DynamoDBAttribute var coast: BigDecimal,
    @field:DynamoDBAttribute var unity: Unity,
    @field:DynamoDBAttribute var group: Group,
    @field:DynamoDBAttribute var brand: Brand,
    @field:DynamoDBAttribute var size: Size,
    @field:DynamoDBAttribute var observation: String,
    @field:DynamoDBAttribute var imageUrl: String
) {
    constructor(
        barcode: String,
        description: String,
        coast: BigDecimal,
        unity: Unity,
        group: Group,
        brand: Brand,
        size: Size,
        observation: String,
        imageUrl: String,
        status: ProductStatus
    ) : this(barcode, description, coast, unity, group, brand, size, observation, imageUrl) {
            this.status = status
        }

    @field:DynamoDBTypeConvertedEnum
    @field:DynamoDBAttribute var status: ProductStatus = ProductStatus.ACTIVE
}

@DynamoDBDocument
class Group(
    @field:DynamoDBAttribute
    val name: String
)

@DynamoDBDocument
class Brand(
    @field:DynamoDBAttribute
    val name: String
)

@DynamoDBDocument
class Size(
    @field:DynamoDBAttribute
    val name: String
)

@DynamoDBDocument
class Unity(
    @field:DynamoDBAttribute
    val name: String
)

enum class ProductStatus {
    ACTIVE, INACTIVE
}
