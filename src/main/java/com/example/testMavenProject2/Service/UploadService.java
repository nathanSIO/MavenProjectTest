package com.example.testMavenProject2.Service;

import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.taglibs.standard.extra.spath.Path;
import org.checkerframework.checker.units.qual.min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;

@Service
// @Configuration
@Component
@PropertySource("classpath:s3.properties")
public class UploadService {

    private MinioClient minioClient;

    @Autowired (required = true)
    private Environment env;

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
        
        System.out.println("checkS3Alive");
        boolean isAlive = false;
        List<Bucket> bucketList = minioClient.listBuckets();
        for (Bucket bucket : bucketList) {
            if (bucket.name().equals(env.getProperty("s3.bucket.name"))) {
                isAlive = true;
            }
        }
        System.err.println("Je suis en vie 1: " + isAlive);
    }

    
    
    public void uploadFile(String uuid, String name, long filelength, MultipartFile file) throws Exception {
        // if(!minioClient.bucketExists(bucketName)){
        //     minioClient.makeBucket(bucketName);
        // }
        setup();
        System.err.println("Setup fini : " + "Debut UploadFile");
        StringBuilder builder = new StringBuilder();
        InputStream istream = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));

        // try {
            ObjectWriteResponse  objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                                                            .bucket(env.getProperty("s3.bucket.name"))
                                                            .object(uuid)
                                                            .stream(istream,file.getSize() ,-1)
                                                            .build());
            System.err.println("Mon Objet " + objectWriteResponse.bucket());

        // } catch (Exception e) {
        //     System.err.println("ERREUR !!!");
        // }
        
        // Afficher la liste d'objets présent dans le S3
        System.out.println("checkListObject");
        
        Iterable<Result<Item>> results = minioClient.listObjects(
        ListObjectsArgs.builder()
            .bucket("accolade-dev")
            .build());
        
        // affiche la liste d'objet sur le s3
        // for (Result<Item> result : results) {
        //     System.err.println("List object : " + result.get().objectName() + " / size : " + result.get().size());
        // }
        
    }

    public void getFile(String uuid)  throws Exception{
        // setup();

        // test S3 is Alive
        System.out.println("checkS3Alive");
        boolean isAlive = false;
        List<Bucket> bucketList = minioClient.listBuckets();
        for (Bucket bucket : bucketList) {
            if (bucket.name().equals(env.getProperty("s3.bucket.name"))) {
                isAlive = true;
            }
        }
        System.err.println("Je suis en vie : " + isAlive);


        // //////////////
        System.err.println("Setup fini : " + "Debut getFile()");
        InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                                .bucket("accolade-dev")
                                .object(uuid)
                                .build());
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = reader.readLine();
                System.out.println("Contenu de mon fichier : " + line);

        }catch (Exception e){
            e.printStackTrace();
        }
        

        // StringBuilder builder = new StringBuilder();

        // File tempDir = new File(System.getProperty("java.io.tmpdir") );
        // File temp = File.createTempFile(uuid, ".txt", tempDir);
        // System.err.println("File is here : " + tempDir);
        // tempDir.canWrite();
        // tempDir.canExecute();
        // Files.copy(stream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING );
        
        // System.err.println(tempDir.getAbsolutePath());

        
        // if (stream != null){
        //     System.err.println("Mon objet: " + stream.toString());
        // }
        // else{
        //     System.err.println("JE SUIS NULL");  
        // }

        // stream.close();
        
    }

}
