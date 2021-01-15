package vn.betting.automation.infrastructure.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HttpRequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    public static String sendPost(List<NameValuePair> nvps, String postUrl, int timeout, String accessKey) throws IOException {
        String result;
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
        try (CloseableHttpClient httpclient =
                     HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            HttpPost httpPost = new HttpPost(postUrl);
            httpPost.addHeader("apiKey", accessKey);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException(
                            "Failed : HTTP getStatusCode: " + response.getStatusLine().getStatusCode()
                                    + " HTTP getReasonPhrase: " + response.getStatusLine().getReasonPhrase());
                }
                HttpEntity entity = response.getEntity();
                try (InputStream inputStream = entity.getContent()) {
                    result = IOUtils.toString(inputStream, "UTF-8");
                }
            }
        }
        return result;
    }

    public static String sendPostViaProxy(List<NameValuePair> nvps, String postUrl, String proxyHost,
                                          int proxyPort, int timeout, String accessKey) throws Exception {

        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(timeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setProxy(proxy).build();
        try (CloseableHttpClient httpclient =
                     HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            HttpPost httpPost = new HttpPost(postUrl);
            httpPost.addHeader("apiKey", accessKey);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                String resp = IOUtils.toString(inputStream, "UTF-8");
                return resp;
            }
        }
    }

    public static String sendGet(String url, int timeout, String accessKey) throws IOException {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setStaleConnectionCheckEnabled(true)
                .build();

        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("apiKey", accessKey);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP getStatusCode: "
                            + response.getStatusLine().getStatusCode()
                            + " HTTP getReasonPhrase: "
                            + response.getStatusLine().getReasonPhrase() + " url " + url);
                }
                try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                }
            }
        }
    }

    public static String sendGetViaProxy(String url, String proxyHost, int proxyPort, int timeout, String accessKey) throws IOException {

        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setStaleConnectionCheckEnabled(true)
                .setProxy(proxy)
                .build();

        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("apiKey", accessKey);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP getStatusCode: "
                            + response.getStatusLine().getStatusCode()
                            + " HTTP getReasonPhrase: "
                            + response.getStatusLine().getReasonPhrase());
                }
                try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                }
            }

        }
    }

    public static String sendGet(List<NameValuePair> nvps, String getUrl, int timeout, String accessKey) throws Exception {
        String result;
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setStaleConnectionCheckEnabled(true)
                .build();
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            URIBuilder builder = new URIBuilder(getUrl);
            for (NameValuePair item : nvps) {
                builder.addParameter(item.getName(), item.getValue());
            }
            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.addHeader("apiKey", accessKey);
            httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP getStatusCode: "
                            + response.getStatusLine().getStatusCode() + " HTTP getReasonPhrase: " + response.getStatusLine().getReasonPhrase());
                }
                HttpEntity entity = response.getEntity();
                try (InputStream inputStream = entity.getContent()) {
                    result = IOUtils.toString(inputStream, "UTF-8");
                }
            }
        }
        return result;
    }

    public static String sendGetViaProxy(List<NameValuePair> nvps, String getUrl, String proxyHost, int proxyPort, int timeout, String accessKey) throws Exception {
        String result;
        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setStaleConnectionCheckEnabled(true)
                .setProxy(proxy)
                .build();
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            URIBuilder builder = new URIBuilder(getUrl);
            for (NameValuePair item : nvps) {
                builder.addParameter(item.getName(), item.getValue());
            }
            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.addHeader("apiKey", accessKey);
            httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP getStatusCode: "
                            + response.getStatusLine().getStatusCode() + " HTTP getReasonPhrase: " + response.getStatusLine().getReasonPhrase());
                }
                HttpEntity entity = response.getEntity();
                try (InputStream inputStream = entity.getContent()) {
                    result = IOUtils.toString(inputStream, "UTF-8");
                }
            }
        }
        return result;
    }


    public static String sendPostJson(String postUrl, String jsonContent, int timeout, String accessKey)
            throws Exception {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .build();
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            HttpPost httpPost = new HttpPost(postUrl);
            httpPost.addHeader("apiKey", accessKey);
            httpPost.setHeader("Accept", "application/json;charset=UTF-8");
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity input = new StringEntity(jsonContent, "UTF-8");
            input.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(input);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP getStatusCode: "
                            + response.getStatusLine().getStatusCode()
                            + " HTTP getReasonPhrase: "
                            + response.getStatusLine().getReasonPhrase());
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())))) {
                    String output;
                    StringBuilder strBuilder = new StringBuilder();
                    while ((output = br.readLine()) != null) {
                        strBuilder.append(output);
                    }
                    return strBuilder.toString();
                }
            }
        }
    }

    public static String sendPostJsonViaProxy(String postUrl, String jsonContent, String proxyHost, int proxyPort, int timeout, String accessKey)
            throws Exception {
        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setStaleConnectionCheckEnabled(true)
                .setProxy(proxy)
                .build();
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build()) {
            HttpPost httpPost = new HttpPost(postUrl);
            httpPost.addHeader("apiKey", accessKey);
            httpPost.setHeader("Accept", "application/json;charset=UTF-8");
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity input = new StringEntity(jsonContent, "UTF-8");
            input.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(input);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new IOException("Failed : HTTP getStatusCode: "
                            + response.getStatusLine().getStatusCode()
                            + " HTTP getReasonPhrase: "
                            + response.getStatusLine().getReasonPhrase());
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())))) {
                    String output;
                    StringBuilder strBuilder = new StringBuilder();
                    while ((output = br.readLine()) != null) {
                        strBuilder.append(output);
                    }
                    return strBuilder.toString();
                }
            }
        }
    }

    public static String sendPostByte(String targetURL, byte[] PostData, int timeout) throws Exception {
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "text/html");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + PostData.length);
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(PostData);
                wr.flush();
            }
            InputStream is = connection.getInputStream();
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
                String line;
                response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }
}
