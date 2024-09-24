package com.glitch.wedeliver.di

import com.glitch.wedeliver.data.datasource.FoodDataSource
import com.glitch.wedeliver.data.repo.FoodsRepository
import com.glitch.wedeliver.retrofit.ApiUtils
import com.glitch.wedeliver.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
	@Provides
	@Singleton
	fun provideFoodsRepository(fds:FoodDataSource) : FoodsRepository {
		return FoodsRepository(fds)
	}

	@Provides
	@Singleton
	fun provideFoodsDataSource(fdao:FoodsDao) : FoodDataSource {
		return FoodDataSource(fdao)
	}

	@Provides
	@Singleton
	fun provideFoodsDao() : FoodsDao {
		return ApiUtils.getFoodsDao()
	}

}