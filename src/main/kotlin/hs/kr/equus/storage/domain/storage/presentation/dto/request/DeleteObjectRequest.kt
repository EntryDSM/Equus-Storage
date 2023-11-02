package hs.kr.equus.storage.domain.storage.presentation.dto.request

data class DeleteObjectRequest(
    val path: String,
    val objectName: String
)
