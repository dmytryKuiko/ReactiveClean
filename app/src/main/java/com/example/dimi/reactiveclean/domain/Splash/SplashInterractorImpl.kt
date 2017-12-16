package com.example.dimi.reactiveclean.domain.Splash

import com.example.dimi.reactiveclean.repositories.Splash.SplashRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Single.just
import javax.inject.Inject

class SplashInterractorImpl
@Inject
constructor(private val repository: SplashRepository) : SplashInterractor {
    override fun isFirstLaunch(): Single<Boolean> =
            repository.isFirstLaunch()
                    .flatMap(this::getAndWhenFirstSet)

    private fun getAndWhenFirstSet(value: Boolean): Single<Boolean> {
        val completable = when(value) {
            true -> repository.setFirstLaunch()
            false -> Completable.complete()
        }
        return completable.andThen(just(value))
    }
}