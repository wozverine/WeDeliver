plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id("kotlin-kapt")
	id("dagger.hilt.android.plugin")
}

android {
	namespace = "com.glitch.wedeliver"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.glitch.wedeliver"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
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
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)

	implementation ("com.google.code.gson:gson:2.10")
	implementation("androidx.navigation:navigation-compose:2.7.6")
	implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
	implementation("androidx.activity:activity-ktx:1.6.1")

	implementation("androidx.datastore:datastore-preferences:1.0.0")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
	api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
	api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

	implementation("com.google.dagger:hilt-android:2.48")
	kapt("com.google.dagger:hilt-android-compiler:2.48")

	implementation ("androidx.room:room-runtime:2.6.1")
	kapt("androidx.room:room-compiler:2.6.1")
	implementation("androidx.room:room-ktx:2.6.1")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

	implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
	implementation("com.airbnb.android:lottie-compose:6.5.0")
	implementation("androidx.compose.foundation:foundation:1.7.1")

}