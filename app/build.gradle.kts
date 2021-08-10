plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt").version("1.18.0-RC3")
    id("com.diffplug.spotless") version "5.14.2"
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.peterfarlow"
        minSdk = 28
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")
    implementation("androidx.compose.ui:ui:1.0.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.1")
    // Material Design
    implementation("androidx.compose.material:material:1.0.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.0.1")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.1")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.0.1")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.1")

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")

    implementation("io.coil-kt:coil-compose:1.3.2")

}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config =
        files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    baseline =
        file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt

    reports {
        html.enabled = true // observe findings in your browser with structure and code snippets
        xml.enabled = true // checkstyle like format mainly for integrations like Jenkins
        txt.enabled =
            true // similar to the console output, contains issue signature to manually edit baseline files
        sarif.enabled =
            true // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
    }
}

// Kotlin DSL
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    jvmTarget = "1.8"
}

spotless {
    format("misc") {
        // define the files to apply `misc` to
        target("*.md", ".gitignore")

        // define the steps to apply to those files
        trimTrailingWhitespace()
        indentWithTabs() // or spaces. Takes an integer argument if you don't like 4
        endWithNewline()
    }
    kotlin {
        target("**/*.kt")
        ktlint("0.41.0")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
