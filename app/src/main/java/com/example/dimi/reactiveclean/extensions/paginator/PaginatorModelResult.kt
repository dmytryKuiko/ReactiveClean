package com.example.dimi.reactiveclean.extensions.paginator

class PaginatorModelResult<T>(
        val showEmptyProgress: Boolean? = null,
        val showErrorMessage: Boolean? = null,
        val showEmptyView: Boolean? = null,
        val paginatorModelData: PaginatorModelData<T>? = null,
        val showRefreshProgress: Boolean? = null
)
