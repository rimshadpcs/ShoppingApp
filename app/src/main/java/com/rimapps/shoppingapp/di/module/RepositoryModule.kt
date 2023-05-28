package com.rimapps.shoppingapp.di.module

import android.content.Context
import androidx.room.Room
import com.rimapps.shoppingapp.features.wishList.repository.WishListDatabaseRepository
import com.rimapps.shoppingapp.features.wishList.repository.WishListRepository
import com.rimapps.shoppingapp.features.productList.repository.api.ApiClient
import com.rimapps.shoppingapp.features.productList.repository.ProductRepository
import com.rimapps.shoppingapp.features.productList.repository.api.ProductRepositoryApi
import com.rimapps.shoppingapp.features.productList.repository.api.ProductService
import com.rimapps.shoppingapp.features.wishList.dataBase.WishListDao
import com.rimapps.shoppingapp.features.wishList.dataBase.WishListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideProductService(): ProductService = ApiClient.getService()


    @Provides
    fun providesProductRepositoryApi(
        service: ProductService
    ): ProductRepositoryApi =  ProductRepositoryApi(service)

    @Provides
    fun providesProductRepository(
        productRepositoryAPI: ProductRepositoryApi
    ): ProductRepository = productRepositoryAPI

    @Provides
    fun providesWishListRepository(
        databaseRepository: WishListDatabaseRepository
    ): WishListRepository = databaseRepository

    @Provides
    fun providesWishListDAO(
        @ApplicationContext context: Context
    ): WishListDao {
        val db = Room.databaseBuilder(
            context, WishListDatabase::class.java,
            "shoppingApp-database"
        ).build()
        return db.wishListDao()
    }
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}

