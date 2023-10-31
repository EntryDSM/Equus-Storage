package hs.kr.equus.storage.infrastructure.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import hs.kr.equus.storage.domain.storage.service.FileUploadService
import org.imgscalr.Scalr
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO

@Service
class StorageServiceImpl(
    private val s3Client: AmazonS3Client
) : FileUploadService {

    companion object {
        const val EXP_TIME = 1000 * 60 * 2
    }

    @Value("\${aws.s3.bucket}")
    lateinit var bucketName: String

    override fun upload(file: MultipartFile, path: String): String {
        val ext = verificationFile(file)

        val randomName = UUID.randomUUID().toString()
        val filename = "$randomName.$ext"

        val os = ByteArrayOutputStream()
        val outputImage: BufferedImage = makeThumbnail(file)
        try {
            ImageIO.write(outputImage, "png", os)
        } catch (e: IOException) {
            throw IllegalArgumentException()
        }

        val inputStream = ByteArrayInputStream(os.toByteArray())
        val metadata = ObjectMetadata()
        metadata.contentType = MediaType.IMAGE_PNG_VALUE
        metadata.contentLength = os.size().toLong()
        metadata.contentDisposition = "inline"
        val fileKey = path + filename
        s3Client.putObject(
            PutObjectRequest(bucketName, path + filename, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.AuthenticatedRead)
        )
        return filename
    }

    private fun verificationFile(file: MultipartFile?): String {
        file?.let {
            if (it.isEmpty || it.originalFilename == null) {
                throw IllegalArgumentException("잘못된 파일 확장자 입니다.")
            }

            val originalFilename = it.originalFilename
            val ext = originalFilename!!.substringAfterLast(".", "")
            val lowerExt = ext.lowercase(Locale.getDefault())

            if (!(lowerExt == "jpg" || lowerExt == "jpeg" || lowerExt == "png" || lowerExt == "heic")) {
                throw IllegalArgumentException("잘못된 파일 확장자 입니다.")
            }

            return ext
        } ?: throw IllegalArgumentException("파일이 유효하지 않습니다.")
    }

    private fun makeThumbnail(file: MultipartFile): BufferedImage {
        val srcImage: BufferedImage

        try {
            srcImage = ImageIO.read(file.inputStream)
        } catch (e: IOException) {
            throw IllegalArgumentException("파일이 비어있습니다.")
        }

        val dw = 300
        val dh = 400

        val ow = srcImage.width
        val oh = srcImage.height

        var nw = ow
        var nh = (ow * dh) / dw

        if (nh > oh) {
            nw = (oh * dw) / dh
            nh = oh
        }

        val cropImage = Scalr.crop(srcImage, (ow - nw) / 2, (oh - nh) / 2, nw, nh)
        return Scalr.resize(cropImage, dw, dh)
    }
}
