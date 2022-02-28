package br.com.eduardo.msproduct.model

import com.amazonaws.services.dynamodbv2.datamodeling.*
import java.math.BigDecimal

@DynamoDBTable(tableName = "product")
class ProductDatabase(

) {
    @field:DynamoDBHashKey
    var barcode: String? = null

    @field:DynamoDBAttribute
    var description: String? = null

    @field:DynamoDBAttribute
    var coast: BigDecimal? = null

    @field:DynamoDBAttribute
    var unity: UnityDatabase? = null

    @field:DynamoDBAttribute
    var group: GroupDatabase? = null

    @field:DynamoDBAttribute
    var brand: BrandDatabase? = null

    @field:DynamoDBAttribute
    var size: SizeDatabase? = null

    @field:DynamoDBAttribute
    var observation: String? = null

    @field:DynamoDBAttribute
    var imageUrl: String? = null

    @field:DynamoDBTypeConvertedEnum
    @field:DynamoDBAttribute
    var status: ProductStatus? = null
}

@DynamoDBDocument
class GroupDatabase {
    @field:DynamoDBAttribute
    var name: String? = null
}

@DynamoDBDocument
class BrandDatabase{
    @field:DynamoDBAttribute
    var name: String? = null
}

@DynamoDBDocument
class SizeDatabase {
    @field:DynamoDBAttribute
    var name: String? = null
}

@DynamoDBDocument
class UnityDatabase{
    @field:DynamoDBAttribute
    var name: String? = null
}