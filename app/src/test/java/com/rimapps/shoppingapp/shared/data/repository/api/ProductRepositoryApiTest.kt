package com.rimapps.shoppingapp.shared.data.repository.api
import com.rimapps.shoppingapp.features.productDetails.data.AsyncResult
import com.rimapps.shoppingapp.features.productDetails.data.ProductDetailsEntity
import com.rimapps.shoppingapp.features.productList.data.ProductEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductRepositoryAPITest {
    private lateinit var repository: ProductRepositoryApi
    private val service = mockk<ProductService>()

    @Before
    fun setup() {
        repository = ProductRepositoryApi(service)
    }

    @Test
    fun `product entity correctly maps to business object`()= runTest {

        coEvery {
            service.getProductList()

        } returns (0..2).map {
            ProductEntity(
                it.toString(),
                "title $it",
                "price",
                0.00,
                "",
            )
        }

        val result = repository.getProductList() as AsyncResult.Success

        assert(result.data.size==3)
        assert(result.data[1].title=="title 1")

    }

    @Test
    fun `repository correctly maps details to business objects`() = runTest{
        coEvery {
            service.getProductDetails("1")
        } returns ProductDetailsEntity(
            "Hello",
            "description",
            "full desc",
            1.00,
            "",
            listOf(""),
            listOf(""),
        )
        val result = repository.getProductDetails("1")
        assert(result.title=="Hello")



    }

}