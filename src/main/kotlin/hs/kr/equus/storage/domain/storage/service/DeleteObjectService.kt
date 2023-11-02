package hs.kr.equus.storage.domain.storage.service

interface DeleteObjectService {
    fun delete(path: String, objectName: String)
}
