package com.aws.rekognition;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

/**
 * Code adapted from Amazon example code at: https://docs.aws.amazon.com/code-samples/latest/catalog
 *
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
*/

public class Main {
    public static void main(String[] args) {

        // If two args not provided, output error message
        final String errorMsg = "Please provide a photo";
        if (args.length != 2) {
            System.out.println(errorMsg);
            System.exit(1);
        }

        // Get image from first arg
        String sourceImage = args[0];

        // Create RekognitionClient
        Region region = Region.US_EAST_2;
        RekognitionClient rekClient = RekognitionClient.builder()
                .region(region)
                .build();

        // Run desired method
        String detect = args[1];
        switch (detect) {
            case "faces":
                DetectFaces detectFaces = new DetectFaces();
                detectFaces.detectFacesinImage(rekClient, sourceImage);
                break;
            case "text":
                DetectText detectText = new DetectText();
                detectText.detectTextLabels(rekClient, sourceImage);
                break;
            case "labels":
                DetectLabels detectLabels = new DetectLabels();
                detectLabels.detectImageLabels(rekClient, sourceImage);
                break;
        }
        // Close client
        rekClient.close();
    }
}
