package hs.kr.equus.storage.infrastructure.s3.dto.response

data class S3BulkResponseEntity(
    var bucket: String,
    var fileKey: String,
    var originFileName: String,
    var successful: Boolean,
    var statusCode: Int
)
