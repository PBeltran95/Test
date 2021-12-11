package ar.com.example.test.di

import ar.com.example.test.domain.PersonRepo
import ar.com.example.test.domain.PersonRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {


    @Binds
    abstract fun bindLocalRepoImpl(repoImpl: PersonRepoImpl): PersonRepo


}