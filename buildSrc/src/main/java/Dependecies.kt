object Versions {
    const val kotlin = "1.2.41"
    const val gradle = "3.1.3"
    const val gradleJunit = "1.0.3"
    const val gradleJunit5 = "1.0.32"
    const val compileSdkVersion = 27
    const val minSdkVersion = 21
    const val targetSdkVersion = 27
    const val buildToolsVersion = "27.1.1"
    const val constraintLayoutVersion = "1.1.2"
    const val junitVersion = "4.12"
    const val runnerVersion = "1.0.1"
    const val espressoVersion = "3.0.1"
    const val kotlinVersion = "1.2.20"
    const val mockitoVersion = "2.13.0"
    const val archComponentsLifeCycleVersion = "1.1.1"
    const val archComponentsRoomVersion = "1.1.0"
    const val leakCanaryVersion = "1.5.4"
    const val daggerVersion = "2.14.1"
    const val rxAndroidVersion = "2.0.1"
    const val rxJavaVersion = "2.1.13"
    const val rxBindingVersion = "2.0.0"
    const val rxRelayVersion = "2.0.0"
    const val retrofitVersion = "2.3.0"
    const val gsonVersion = "2.8.2"
    const val retrofitConverterGsonVersion = "2.3.0"
    const val okHttpVersion = "3.9.1"
    const val timberVersion = "4.6.0"
    const val ciceroneVersion = "3.0.0"
    const val picassoVersion = "2.5.2"
    const val adapterDelegatesVersion = "3.0.1"
    const val jodaTimeVersion = "2.9.9"
    const val hamcrestVersion = "1.3"
    const val spekVersion = "1.1.5"
}

object Dependencies {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val gradleJunit = "org.junit.platform:junit-platform-gradle-plugin:${Versions.gradleJunit}"
    const val gradleJunit5 = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.gradleJunit5}"
    const val appcompat = "com.android.support:appcompat-v7:${Versions.buildToolsVersion}"
    const val recyclerView = "com.android.support:recyclerview-v7:${Versions.buildToolsVersion}"
    const val supportV4 = "com.android.support:support-v4:${Versions.buildToolsVersion}"
    const val supportDesign = "com.android.support:design:${Versions.buildToolsVersion}"
    const val supportCustomTabs = "com.android.support:customtabs:${Versions.buildToolsVersion}"
    const val supportConstraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayoutVersion}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val testRunner = "com.android.support.test:runner:${Versions.runnerVersion}"
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espressoVersion}"

    //Hamcrest Matchers
    const val hamcrest = "org.hamcrest:hamcrest-library:${Versions.hamcrestVersion}"

    //Kotlin reflections
    const val kotlinReflections = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"

    //Mockito
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"

    //LiveData and ViewModel
    const val lifecycleExtensions = "android.arch.lifecycle:extensions:${Versions.archComponentsLifeCycleVersion}"

    //LifeCycles
    const val lifecycleRuntime = "android.arch.lifecycle:runtime:${Versions.archComponentsLifeCycleVersion}"
    const val lifecyclerCompiler = "android.arch.lifecycle:compiler:${Versions.archComponentsLifeCycleVersion}"

    //LiveData with ReactiveStreams
    const val lifecycleReactiveStreams = "android.arch.lifecycle:reactivestreams:${Versions.archComponentsLifeCycleVersion}"

    //LiveData testing
    const val coreTesting = "android.arch.core:core-testing:${Versions.archComponentsLifeCycleVersion}"

    //Room
    const val roomRuntime = "android.arch.persistence.room:runtime:${Versions.archComponentsRoomVersion}"

    //Room RxJava
    const val roomRxJava2 = "android.arch.persistence.room:rxjava2:${Versions.archComponentsRoomVersion}"
    const val roomCompiler = "android.arch.persistence.room:compiler:${Versions.archComponentsRoomVersion}"


    //Leak canary
    const val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanaryVersion}"
    const val leakcanaryAndroiNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanaryVersion}"

    //Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    //Rx
    const val rxJava2RxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxJava2RxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"

    //RxBindings
    const val rxBinding2AppCompat = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:${Versions.rxBindingVersion}"
    const val rxBinding2RxBinding = "com.jakewharton.rxbinding2:rxbinding:${Versions.rxBindingVersion}"
    const val rxBinding2SupportV4 = "com.jakewharton.rxbinding2:rxbinding-support-v4-kotlin:${Versions.rxBindingVersion}"
    const val rxBinding2RecyclerView = "com.jakewharton.rxbinding2:rxbinding-recyclerview-v7:${Versions.rxBindingVersion}"

    //RxRelay
    const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:${Versions.rxRelayVersion}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"

    //GSON
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGsonVersion}"

    //OkHttp
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"

    //Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    //Cicerone
    const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.ciceroneVersion}"

    //Picasso
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

    //AdapterDelegates
    const val adapterDelegates = "com.hannesdorfmann:adapterdelegates3:${Versions.adapterDelegatesVersion}"

    //JodaTime
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTimeVersion}"

    //Spek
    const val spekApi = "org.jetbrains.spek:spek-api:${Versions.spekVersion}"
    const val spekJunitPlatform = "org.jetbrains.spek:spek-junit-platform-engine:${Versions.spekVersion}"
}