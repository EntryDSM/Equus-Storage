package hs.kr.equus.storage.domain.storage.service

interface GetObjectService {
    fun getObject(path: String, objectName: String): ByteArray
}
