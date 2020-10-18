class Deps{
    public static final support = [
            appcompat: "androidx.appcompat:appcompat:$Versions.appcompat",
            constraintlayout: "androidx.constraintlayout:constraintlayout:$Versions.constraintlayout",
            recyclerview: "androidx.recyclerview:recyclerview:$Versions.recyclerview",
            cardview: "androidx.cardview:cardview:$Versions.cardview",
            corektx: "androidx.core:core-ktx:$Versions.corektx",
            collectionsKtx: "androidx.collection:collection-ktx:$Versions.collectionsktx",
            fragmentKtx: "androidx.fragment:fragment-ktx:$Versions.fragmentKtx",
            lifecycleKtx: "androidx.lifecycle:lifecycle-runtime-ktx:$Versions.lifecycleKtx",
            percentlayout: "androidx.percentlayout:percentlayout:$Versions.percentlayout",
            multidex: "androidx.multidex:multidex:$Versions.multidex"
    ]

    public static final design = [
            material: "com.google.android.material:material:$Versions.material",
            vectordrawable: "androidx.vectordrawable:vectordrawable:$Versions.vectordrawable"
    ]

    public static final database = [
            room: "android.arch.persistence.room:runtime:$Versions.room",
            roomKotlin: "androidx.room:room-ktx:$Versions.room"
    ]

    public static final unitTests = [
            junit: "junit:junit:$Versions.junit",
            roomTests: "androidx.room:room-testing:$Versions.room",
            androidCore: "android.arch.core:core-testing:$Versions.coreTesting",
            mockk: "io.mockk:mockk:$Versions.mockk"
    ]

    public static final automatedTests = [
            runner: "com.android.support.test:runner:$Versions.runner",
            espresso: "com.android.support.test.espresso:espresso-core:$Versions.espresso"
    ]

    public static final kotlinLibs = [
            stdlib: "org.jetbrains.kotlin:kotlin-stdlib:$Versions.stdlib",
    ]

    public static final networking = [
            retorfit: "com.squareup.retrofit2:retrofit:$Versions.retrofit",
            gsonConverter: "com.squareup.retrofit2:converter-gson:$Versions.retrofit",
            okhttp: "com.squareup.okhttp3:okhttp:$Versions.okhttp",
            okhttpLoggingInterceptor: "com.squareup.okhttp3:logging-interceptor:$Versions.okhttp"
    ]

    public static final compilers = [
            roomCompiler: "android.arch.persistence.room:compiler:$Versions.room",
            daggerCompiler: "com.google.dagger:hilt-android-compiler:$Versions.hilt",
            hiltCompiler: "androidx.hilt:hilt-compiler:$Versions.hiltLifecycle"
    ]

    public static final dependencyInjection = [
            hilt: "com.google.dagger:hilt-android:$Versions.hilt",
            hiltLifecycle: "androidx.hilt:hilt-lifecycle-viewmodel:$Versions.hiltLifecycle"
    ]

    public static final rxjava = "io.reactivex.rxjava3:rxjava:$Versions.rxjava"
    public static final gson = "com.google.code.gson:gson:$Versions.gson"
}