import org.gradle.internal.declarativedsl.parsing.main
import java.util.UUID

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "tech.voicer.models"
    compileSdk = 34

    buildFeatures { buildConfig = false }
    sourceSets {
        getByName("main") {
            assets.srcDirs("${layout.buildDirectory}/generated/assets")
        }
    }

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks.register("genUUID") {
    val uuid = UUID.randomUUID().toString()
    val odir = file("${layout.buildDirectory}/generated/assets/model-pt-br")
    val ofile = file("$odir/uuid")
    doLast {
        mkdir(odir)
        ofile.writeText(uuid)
    }
}

tasks.named("preBuild") {
    dependsOn("genUUID")
}
