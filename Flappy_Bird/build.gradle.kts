plugins {
//    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version("0.1.0")
}

application {
    mainClass = "me.Main"
}

javafx {
    version = "25"
    modules("javafx.controls", "javafx.fxml", "javafx.graphics")
}

//group = "me"
//version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//}
//
//tasks.test {
//    useJUnitPlatform()
//}