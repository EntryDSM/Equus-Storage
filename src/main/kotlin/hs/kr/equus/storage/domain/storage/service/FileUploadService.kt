package hs.kr.equus.storage.domain.storage.service

import org.springframework.web.multipart.MultipartFile

interface FileUploadService {
    fun upload(file: MultipartFile, path: String): String
}
