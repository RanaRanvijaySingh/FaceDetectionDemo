package facedetection.com.facedetectiondemo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var imageDrawableId = R.drawable.infinity;
//        var imageDrawableId = R.drawable.thor;
        var imageDrawableId = R.drawable.expandables;
//        var imageDrawableId = R.drawable.justiceleague;

        imageViewPic.setBackgroundResource(imageDrawableId)

        //1. Create an object of FirebaseVisionFaceDetectorOptions
        var options: FirebaseVisionFaceDetectorOptions =
                FirebaseVisionFaceDetectorOptions.Builder()
                        .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE)
                        .setLandmarkType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .setMinFaceSize(0.15f)
                        .setTrackingEnabled(true)
                        .build()


        var bitmap = BitmapFactory.decodeResource(resources, imageDrawableId)

        //2. Create FirebaseVisionImage object from bitmap
        var image: FirebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap)

        //3. Create instance of FirebaseVisionFaceDetector
        var detector = FirebaseVision.getInstance().getVisionFaceDetector(options)

        //4. Pass image to image detecting method.
        var result = detector.detectInImage(image)
                .addOnSuccessListener {
                    //Face detected successfully
                    Log.i(TAG, "addOnSuccessListener - Number of faces detected: " + it.size)
                    textViewFaceCount.setText("Number of face detected: " + it.size)
                }
                .addOnFailureListener {
                    //Face detected failed
                    Log.i(TAG, "addOnFailureListener - Number of faces detected: ")
                    textViewFaceCount.setText("Number of face detected: ")
                }
    }
}
