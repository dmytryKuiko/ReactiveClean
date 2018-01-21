package com.example.dimi.reactiveclean.domain.splash

import com.example.dimi.reactiveclean.data.splash.SplashRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Single.just
import javax.inject.Inject

class SplashInteractorImpl
@Inject constructor(private val repository: SplashRepository) : SplashInteractor {

    override fun isFirstLaunch(): Single<Boolean> =
        repository.isFirstLaunch()
            .flatMap(this::getAndWhenFirstSet)

    private fun getAndWhenFirstSet(value: Boolean): Single<Boolean> {
        val completable = when (value) {
            true -> repository.setFirstLaunch()
            false -> Completable.complete()
        }
        return completable.andThen(just(value))
    }
}