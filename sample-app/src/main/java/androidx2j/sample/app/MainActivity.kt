package androidx2j.sample.app

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LayoutInflater.from(this).inflate(R.layout.fragment_layout, null)
        LayoutInflater.from(this).inflate(R.layout.fragment_layout, null, false)
    }
}