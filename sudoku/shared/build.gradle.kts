plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
        }

        commonTest.dependencies {
            implementation(libs.coroutines.test)
        }

        jvmMain.dependencies {
            implementation(libs.coroutines.jvm)
        }
    }
}
