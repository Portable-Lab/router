plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "tw.yukina.portable-lab"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
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

extra["springShellVersion"] = "3.3.1"

dependencies {
	implementation("org.springframework.shell:spring-shell-starter")
	implementation("org.apache.sshd:sshd-core:2.13.2")
	implementation("com.github.hypfvieh:dbus-java:3.3.2")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.shell:spring-shell-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.shell:spring-shell-dependencies:${property("springShellVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
