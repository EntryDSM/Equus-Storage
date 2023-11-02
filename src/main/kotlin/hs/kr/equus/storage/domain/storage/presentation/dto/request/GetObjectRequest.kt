package hs.kr.equus.storage.domain.storage.presentation.dto.request

data class GetObjectRequest(
    val path: String,
    val objectName: String
)
