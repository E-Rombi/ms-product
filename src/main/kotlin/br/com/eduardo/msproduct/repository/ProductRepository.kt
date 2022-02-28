package br.com.eduardo.msproduct.repository

import br.com.eduardo.msproduct.converter.ProductDatabaseConverter
import br.com.eduardo.msproduct.exception.Messages
import br.com.eduardo.msproduct.exception.ResourceNotFoundException
import br.com.eduardo.msproduct.model.Product
import br.com.eduardo.msproduct.model.ProductDatabase
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    private val dynamoDBMapper: DynamoDBMapper
) {

    fun save(product: Product): Product {
        dynamoDBMapper.save(product, buildSaveExpression())
        return product
    }

    fun delete(id: String) {
        dynamoDBMapper.load(ProductDatabase::class.java, id).also {
            dynamoDBMapper.delete(it)
        } ?: throw ResourceNotFoundException(Messages.resourceNotFound("Product"))
    }

    fun findById(id: String): Product {
        val productDatabase = dynamoDBMapper.load(ProductDatabase::class.java, id)
            ?: throw ResourceNotFoundException(Messages.resourceNotFound("Product"))

        return ProductDatabaseConverter.toProduct(productDatabase)
    }

    fun update(id:String, product: Product) = dynamoDBMapper.save(product, buildUpdateExpression(id))

    private fun buildSaveExpression(): DynamoDBSaveExpression {
        return DynamoDBSaveExpression().withExpectedEntry(
            "barcode",
            ExpectedAttributeValue(false)
        )
    }

    private fun buildUpdateExpression(id: String): DynamoDBSaveExpression {
        return DynamoDBSaveExpression().withExpectedEntry(
            "barcode",
            ExpectedAttributeValue(
                AttributeValue().withS(id)
            )
        )
    }
}