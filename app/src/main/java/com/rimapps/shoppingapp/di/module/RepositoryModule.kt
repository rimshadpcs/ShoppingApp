package com.rimapps.shoppingapp.di.module

import com.rimapps.WishListDatabaseRepository
import com.rimapps.shoppingapp.features.WishListRepository
import com.rimapps.shoppingapp.features.productList.repository.api.ApiClient
import com.rimapps.shoppingapp.features.productList.repository.ProductRepository
import com.rimapps.shoppingapp.features.productList.repository.api.ProductRepositoryApi
import com.rimapps.shoppingapp.features.productList.repository.api.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    ):WishListRepository = databaseRepository

}