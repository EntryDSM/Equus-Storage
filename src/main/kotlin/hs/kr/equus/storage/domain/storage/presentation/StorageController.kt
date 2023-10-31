package hs.kr.equus.storage.domain.storage.presentation

import hs.kr.equus.storage.domain.storage.service.FileUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/storage")
@RestController
class StorageController(
    private val fileUploadService: FileUploadService
) {
    @PostMapping("/upload")
    fun uploadImage(
        @RequestPart("image") file: MultipartFile,
        @RequestParam(
            "path"
        ) path: String
    ) =
        fileUploadService.upload(
            file,
            path
        )
}
