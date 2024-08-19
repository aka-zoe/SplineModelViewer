package com.zoe.show_model_demo.view

import android.content.Context
import android.view.Choreographer
import android.view.SurfaceView
import com.google.android.filament.Skybox
import com.google.android.filament.utils.KTXLoader
import com.google.android.filament.utils.ModelViewer
import com.google.android.filament.utils.Utils
import java.nio.ByteBuffer

class CustomViewer {
    companion object {
        init {
            Utils.init()
        }
    }

    private var choreographer: Choreographer? = null
    private var modelViewer: ModelViewer? = null

    fun loadEntity() {
        choreographer = Choreographer.getInstance()
    }

    fun setSurfaceView(
        mSurfaceView: SurfaceView,
        rgbaArr: FloatArray? = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)
    ) {
        modelViewer = ModelViewer(mSurfaceView)
        mSurfaceView.setOnTouchListener(modelViewer)
//        [1.0f, 1.0f, 1.0f, 1.0]

        //Skybox and background color
        //without this part the scene'll appear broken
        modelViewer?.scene?.skybox = modelViewer?.engine?.let { Skybox.Builder().build(it) }
//        modelViewer.scene.skybox?.setColor(1.0f, 1.0f, 1.0f, 1.0f) //White color
//        modelViewer.scene.skybox?.setColor(0f, 0f, 0f, 0f) //透明天空盒子
        if (rgbaArr != null) {
            modelViewer?.scene?.skybox?.setColor(rgbaArr)
        }
    }

    fun loadGlb(context: Context, name: String) {
        val buffer = readAsset(context, "models/${name}.glb")
        modelViewer?.apply {
            loadModelGlb(buffer)
            transformToUnitCube()
        }
    }

    fun loadGlb(context: Context, dirName: String, name: String) {
        val buffer = readAsset(context, "models/${dirName}/${name}.glb")
        modelViewer?.apply {
            loadModelGlb(buffer)
            transformToUnitCube()
        }
    }

    fun loadGltf(context: Context, name: String) {
        val buffer = context.assets.open("models/${name}.gltf").use { input ->
            val bytes = ByteArray(input.available())
            input.read(bytes)
            ByteBuffer.wrap(bytes)
        }
        modelViewer?.apply {
            loadModelGltf(buffer) { uri -> readAsset(context, "models/$uri") }
            transformToUnitCube()
        }
    }

    fun loadGltf(context: Context, dirName: String, name: String) {
        val buffer = context.assets.open("models/${dirName}/${name}.gltf").use { input ->
            val bytes = ByteArray(input.available())
            input.read(bytes)
            ByteBuffer.wrap(bytes)
        }
        modelViewer?.apply {
            loadModelGltf(buffer) { uri -> readAsset(context, "models/${dirName}/$uri") }
            transformToUnitCube()
        }
    }

    fun loadIndirectLight(context: Context, ibl: String) {
        // Create the indirect light source and add it to the scene.
        val buffer = readAsset(context, "environments/venetian_crossroads_2k/${ibl}_ibl.ktx")
        modelViewer?.engine?.let {
            KTXLoader.createIndirectLight(it, buffer).apply {
                intensity = 50_000f
                modelViewer?.scene?.indirectLight = this
            }
        }

    }

    fun loadEnvironment(context: Context, ibl: String) {
        // Create the sky box and add it to the scene.
        val buffer = readAsset(context, "environments/venetian_crossroads_2k/${ibl}_skybox.ktx")
        modelViewer?.engine?.let {
            KTXLoader.createSkybox(it, buffer).apply {
                modelViewer?.scene?.skybox = this
            }
        }

    }


    private fun readAsset(context: Context, assetName: String): ByteBuffer {
        val input = context.assets.open(assetName)
        val bytes = ByteArray(input.available())
        input.read(bytes)
        return ByteBuffer.wrap(bytes)
    }

    private val frameCallback = object : Choreographer.FrameCallback {
        private val startTime = System.nanoTime()
        override fun doFrame(currentTime: Long) {
            val seconds = (currentTime - startTime).toDouble() / 1_000_000_000
            choreographer?.postFrameCallback(this)
            modelViewer?.animator?.apply {
                if (animationCount > 0) {
                    applyAnimation(0, seconds.toFloat())
                }
                updateBoneMatrices()
            }
            modelViewer?.render(currentTime)
        }
    }

    fun onResume() {
        choreographer?.postFrameCallback(frameCallback)
    }

    fun onPause() {
        choreographer?.removeFrameCallback(frameCallback)
    }

    fun onDestroy() {
        choreographer?.removeFrameCallback(frameCallback)
    }
}
