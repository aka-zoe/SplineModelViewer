package com.zoe.show_model_demo.view

import android.content.Context
import android.view.SurfaceView
import com.google.android.filament.Skybox
import com.google.android.filament.utils.KTXLoader
import com.google.android.filament.utils.ModelViewer
import com.zoe.show_model_demo.spline.SplinePlatformView
import java.nio.ByteBuffer

class ModelSurfaceView(context: Context) : SurfaceView(context) {

    private val customViewer: CustomViewer = CustomViewer()

    init {

        customViewer.run {
            loadEntity()

            //directory and model each as param
//            loadGlb(context, "grogu", "grogu")
//            loadGlb(context, "sofa", "pink_sofa")
//            loadGlb(context, "lizi", "lizi")
//            loadGlb(context, "rocket", "rocket1")
//            loadGlb(context, "illusion_cube", "illusion_cube")
//            loadGlb(context, "macbook", "macbook")
//            loadGlb(context, "mushroom", "mushroom")
//            loadGlb(context, "arch", "arch")
//            loadGltf(context, "warcraft", "scene");

            //directory and model as one
            //loadGlb(context, "grogu/grogu");

            //Enviroments and Lightning (OPTIONAL)
//            loadIndirectLight(context, "venetian_crossroads_2k")
//            loadEnviroment(context, "venetian_crossroads_2k");
        }
    }

    /**
     * 绑定surfaceView与盒子色值
     * [rgbaArr] rgba 色值
     */
    fun setSurfaceView(
        rgbaArr: FloatArray? = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)
    ) {
        customViewer.setSurfaceView(this@ModelSurfaceView, rgbaArr)
    }

    /**
     * 加载glb格式3D模型
     * [context]
     * [dirName]
     */
    fun loadGlb(context: Context, dirName: String) {
        customViewer.loadGlb(context, dirName)
    }

    /**
     * 加载glb格式3D模型
     * [context]
     * [dirName]
     * [name]
     */
    fun loadGlb(context: Context, dirName: String, name: String) {
        customViewer.loadGlb(context, dirName, name)
    }

    /**
     * 加载gltf格式3D模型
     * [context]
     * [name]
     */
    fun loadGltf(context: Context, name: String) {
        customViewer.loadGltf(context, name)
    }

    /**
     * 加载gltf格式3D模型
     * [context]
     * [dirName]
     * [name]
     */
    fun loadGltf(context: Context, dirName: String, name: String) {
        customViewer.loadGltf(context, dirName, name)
    }

    /**
     * 创建间接光源并将其添加到场景中
     * [context]
     * [ibl]
     */
    fun loadIndirectLight(context: Context, ibl: String) {
        customViewer.loadIndirectLight(context, ibl)
    }

    /**
     * 创建天空框并将其添加到场景中
     * [context]
     * [ibl]
     */
    fun loadEnvironment(context: Context, ibl: String) {
        customViewer.loadEnvironment(context, ibl)
    }

    fun onResume() {
        customViewer.onResume()
    }

    fun onPause() {
        customViewer.onPause()
    }

    fun onDestroy() {
        customViewer.onDestroy()
    }

}
