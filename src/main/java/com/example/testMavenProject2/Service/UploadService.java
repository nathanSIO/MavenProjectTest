package com.example.testMavenProject2.Service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;

@Service
// @Configuration
@Component
@PropertySource("classpath:s3.properties")
public class UploadService {

    private MinioClient minioClient;

    // @Value(System.getProperty("s3.bucket.name"))

    // String defaultBucketName;

    @Autowired (required = true)
    private Environment env;

    // @Value("${minio.folder.name}")
    // String baseFolder;
    


    // public UploadService(Environment env){
    //     this.environment = env;
    //     profile = env.getProperty("s3.profile");
    //     // s3Client = getS3Client();
    // }


    public void setup() throws Exception {
        System.out.println("setup");
        // System.out.println("Key : " + key);
        File tempDir = new File(System.getProperty("java.io.tmpdir"));

        File temp = File.createTempFile("originalFilename", ".txt", tempDir);
        Files.write(temp.toPath(), "Hello World !".getBytes(StandardCharsets.UTF_8));

        Resource resource = new ClassPathResource("s3.properties");
        Properties properties = new Properties();
        try {

            properties.load(resource.getInputStream());

            for (Object key : properties.keySet()) {
                env.getProperty(key.toString(), properties.getProperty(key.toString()));
            }
            minioClient = MinioClient.builder()
                    .endpoint(env.getProperty("s3.endpoint"))
                    .credentials(env.getProperty("s3.access.key"), env.getProperty("s3.secret.key"))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    
    
    public void uploadFile(String name, int content) throws Exception {
        // if(!minioClient.bucketExists(bucketName)){
        //     minioClient.makeBucket(bucketName);
        // }
        setup();
        System.err.println("Setup fini");
        StringBuilder builder = new StringBuilder();
        ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));

        // System.err.println(minioClient.bucketExists("s3.bucket.name"));
        // File file = new File("/tmp/" + name);
        // file.canWrite();
        // file.canRead();
        // try {
            // FileOutputStream iofs = new FileOutputStream(file);
            // iofs.write(content);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(env.getProperty("s3.bucket.name"))
                    .object(name)
                    .stream(bais,bais.available(), -1)
                    .build());
                // baseFolder + name, file.getAbsolutePath());
            // iofs.close();
        // } catch (Exception e) {
        //     throw new RuntimeException(e.getMessage());
        // }
    }

}
// StringBuilder builder = new StringBuilder();
       
//         File tempDir = new File(System.getProperty("java.io.tmpdir"));
//         System.err.println("File is here : " + tempDir);
//         File temp = File.createTempFile("originalFilename", ".txt", tempDir);

//         tempDir.canWrite();
//         tempDir.canRead();
//         // try {
//             ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
//             // System.err.println("JE SUIS LA ");
//             OutputStream iofs = new FileOutputStream(tempDir);
//             iofs.write(content);
            
            

//             minioClient.putObject(PutObjectArgs.builder()
//                     .bucket("dev-testMaven")
//                     .object(name)
//                     .stream(bais,bais.available(), -1)
//                     .build());
//             bais.close();
            
//             System.out.println("my object is upload ");
                
//             //  (defaultBucketName, defaultBaseFolder + name, file.getAbsolutePath());
//         // } catch (Exception e) {
//         //    throw new RuntimeException(e.getMessage());
//         // }