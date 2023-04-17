package com.breakapp.room.di

import com.breakapp.apv2.ui.configs.viewmodel.repository.UserRepo
import com.breakapp.apv2.ui.configs.viewmodel.repository.UserRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.sql.DataSource


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: UserRepoImpl): UserRepo


    @Binds
    abstract fun bindDataSource(dataSource: DataSource) :DataSource
}