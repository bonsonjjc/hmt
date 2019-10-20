package com.zs.comm;

import okhttp3.*;
import okio.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static OkHttpClient okHttpClient;

    public static final MediaType JSON_TYPE = MediaType.parse("application/json");
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("multipart/form-data");

    static {
        init();
    }

    private HttpUtil() {

    }

    public static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.callTimeout(30, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }

    public static String post(String url, Map<String, Object> param, Map<String, String> header) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        if (param != null) {
            for (String key : param.keySet()) {
                builder.add(key, param.get(key) + "");
            }
        }
        Response response = request(url, "post", builder.build(), header);
        return string(response);
    }

    public static String post(String url, String json, Map<String, String> header) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON_TYPE, json);
        Response response = request(url, "post", requestBody, header);
        return string(response);
    }

    public static String get(String url, Map<String, String> header) throws IOException {
        Response response = request(url, "get", null, header);
        return string(response);
    }

    public static boolean download(String url, Map<String, String> header, File file, OnProgress progress) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        Response response = request(url, "get", null, header);
        InputStream inputStream = stream(response);
        try {
            if (progress != null) {
                byte[] bytes = new byte[1024];
                int read;
                long total = response.body().contentLength();
                long readSize = 0;
                while (-1 != (read = inputStream.read(bytes))) {
                    outputStream.write(bytes, 0, read);
                    readSize += read;
                    progress.onProgress(total, readSize);
                }
            }
            IOUtils.copy(inputStream, outputStream);
            return true;
        } catch (IOException e) {
            throw e;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {

                }
            }
        }
    }

    public static String upload(String url, String path, String fileName, Map<String, String> header, OnProgress onProgress) throws IOException {
        MultipartBody.Builder uploadBuilder = new MultipartBody.Builder();
        uploadBuilder.setType(MultipartBody.FORM);
        RequestBody requestBody;
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("file is not exists");
        }
        if (onProgress == null) {
            requestBody = RequestBody.create(FORM_DATA_TYPE, file);
        } else {
            requestBody = UploadBody.create(file, onProgress);
        }
        uploadBuilder.addFormDataPart("file", fileName, requestBody);
        Response post = request(url, "post", uploadBuilder.build(), header);
        return string(post);
    }

    private static Response request(String url, String method, RequestBody body, Map<String, String> header) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        if ("get".equals(method.toLowerCase())) {
            builder.get();
        } else if ("post".equals(method.toLowerCase())) {
            builder.post(body);
        }
        if (header != null) {
            builder.headers(Headers.of(header));
        }
        Call call = okHttpClient.newCall(builder.build());
        return call.execute();
    }

    private static InputStream stream(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() == null) {
                throw new IOException("下载失败");
            }
            return response.body().byteStream();
        }
        throw new IOException(response.message());
    }

    private static String string(Response execute) throws IOException {
        if (execute.isSuccessful()) {
            if (execute.body() == null) {
                return "";
            }
            return execute.body().string();
        } else {
            return "";
        }
    }

    public interface OnProgress {
        void onProgress(long total, long progress);
    }


    public static String unit(long size) {
        //如果字节数少于1024，则直接以B为单位
        if (size < 1024) {
            return size + "B";
        }
        float unit = size / 1024f;
        if (unit < 1024) {
            return NumberUtil.roundHalfUp(unit, 2) + "KB";
        }
        unit = unit / 1024;
        if (unit < 1024) {
            return NumberUtil.roundHalfUp(unit, 2) + "MB";
        }
        unit = unit / 1024;
        return NumberUtil.roundHalfUp(unit, 2) + "GB";
    }


    public static class UploadBody extends RequestBody {
        private File file;
        private MediaType contentType;
        private OnProgress onProgress;

        public UploadBody(File file, MediaType contentType, OnProgress onProgress) {
            this.file = file;
            this.contentType = contentType;
            this.onProgress = onProgress;
        }

        public UploadBody(File file) {
            this.file = file;
        }

        @Override
        public MediaType contentType() {
            return contentType;
        }

        @Override
        public long contentLength() {
            return file.length();
        }

        @Override
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Source source;
            source = Okio.source(file);
            Buffer buffer = new Buffer();
            long fileSize = contentLength();
            long progress = 0;
            long readSize;
            while (-1 != (readSize = source.read(buffer, 1024))) {
                bufferedSink.write(buffer, readSize);
                progress += readSize;
                onProgress.onProgress(fileSize, progress);
            }
        }

        public static UploadBody create(File file, OnProgress onProgress) {
            return new UploadBody(file, FORM_DATA_TYPE, onProgress);
        }
    }
}
