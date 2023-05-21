package br.com.fiap.main;

import java.net.URL;
import java.util.Base64;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class S3Uploader {

	public static String uploadImageToS3(String base64Image, String objectKey) {
		String bucketName = "munera";

		S3Client s3Client = S3Client.builder().region(Region.SA_EAST_1)
				.credentialsProvider(StaticCredentialsProvider.create(
						AwsBasicCredentials.create("AKIA4S535KOUMDYFOTMV", "ZEwfhejxizvlSVyOYEHqqtODXz+m0IADOQSNEVpp")))
				.build();

		byte[] imageBytes = Base64.getDecoder().decode(base64Image);

		PutObjectResponse response = s3Client.putObject(
				PutObjectRequest.builder().bucket(bucketName).key(objectKey).contentType("image/jpeg").build(),
				RequestBody.fromBytes(imageBytes));

		if (response != null && response.sdkHttpResponse().isSuccessful()) {
			URL publicUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(objectKey).build());
			return publicUrl.toString();
		} else {
			System.out.println("Falha ao enviar a imagem para o Amazon S3.");
			return null;
		}
	}
}