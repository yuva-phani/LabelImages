package com.example.cameraviewusecases

import android.app.Notification
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.label.FirebaseVisionCloudImageLabelerOptions
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions
import com.otaliastudios.cameraview.frame.Frame
import com.otaliastudios.cameraview.frame.FrameProcessor
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Override as Override1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // startForeground(1, Notification())

        setContentView(R.layout.activity_main)
        // camera.setLifecycleOwner(this);
print("START >>>>>>>>>>>>>>")
        Log.d("yy","START >>>>>>")
        camera_view.addFrameProcessor(
            FrameProcessor() {

                Log.d("yy","addFrameProcessor >>>>>>")


                Log.d("yy","process >>>>>>")

                val bytes = it.data
                val metadata = FirebaseVisionImageMetadata.Builder()
                    .setWidth(it.size.width) // 480x360 is typically sufficient for
                    .setHeight(it.size.height) // image recognition
                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                    .setRotation(FirebaseVisionImageMetadata.ROTATION_90)
                    .build()
                Log.d("yy","metadata >>>>>>")

                val image = FirebaseVisionImage.fromByteArray(bytes, metadata)
                Log.d("yy","image >>>>>>")

               // val detector = FirebaseVision.getInstance().getv
                val options = FirebaseVisionOnDeviceImageLabelerOptions.Builder()
                .setConfidenceThreshold(0.7f)
                    .build()
                val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler(options)
                /* val options = FirebaseVisionCloudImageLabelerOptions.Builder()
                 .setConfidenceThreshold(0.7f)
                  .build()
                val labeler = FirebaseVision.getInstance().getCloudImageLabeler(options)*/

                labeler.processImage(image)
                    .addOnSuccessListener { labels ->
                        for (label in labels) {
                            val text1 = label.text
                            val entityId = label.entityId
                            val confidence = label.confidence
                            print(">>>>>>$$$$ "+text1)
                            Log.d("label>> ",label.toString())
                            Log.d("test>> ",text1)
                            Log.d("label.entityId>> ",label.entityId)
                            Log.d("label.confidence>> ",label.confidence.toString())
                           // Log.d("test>> ",text1)
                            //editText.append(text1 + "\n")
                            editText.setText(text1+"%\n "+confidence)

                        }

                    }
                    .addOnFailureListener { e ->
                        // Task failed with an exception
                        // ...
                    }






        }
        )


        /* camera_view.captureFrame { cameraKitView, bytes ->
 print("INSIDE FRAME CAPTURE >>>>>>>")
             Log.d("yy","INSIDE FRAME CAPTURE >>>>>>> >>>>>>")


             val metadata = FirebaseVisionImageMetadata.Builder()
                 .setWidth(480) // 480x360 is typically sufficient for
                 .setHeight(360) // image recognition
                 .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                 .setRotation(FirebaseVisionImageMetadata.ROTATION_90)
                 .build()
             print("AFTER META DATA >>>>>>>>>>>>>>")
             Log.d("yy","METADATA >>>>>>> >>>>>>")

             val image = FirebaseVisionImage.fromByteArray(bytes, metadata)
 print("AFTER IMAGE >>>>>>>>>>>>>>>")
             Log.d("yy","IMAGE >>>>>>> >>>>>>")

             val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()
             println("AFTER LABLER >>>>>>>>>")
             Log.d("yy","LABLER >>>>>>> >>>>>>")

             labeler.processImage(image)
                 .addOnSuccessListener { labels ->
                     for (label in labels) {
                         val text = label.text
                         val entityId = label.entityId
                         val confidence = label.confidence
                         print(">>>>>>$$$$ " + text)
                     }

                 }
                 .addOnFailureListener { e ->
                     // Task failed with an exception
                     // ...
                 }


         }*/
    }

    override fun onResume() {
        super.onResume()
        camera_view.open()

    }

    override fun onPause() {
        super.onPause()
        camera_view.close()

    }


    override fun onStop() {
        super.onStop()
        camera_view.destroy()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //camera_view.onr

       // camera_view.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

}


       /* camera.addFrameProcessor(  FrameProcessor {

            fun process(frame: Frame) {
val bytes = frame.data
                val metadata = FirebaseVisionImageMetadata.Builder()
                    .setWidth(480) // 480x360 is typically sufficient for
                    .setHeight(360) // image recognition
                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                    .setRotation(FirebaseVisionImageMetadata.ROTATION_90)
                    .build()
                val image = FirebaseVisionImage.fromByteArray(bytes, metadata)
                 *//*val options = FirebaseVisionOnDeviceImageLabelerOptions.Builder()
    .setConfidenceThreshold(0.01f)
    .build()*//*
     val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()
                labeler.processImage(image)
                    .addOnSuccessListener { labels ->
                        for (label in labels) {
                            val text = label.text
                            val entityId = label.entityId
                            val confidence = label.confidence
                            print(">>>>>>$$$$ "+text)
                        }

                    }
                    .addOnFailureListener { e ->
                        // Task failed with an exception
                        // ...
                    }




            }

        }
                )


        fun  onResume() {
            super.onResume();
            camera_view.open();
        }


        fun  onPause() {
            super.onPause();
            camera.close();
        }

        fun  onDestroy() {
            super.onDestroy();
            camera.destroy();
        }

*/









