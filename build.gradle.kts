import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0-M3"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.0"
	kotlin("plugin.spring") version "1.4.0"
}

group = "com.ssc.camunda.bpm"
version = "0.0.1-SNAPSHOT"
//java.sourceCompatibility  = VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {


	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("com.h2database:h2")

	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.14.0")
	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-rest:7.14.0")
	implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:7.14.0")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")




	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude( group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
