package com.rimapps.shoppingapp.features.productList.presentation

import com.rimapps.shoppingapp.TestCoroutineRule
import com.rimapps.shoppingapp.features.productDetails.data.AsyncResult
import com.rimapps.shoppingapp.features.productList.business.Product
import com.rimapps.shoppingapp.features.wishList.usecase.AddOrRemoveFromWishListUseCase
import com.rimapps.shoppingapp.features.wishList.usecase.IsProductInWishListUseCase
import com.rimapps.shoppingapp.shared.data.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

 @get:Rule
 var testRule = TestCoroutineRule()
 private val dispatcher = StandardTestDispatcher()

 private lateinit var viewModel: ProductListViewModel
 private val repository = mockk<ProductRepository>()
 private val addOrRemoveFromWishListUseCase = mockk<AddOrRemoveFromWishListUseCase>()
 private val isProductInWishListUseCase = mockk<IsProductInWishListUseCase>()
 private val listOfProducts = (0..2).map {
  Product("title", "description", 6.0, "", "id-$it")
 }

 private val lisOfProductCardList = (0..2).map {
  ProductCardViewState(it.toString(), "title", "desc", "3.0", "", false)
 }

 @Before
 fun setup() {
  coEvery { isProductInWishListUseCase.execute(any()) } returns false
  coEvery {
   isProductInWishListUseCase.execute("id-1")
  } returns true
  coEvery {
   repository.getProductList()
  } returns AsyncResult.Success(listOfProducts)

  viewModel = ProductListViewModel(
   repository,
   addOrRemoveFromWishListUseCase,
   isProductInWishListUseCase,
   dispatcher
  )
 }

 //This test working fine
 @Test
 fun `initial state is Loading`() = testRule.runTest {
  val viewModel = ProductListViewModel(
   repository,
   addOrRemoveFromWishListUseCase,
   isProductInWishListUseCase,
   dispatcher
  )

  val actualState = viewModel.viewState.first()

  Assert.assertEquals(ProductListViewState.Loading, actualState)
 }

 //Not working
 @Test
 fun `Load method correctly when creates the viewState`() = runTest {
  val values = mutableListOf<ProductListViewState>()

  val job = launch {
   viewModel.viewState.collect {

    values.add(it)
   }

   viewModel.loadProductList()
   dispatcher.scheduler.advanceUntilIdle()
   assert(values[1] ==
           ProductListViewState.Content(
            (0..2).map {
             ProductCardViewState(
              "id-$it",
              "title",
              "description",
              "US $ 6.0",
              "",
              it == 1
             )
            }
           ))

  }
  job.cancel()
 }
}

