package com.mfahimi.domain


import kotlinx.coroutines.flow.Flow
import com.mfahimi.domain.result.Result

typealias FlowResponse<T> = Flow<Result<T>>
