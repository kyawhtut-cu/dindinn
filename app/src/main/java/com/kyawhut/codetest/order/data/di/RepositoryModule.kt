package com.kyawhut.codetest.order.data.di

import com.kyawhut.codetest.order.ui.ingredient.IngredientRepository
import com.kyawhut.codetest.order.ui.ingredient.IngredientRepositoryImpl
import com.kyawhut.codetest.order.ui.order.OrderRepository
import com.kyawhut.codetest.order.ui.order.OrderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideOrderRepository(repository: OrderRepositoryImpl): OrderRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideIngredientRepository(repository: IngredientRepositoryImpl): IngredientRepository {
        return repository
    }
}
