package com.rimapps.shoppingapp.features.wishList.repository

import com.rimapps.shoppingapp.features.wishList.data.FavouriteProductEntity
import com.rimapps.shoppingapp.features.wishList.dataBase.WishListDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WishListDatabaseRepositoryTest{

  private lateinit var repository: WishListDatabaseRepository
  private  var dao = mockk<WishListDao>()

  @Before
  fun setup(){
   repository = WishListDatabaseRepository(dao)
  }

 @Test
 fun `when given product is saved in db it returns true`() = runTest{
  coEvery {
   dao.isProductFavourite("1")
  }returns FavouriteProductEntity(
   "1","banana"
  )
  assert(repository.isFavourite("1"))
 }

 @Test
 fun `when given product is not saved in db returns false`()= runTest {

  coEvery {
   dao.isProductFavourite("1")
  }returns null

  assert(!repository.isFavourite("1"))
 }

@Test
fun `when given product is save in db it returns true`() = runTest {
    coEvery {
        dao.isProductFavourite("1")
    }returns FavouriteProductEntity("1","banana")
    assert(repository.isFavourite("1"))
}


}