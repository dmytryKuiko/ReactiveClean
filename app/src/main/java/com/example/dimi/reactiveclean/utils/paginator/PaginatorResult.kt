package com.example.dimi.reactiveclean.utils.paginator

class PaginatorResult<T>(
        val showEmptyProgress: Boolean? = null,
        val showDatabaseMessage: Boolean? = null,
        val showEmptyView: Boolean? = null,
        val paginatorData: PaginatorData<T>? = null,
        val showRefreshProgress: Boolean? = null
)