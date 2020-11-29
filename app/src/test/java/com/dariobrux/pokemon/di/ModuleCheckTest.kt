//package com.dariobrux.pokemon.di
//
//import android.app.Application
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.dariobrux.pokemon.ui.main.MainViewModel
//import org.junit.Rule
//import org.junit.Test
//import org.junit.experimental.categories.Category
//import org.koin.android.ext.koin.androidContext
//import org.koin.core.parameter.parametersOf
//import org.koin.test.KoinTest
//import org.koin.test.category.CheckModuleTest
//import org.koin.test.check.checkModules
//import org.mockito.Mockito.mock
//
///**
// * Test Koin modules
// */
//@Category(CheckModuleTest::class)
//class ModuleCheckTest : KoinTest {
//
//    // Because we have some init states
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//    val mockedAndroidContext = mock(Application::class.java)
//
//    val fakeUrl = "http://fake.com"
//
//    @Test
//    fun testRemoteConfiguration() {
//        checkModules(parameters = {
//            create<MainViewModel> { parametersOf() }
//        }) {
//            printLogger()
//            modules(onlinePokemonApp)
//            properties(hashMapOf(Properties.SERVER_URL to fakeUrl))
//        }
//    }
//
//    @Test
//    fun testLocalConfiguration() {
//        checkModules(parameters = {
//            create<MainViewModel> { parametersOf() }
//        }) {
//            printLogger()
//            androidContext(mockedAndroidContext)
//            modules(offlinePokemonApp)
//            properties(hashMapOf(Properties.SERVER_URL to fakeUrl))
//        }
//    }
//
//    @Test
//    fun testTestConfiguration() {
//        checkModules(parameters = {
//            create<MainViewModel> { parametersOf() }
//        }) {
//            printLogger()
//            androidContext(mockedAndroidContext)
//            modules(testWeatherApp)
//            properties(hashMapOf(Properties.SERVER_URL to fakeUrl))
//        }
//    }
//}