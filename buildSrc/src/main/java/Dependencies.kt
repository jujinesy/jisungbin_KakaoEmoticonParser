import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 23
    const val targetSdk = 30
    const val compileSdk = 30
    const val versionCode = 14
    const val jvmTarget = "1.8"
    const val versionName = "1.1.0"

    val targetCompat = JavaVersion.VERSION_1_8
    val sourceCompat = JavaVersion.VERSION_1_8
}

object Versions {
    object Essential {
        const val Anko = "0.10.8"
        const val Kotlin = "1.4.10"
        const val Gradle = "4.1.0"
        const val CoreKtx = "1.3.2"
    }

    object Di {
        const val Hilt = "2.28-alpha"
    }

    object Ui {
        const val Glide = "4.11.0"
        const val CardView = "1.0.0"
        const val ConstraintLayout = "2.0.2"
        const val Lottie = "3.4.4"
    }

    object Util {
        const val AndroidUtil = "4.0.3"
        const val CrashReporter = "1.1.0"
    }

    object Network {
        const val Jsoup = "1.13.1"
        const val OkHttp = "4.9.0"
        const val Retrofit = "2.9.0"
    }

    object Rx {
        const val RxKotlin = "3.0.1"
        const val RxAndroid = "3.0.0"
        const val RxRetrofit = "2.9.0"
    }
}

object Dependencies {
    object Network {
        const val Jsoup = "org.jsoup:jsoup:${Versions.Network.Jsoup}"
        const val Retrofit = "com.squareup.okhttp3:okhttp:${Versions.Network.OkHttp}"
        const val OkHttp = "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}"
    }

    object Rx {
        const val Kotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.Rx.RxKotlin}"
        const val Android = "io.reactivex.rxjava3:rxandroid:${Versions.Rx.RxAndroid}"
        const val RxRetrofit = "com.squareup.retrofit2:adapter-rxjava3:${Versions.Rx.RxRetrofit}"
    }

    object Essential {
        const val Anko = "org.jetbrains.anko:anko:${Versions.Essential.Anko}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.Essential.CoreKtx}"
        const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.Kotlin}"
    }

    object Di {
        const val Hilt = "com.google.dagger:hilt-android:${Versions.Di.Hilt}"
        const val HiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Di.Hilt}"
    }

    object Ui {
        const val Lottie = "com.airbnb.android:lottie:${Versions.Ui.Lottie}"
        const val Glide = "com.github.bumptech.glide:glide:${Versions.Ui.Glide}"
        const val CardView = "androidx.cardview:cardview:${Versions.Ui.CardView}"
        const val GlideCompiler = "com.github.bumptech.glide:compiler:${Versions.Ui.Glide}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Ui.ConstraintLayout}"
    }

    object Util {
        const val AndroidUtil = "com.github.sungbin5304:AndroidUtils:${Versions.Util.AndroidUtil}"
        const val CrashReporter =
            "com.balsikandar.android:crashreporter:${Versions.Util.CrashReporter}"
    }
}