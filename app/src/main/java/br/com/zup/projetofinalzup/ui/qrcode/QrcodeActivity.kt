package br.com.zup.projetofinalzup.ui.qrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.projetofinalzup.R
import br.com.zup.projetofinalzup.databinding.ActivityQrcodeBinding
import br.com.zup.projetofinalzup.ui.home.view.HomeActivity
import com.google.zxing.integration.android.IntentIntegrator

class QrcodeActivity : AppCompatActivity() {
    companion object {
        const val RESULT = "RESULT"
    }

    private lateinit var binding: ActivityQrcodeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScan.setOnClickListener { initScanner() }
    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("posicione o leitor no qr code")
        integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}