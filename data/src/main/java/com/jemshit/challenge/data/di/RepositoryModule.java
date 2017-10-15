package com.jemshit.challenge.data.di;

import com.jemshit.challenge.data.repository.DataRepository;
import com.jemshit.challenge.domain.repository.Repository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    public abstract Repository provideRepository(DataRepository repository);
}
