object Dependencies {

    // kotlin
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin:${DependencyVersions.JACKSON_VERSION}"
    const val JACKSON_TYPE = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${DependencyVersions.JACKSON_VERSION}"

    // web
    const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"

    // test
    const val SPRING_TEST = "org.springframework.boot:spring-boot-starter-test:${PluginVersions.SPRING_BOOT_VERSION}"

    // validation
    const val SPRING_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"

    // Cloud Config
    const val CLOUD_CONFIG = "org.springframework.cloud:spring-cloud-config-client"

    // cloud
    const val SPRING_CLOUD = "org.springframework.cloud:spring-cloud-dependencies:${DependencyVersions.SPRING_CLOUD_VERSION}"
}
