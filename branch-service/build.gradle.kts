plugins {
  java
  id("org.springframework.boot") version "3.4.4"
  id("io.spring.dependency-management") version "1.1.7"
  id("org.hibernate.orm") version "6.6.11.Final"
//  id("org.graalvm.buildtools.native") version "0.10.6"
  id("com.google.protobuf") version "0.9.4"
}

group = "io.pranludi.crossfit"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

extra["springCloudVersion"] = "2024.0.1"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-security")
  // cloud
  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
  implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
  // kafka
  implementation("org.springframework.kafka:spring-kafka")
  // redisson
  implementation("org.redisson:redisson-spring-boot-starter:3.45.1")
  // mapstruct
  implementation("org.mapstruct:mapstruct:1.5.5.Final")
  annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
  // jwt
  implementation("io.jsonwebtoken:jjwt-api:0.12.6")
  runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
  // json
  implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.3")
  // zipkin
  implementation("io.micrometer:micrometer-tracing-bridge-brave")
  implementation("io.zipkin.reporter2:zipkin-reporter-brave")
  // db
  runtimeOnly("com.h2database:h2")
  runtimeOnly("org.postgresql:postgresql")
  // record-builder
  annotationProcessor("io.soabase.record-builder:record-builder-processor:44")
  implementation("io.soabase.record-builder:record-builder-core:44")
  compileOnly("io.soabase.record-builder:record-builder-processor:44")
  // protobuf
  implementation("com.google.protobuf:protobuf-java:4.30.2")
  implementation("com.google.protobuf:protobuf-java-util:4.30.2")
  // test
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

hibernate {
  enhancement {
    enableAssociationManagement = true
  }
}

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:4.30.2"
  }
  generateProtoTasks {
    all().forEach {
      it.plugins {
      }
    }
  }
}

sourceSets {
  main {
    proto {
      srcDir("../service-protocol/protobuf")
    }
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
