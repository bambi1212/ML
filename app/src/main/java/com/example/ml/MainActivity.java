package com.example.ml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;
import com.google.mlkit.vision.objects.defaults.PredefinedCategory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Live detection and tracking
        ObjectDetectorOptions options =
                new ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                        .enableClassification()  // Optional
                        .build();

        // Multiple object detection in static images
        options =
                new ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableMultipleObjects()
                        .enableClassification()  // Optional
                        .build();
        ObjectDetector objectDetector = ObjectDetection.getClient(options);

        // Call getImageFromDrawable method to get InputImage
        InputImage inputImage = getImageFromDrawable(this);

        objectDetector.process(inputImage)
                .addOnSuccessListener(
                        new OnSuccessListener<List<DetectedObject>>() {
                            @Override
                            public void onSuccess(List<DetectedObject> detectedObjects) {
                                // Task completed successfully
                                // Process the detected objects here
                                processDetectedObjects(detectedObjects);
                                ImageView iv = findViewById(R.id.imageResult);
                                Bitmap bitmap = inputImage.getBitmapInternal();
                                iv.setImageBitmap(bitmap);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // Handle the failure
                            }
                        });
    }

    // Function to process detected objects
    private void processDetectedObjects(List<DetectedObject> detectedObjects) {
        // Process the detected objects here
        for (DetectedObject detectedObject : detectedObjects) {
            Rect boundingBox = detectedObject.getBoundingBox();
            Integer trackingId = detectedObject.getTrackingId();
            for (DetectedObject.Label label : detectedObject.getLabels()) {
                String text = label.getText();
                if (PredefinedCategory.FOOD.equals(text)) {
                    // Handle FOOD object
                    // ...
                }
                int index = label.getIndex();
                if (PredefinedCategory.FOOD_INDEX == index) {
                    // Handle FOOD object by index
                    // ...
                }
                float confidence = label.getConfidence();
            }
        }
    }

    // Function to get InputImage from drawable
    public InputImage getImageFromDrawable(Context context) {
        // Decode drawable resource to bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bagtrash);

        // Create InputImage from bitmap
        InputImage image = InputImage.fromBitmap(bitmap, 0);

        return image;
    }
}



        /*

        // Live detection and tracking
            ObjectDetectorOptions options =
                    new ObjectDetectorOptions.Builder()
                            .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                            .enableClassification()  // Optional
                            .build();

            // Multiple object detection in static images
             options =
                    new ObjectDetectorOptions.Builder()
                            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                            .enableMultipleObjects()
                            .enableClassification()  // Optional
                            .build();
        ObjectDetector objectDetector = ObjectDetection.getClient(options);


        InputImage image =getImageFromDrawable(this);


        objectDetector.process(image)
                .addOnSuccessListener(
                        new OnSuccessListener<List<DetectedObject>>() {
                            @Override
                            public void onSuccess(List<DetectedObject> detectedObjects) {
                                // Task completed successfully
                                // ...
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                            }
                        });

        // The list of detected objects contains one item if multiple
// object detection wasn't enabled.
        for (DetectedObject detectedObject : detectedObjects) {
            Rect boundingBox = detectedObject.getBoundingBox();
            Integer trackingId = detectedObject.getTrackingId();
            for (Label label : detectedObject.getLabels()) {
                String text = label.getText();
                if (PredefinedCategory.FOOD.equals(text)) {
            ...
                }
                int index = label.getIndex();
                if (PredefinedCategory.FOOD_INDEX == index) {
            ...
                }
                float confidence = label.getConfidence();
            }
        }

    }
    public InputImage getImageFromDrawable(Context context) {
        // Decode drawable resource to bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bagtrash);

        // Create InputImage from bitmap
        InputImage image = InputImage.fromBitmap(bitmap, 0);

        return image;
    }




         */


