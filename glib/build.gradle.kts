plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

afterEvaluate{
    android.libraryVariants.forEach{ variant ->
        publishing.publications.create(variant.name,MavenPublication::class){
            from(components.findByName(variant.name))
            groupId = "com.gx"
            artifactId = "glib"
            version = "0.0.7"
        }
    }
}


android {
    namespace = "com.gx.glib"
    compileSdk = 35

    defaultConfig {
        minSdk = 19

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.refresh.layout.kernel)
    implementation(libs.refresh.layout.header)
    implementation(libs.refresh.layout.footer)
    implementation(libs.androidx.constraint.layout)
}