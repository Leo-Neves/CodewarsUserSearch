class Deps{
    public static final support = [
            appcompat: "androidx.appcompat:appcompat:$Versions.appcompat",
            constraintlayout: "androidx.constraintlayout:constraintlayout:$Versions.constraintlayout",
            recyclerview: "androidx.recyclerview:recyclerview:$Versions.recyclerview",
            cardview: "androidx.cardview:cardview:$Versions.cardview",
            corektx: "androidx.core:core-ktx:$Versions.corektx",
            percentlayout: "androidx.percentlayout:percentlayout:$Versions.percentlayout",
            multidex: "androidx.multidex:multidex:$Versions.multidex"
    ]

    public static final design = [
            material: "com.google.android.material:material:$Versions.material",
            vectordrawable: "androidx.vectordrawable:vectordrawable:$Versions.vectordrawable"
    ]

    public static final database = [
            greendao: "org.greenrobot:greendao:$Versions.greendao",
            sqlcipher: "net.zetetic:android-database-sqlcipher:$Versions.sqlcipher"
    ]

    public static final analytics = [
            crashlytics: "com.crashlytics.sdk.android:crashlytics:$Versions.crashlytics",
            acra: "ch.acra:acra:$Versions.acra"
    ]

    public static final unitTests = [
            junit: "junit:junit:$Versions.junit"
    ]

    public static final automatedTests = [
            runner: "com.android.support.test:runner:$Versions.runner",
            espresso: "com.android.support.test.espresso:espresso-core:$Versions.espresso"
    ]

    public static final kotlinLibs = [
            stdlib: "org.jetbrains.kotlin:kotlin-stdlib:$Versions.stdlib",
    ]

    public static final firebase = [
            firebasecore: "com.google.firebase:firebase-core:$Versions.firebasecore"
    ]

    public static final storage = [
            storageutil: "com.github.hendrawd:StorageUtil:$Versions.storageutil",
            storageChooser: "com.github.codekidX:storage-chooser:$Versions.storageChooser"
    ]

    public static final retrofit = [
            retorfit: "com.squareup.retrofit2:retrofit:$Versions.retrofit",
            gsonConverter: "com.squareup.retrofit2:converter-gson:$Versions.retrofit"
    ]

    public static final rxjava = "io.reactivex.rxjava3:rxjava:$Versions.rxjava"
    public static final gson = "com.google.code.gson:gson:$Versions.gson"
}