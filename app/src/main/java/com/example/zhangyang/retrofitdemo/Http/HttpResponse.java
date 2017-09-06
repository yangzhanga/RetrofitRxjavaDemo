package com.example.zhangyang.retrofitdemo.Http;

/**
 * Created by zhangyang on 2017/9/5.
 */

public class HttpResponse<T> {
    public boolean error;
    public T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
