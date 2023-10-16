package com.example.nikakudirko.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


class MyApplication: Application()  {
    private fun startKoin(function:() -> Unit ){}
}