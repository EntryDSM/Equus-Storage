package hs.kr.equus.storage.domain.storage.presentation

import hs.kr.equus.storage.domain.storage.presentation.dto.request.DeleteObjectRequest
import hs.kr.equus.storage.domain.storage.presentation.dto.request.GenerateObjectUrlRequest
import hs.kr.equus.storage.domain.storage.presentation.dto.request.GetObjectRequest
import hs.kr.equus.storage.domain.storage.service.DeleteObjectService
import hs.kr.equus.storage.domain.storage.service.FileUploadService
import hs.kr.equus.storage.domain.storage.service.GenerateObjectUrlService
import hs.kr.equus.storage.domain.storage.service.GetObjectService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/storage")
@RestController
class StorageController(
    private val fileUploadService: FileUploadService,
    private val deleteObjectService: DeleteObjectService,
    private val getObjectService: GetObjectService,
    private val generateObjectUrlService: GenerateObjectUrlService
) {
    @PostMapping
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

    @DeleteMapping
    fun deleteObject(@RequestBody deleteObjectRequest: DeleteObjectRequest) =
        deleteObjectService.delete(path = deleteObjectRequest.path, objectName = deleteObjectRequest.objectName)

    @GetMapping
    fun getObject(@RequestBody getObjectRequest: GetObjectRequest) =
        getObjectService.getObject(
            path = getObjectRequest.path,
            objectName = getObjectRequest.objectName
        )

    @GetMapping("/url")
    fun generateObjectUrl(@RequestBody generateObjectUrlRequest: GenerateObjectUrlRequest) =
        generateObjectUrlService.generateObjectUrl(
            objectName = generateObjectUrlRequest.objectName,
            path = generateObjectUrlRequest.path
        )
}
